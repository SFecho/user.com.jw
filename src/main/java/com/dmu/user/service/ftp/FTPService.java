package com.dmu.user.service.ftp;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

@Service("ftpService")
public class FTPService implements IFTPService {
    @Value("${ftp.username}")
    private String username;

    @Value("${ftp.password}")
    private String password;

    @Value("${ftp.hostname}")
    private String hostname;

    @Value("${ftp.port}")
    private int port;
    private static final Logger logger = (Logger) LogManager.getLogger(FTPService.class);

    /**
     * 上传文件（可供Action/Controller层使用）
     * @param pathname FTP服务器保存目录
     * @param fileName 上传到FTP服务器后的文件名称
     * @param inputStream 输入文件流
     * @return
     */
    public  boolean uploadFile(String pathname, String fileName, InputStream inputStream){
        boolean flag = false;
        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding("UTF-8");
        try {
            //连接FTP服务器
            ftpClient.connect(hostname, port);
            //登录FTP服务器
            ftpClient.login(username, password);
            //是否成功登录FTP服务器
            int replyCode = ftpClient.getReplyCode();
            if(!FTPReply.isPositiveCompletion(replyCode)){
                return flag;
            }

            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.makeDirectory(pathname);
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            ftpClient.logout();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(ftpClient.isConnected()){
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }


    /**
     * 上传文件（可对文件进行重命名）
     * @param pathname FTP服务器保存目录
     * @param filename 上传到FTP服务器后的文件名称
     * @param originfilename 待上传文件的名称（绝对地址）
     * @return
     */
    @Override
    public  boolean uploadFileFromProduction(String pathname, String filename, String originfilename){
        boolean flag = false;
        try {
            InputStream inputStream = new FileInputStream(new File(originfilename));
            flag = uploadFile(pathname, filename, inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 上传文件（不可以进行文件的重命名操作）
     * @param pathname FTP服务器保存目录
     * @param originfilename 待上传文件的名称（绝对地址）
     * @return
     */
    @Override
    public  boolean uploadFileFromProduction(String pathname, String originfilename){
        boolean flag = false;
        try {
            String fileName = new File(originfilename).getName();
            InputStream inputStream = new FileInputStream(new File(originfilename));
            flag = uploadFile( pathname, fileName, inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * 删除文件
     * @param pathname FTP服务器保存目录
     * @param filename 要删除的文件名称
     * @return
     */
    @Override
    public  boolean deleteFile(String pathname, String filename){
        boolean flag = false;
        FTPClient ftpClient = new FTPClient();
        try {
            //连接FTP服务器
            ftpClient.connect(hostname, port);
            //登录FTP服务器
            ftpClient.login(username, password);
            //验证FTP服务器是否登录成功
            int replyCode = ftpClient.getReplyCode();
            if(!FTPReply.isPositiveCompletion(replyCode)){
                return flag;
            }
            //切换FTP目录
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.dele(filename);
            ftpClient.logout();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(ftpClient.isConnected()){
                try {
                    ftpClient.logout();
                } catch (IOException e) {

                }
            }
        }
        return flag;
    }

    /**
     * 下载文件
     * @param pathname FTP服务器文件目录
     * @param filename 文件名称
     * @param localpath 下载后的文件路径
     * @return
     */
    public  boolean downloadFile( String pathname, String filename, String localpath) {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding("UTF-8");
        boolean flag = false;
        try {
            File localFile = new File(localpath + "/" + filename);
            OutputStream os = new FileOutputStream(localFile);
            flag =  downloadFile(pathname, filename, os);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public  boolean downloadFile( String pathname, String filename,  OutputStream outputStream){
        boolean flag = false;
        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding("UTF-8");
        try {
            //连接FTP服务器
            ftpClient.connect(hostname, port);
            //登录FTP服务器
            ftpClient.login(username, password);
            //验证FTP服务器是否登录成功
            int replyCode = ftpClient.getReplyCode();
            if(!FTPReply.isPositiveCompletion(replyCode)){
                return flag;
            }
            //切换FTP目录
            Boolean isExistDir = ftpClient.changeWorkingDirectory(pathname);
            logger.info(isExistDir);

            FTPFile[] ftpFiles = ftpClient.listFiles();
            logger.info(ftpFiles.length);
            for(FTPFile file : ftpFiles){
                if(filename.equalsIgnoreCase(file.getName())){
                    ftpClient.retrieveFile(file.getName(), outputStream);
                }
            }
            ftpClient.logout();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(ftpClient.isConnected()){
                try {
                    ftpClient.logout();
                } catch (IOException e) {

                }
            }
        }
        return flag;
    }
}
