package com.EMS._abstract;

public interface IOManager
{
    public void setPath(String path);
    public String getPath();
    public void setFileName(String filename);
    public String getFileName();
    public void writeToFile(String content);
    public String readFromFile();
    public void save();
}