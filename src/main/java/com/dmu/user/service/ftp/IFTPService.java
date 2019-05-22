package com.dmu.user.service.ftp;

import java.io.InputStream;

public interface IFTPService {
    public  boolean uploadFile(String hostname, int port, String username, String password, String pathname, String fileName, InputStream inputStream);
    public  boolean uploadFileFromProduction(String hostname, int port, String username, String password, String pathname, String filename, String originfilename);
    public  boolean uploadFileFromProduction(String hostname, int port, String username, String password, String pathname, String originfilename);
    public  boolean deleteFile(String hostname, int port, String username, String password, String pathname, String filename);
    public  boolean downloadFile(String hostname, int port, String username, String password, String pathname, String filename, String localpath);
}
