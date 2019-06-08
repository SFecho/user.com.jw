package com.dmu.user.data.file.dao;

import com.dmu.user.data.file.model.FileInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileInfoDao {
    public List<FileInfo> getFileList(@Param("parentId") Integer fileParentId);
}
