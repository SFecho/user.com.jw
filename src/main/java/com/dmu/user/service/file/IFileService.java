package com.dmu.user.service.file;

import com.dmu.user.data.file.model.FileInfo;
import net.minidev.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IFileService {
    public List<FileInfo> getFileList(Integer fileParentId);
    public boolean upLoadService(JSONObject message, MultipartFile file) throws IOException;
    public void downloadFile(HttpServletResponse response, Map<String, String> pathMap);
}
