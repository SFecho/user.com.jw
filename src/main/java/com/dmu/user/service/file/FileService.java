package com.dmu.user.service.file;

import com.dmu.user.data.file.dao.FileInfoDao;
import com.dmu.user.data.file.model.FileInfo;

import com.dmu.user.service.ftp.IFTPService;
import net.minidev.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Service("fileService")
public class FileService implements IFileService {
    @Resource
    private FileInfoDao fileInfoDao;

    @Resource
    private IFTPService ftpUtil;

    private static final Logger logger = LogManager.getLogger(FileService.class);

    public boolean upLoadService(JSONObject fileInfoJSON, MultipartFile file)  throws IOException {
        boolean retFlag = false;
        //获取文件名字以及存入FTP的目录，文件名
        String originFileName = file.getOriginalFilename();
        String newFileName = fileInfoJSON.getAsString("fileName");
        String dirPath = fileInfoJSON.getAsString("filePath");
        String tmpRootPath = fileInfoJSON.getAsString("tmpRootPath");
        //打开tmp文件夹
        tmpRootPath += "tmp";
        File dir = new File(tmpRootPath);
        //如果没有tmp文件夹，则创建

        if (!dir.exists())
            dir.mkdirs();
        //将传入的文件保存至临时目录中
        File imageFile = new File(dir, originFileName);
        file.transferTo(imageFile);
        long size = 0L;
        //如果保存成功，则将其上传至FTP中,并同步数据库

        String suffixName = originFileName.substring(originFileName.lastIndexOf("."));
        originFileName = newFileName + suffixName;

        fileInfoJSON.put("originFileName", originFileName);
        fileInfoJSON.put("layer", 0);
        if(imageFile.exists()){
            fileInfoJSON.put("size", imageFile.length());
            InputStream is = new FileInputStream(imageFile);
            //上传文件，并判断是否上传成功
            Boolean isSuccess = ftpUtil.uploadFile(
                    dirPath,
                    originFileName ,
                    is);
            is.close();
            if(isSuccess) {
                imageFile.delete();
                //将目录同步到数据库中
                Integer layer = 1;
                int i = 0;
                //将根部的无用斜杠删除
                while(i < dirPath.length() && (dirPath.charAt(i) == '/' || dirPath.charAt(i) == '\\'))
                    i++;
                char prev = '@';

                StringBuffer stringBuffer = new StringBuffer(1 << 6);

                while(i < dirPath.length()){
                    char tmp = dirPath.charAt(i);
                    if(tmp == '\\')
                        tmp = '/';
                    i++;
                    if(tmp == '/') {
                        if(prev == '/')
                            continue;
                        layer++;    //计算文件夹所在层数
                    }
                    stringBuffer.append(tmp);   //将文件夹加入至其中
                    prev = tmp;
                }

                int len = stringBuffer.length();
                if(stringBuffer.charAt(len - 1) == '/'){
                    stringBuffer.deleteCharAt(len - 1);
                    layer--;
                }
                fileInfoJSON.put("layer", layer);
                logger.info(stringBuffer.toString());
                fileInfoJSON.put("filePath",stringBuffer.toString());
                retFlag = true;
            }
        }
        return retFlag;
    }

    @Override
    public List<FileInfo> getFileList(Integer fileParentId) {
        List<FileInfo> list = fileInfoDao.getFileList(fileParentId);
        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
        for(FileInfo fileInfo: list){
            fileInfo.setFormatDate(sdf.format(fileInfo.getDate())); ;
        }
        return list;
    }

    @Override
    public void downloadFile(HttpServletResponse response, Map<String, String> pathMap){
        OutputStream outputStream = null;
        try {
            //下载文件以form的形式
            response.setContentType("multipart/form-data");
            String saveFileName = (String)pathMap.get("filepath");
            response.addHeader("Content-Disposition", "attachment; filename=\"" + saveFileName + "\"");
            outputStream = response.getOutputStream();
            ftpUtil.downloadFile(pathMap.get("dirpath"), pathMap.get("filepath"), outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                logger.info("关闭流异常");
                e.printStackTrace();
            }
        }
    }
}
