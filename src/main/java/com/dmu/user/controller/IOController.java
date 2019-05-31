package com.dmu.user.controller;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/io")
public class IOController extends BaseController{
    private static final Logger logger = LogManager.getLogger(LoginController.class);

    @PostMapping(value = "/getpath", produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
    String getFilePath(@RequestBody JSONObject message, HttpServletRequest request){
        String path = contentService.getPath("ddd");

        logger.debug(path);

        return success;
    }


    @PostMapping(value = "/upload", produces = "application/json; charset=utf-8")
    String upLoadFile(@RequestParam(value = "file", required = true) MultipartFile file, HttpServletRequest request, @RequestParam(value = "fileInfo")String fileInfo) throws IOException {
        JSONObject fileInfoJSON = (JSONObject)JSONValue.parse(fileInfo);

        String flag = failure;
        //得到当前项目的根目录
        String tmpRootPath = request.getSession().getServletContext().getRealPath("/");
        //打开tmp文件夹
        tmpRootPath += "tmp";
        //获取文件名字以及存入FTP的目录，文件名
        String originFileName = file.getOriginalFilename();
        String newFileName = fileInfoJSON.getAsString("fileName");
        String dirPath = fileInfoJSON.getAsString("filePath");

        File dir = new File(tmpRootPath);
        //如果没有tmp文件夹，则创建
        if (!dir.exists())
            dir.mkdirs();
        //将传入的文件保存至临时目录中
        File imageFile = new File(dir, originFileName);
        file.transferTo(imageFile);
        //如果保存成功，则将其上传至FTP中,并同步数据库
        if(imageFile.exists()){
            InputStream is = new FileInputStream(imageFile);
            //上传文件，并判断是否上传成功
            Boolean isSuccess = ftpService.uploadFile(
                    "127.0.0.1",
                    21,
                    "guest",
                    "guest",
                    dirPath,
                    originFileName ,
                    is);
            if(isSuccess) {
                imageFile.delete();
                is.close();
                //将目录同步到数据库中
                int layer = 1;
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

                dirPath = stringBuffer.toString();
                logger.info(dirPath);
                contentService.writePath(dirPath, newFileName, originFileName, layer);
                flag = success;
            }

        }
        return flag;
    }

}
