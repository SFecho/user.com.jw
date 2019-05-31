package com.dmu.user.data.content.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ContentDao {
    public List<String> getPath(@Param("name") String params);
    public int writePath(@Param("dirPath")String dirPath, @Param("newFilePath")String newFileName, @Param("originFilePath")String originFileName, @Param("layer")int layer);
}
