package com.dmu.user.controller;

import com.dmu.user.data.content.model.Content;
import com.dmu.user.data.file.model.FileInfo;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/io")
public class FileController extends BaseController{
    private static final Logger logger = LogManager.getLogger(FileController.class);

    @GetMapping(value = "download")
    void doDownLoadFile(@RequestParam("filename")String filename,  HttpServletResponse response) {
        logger.info(filename);
        //得到文件对应的路径信息
        Map<String, String> pathMap = contentService.getPath(filename);
        fileService.downloadFile(response, pathMap);
    }

    @PostMapping(value = "/upload", produces = "application/json; charset=utf-8")
    String upLoadFile(@RequestParam(value = "file", required = true) MultipartFile file, HttpServletRequest request, @RequestParam(value = "fileInfo")String fileInfo) throws IOException {
        String retFlag = failure;
        JSONObject fileInfoJSON = (JSONObject)JSONValue.parse(fileInfo);
        fileInfoJSON.put("tmpRootPath",request.getSession().getServletContext().getRealPath("/"));
        boolean uploadFlag = fileService.upLoadService(fileInfoJSON, file);

        if(uploadFlag){
            int writeDB = contentService.writePath(
                    fileInfoJSON.getAsString("filePath"),
                    fileInfoJSON.getAsString("fileName"),
                    fileInfoJSON.getAsString("originFileName"),
                    (Integer)fileInfoJSON.getAsNumber("layer"),
                    (Long)fileInfoJSON.getAsNumber("size")
            );
            if(writeDB == 1)
                retFlag = success;
        }
        return retFlag;
    }

    @PostMapping(value = "/getrootdir", produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
    List<Content> doGetRootDirInfo(@RequestBody JSONObject message, HttpServletRequest request){
        List<Content> rootList = contentService.getRootDirInfo();
        return rootList;
    }

    @PostMapping(value = "getcontent", produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
    List<Content> doGetContentList(@RequestBody JSONObject message, HttpServletRequest request){
        List<Content> list = contentService.getContentList(message);
        return list;
    }

    @PostMapping(value = "getfiles")
    List<FileInfo> doGetFileList(@RequestBody JSONObject message, HttpServletRequest request) throws ParseException {
        Integer parentId = (Integer)message.getAsNumber("id");
        List<FileInfo> list = fileService.getFileList(parentId);
        return list;
    }


}
