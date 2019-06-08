package com.dmu.user.service.ftp;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

public interface IFTPService {
    public  boolean uploadFile(String pathname, String fileName, InputStream inputStream);
    public  boolean uploadFileFromProduction(String pathname, String filename, String originfilename);
    public  boolean uploadFileFromProduction(String pathname, String originfilename);
    public  boolean deleteFile(String pathname, String filename);
    public  boolean downloadFile(String pathname, String filename, String localpath);
    public  boolean downloadFile( String pathname, String filename,  OutputStream outputStream);
}
