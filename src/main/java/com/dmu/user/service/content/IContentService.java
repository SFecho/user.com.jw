package com.dmu.user.service.content;


public interface IContentService {
    public String getPath(String fileName);
    public void writePath(String dirPath, String newFileName, String originFileName, int layer);

}
