package com.dmu.user.service.content;


import com.dmu.user.data.content.model.Content;
import net.minidev.json.JSONObject;

import java.util.List;
import java.util.Map;

public interface IContentService {
    public Map<String, String> getPath(String fileName);
    public int writePath(String dirPath, String newFileName, String originFileName, int layer, long size);
    public List<Content> getRootDirInfo();
    public List<Content> getContentList(JSONObject object);
}
