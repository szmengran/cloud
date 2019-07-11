package com.suntak.cloud.recruitment.entity;
/** 
 * @Package com.suntak.cloud.recruitment.entity 
 * @Description: 文件信息
 * @date Jul 9, 2019 4:50:00 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class FileInfo {
    private String fileUrl;
    private String filename;
    private String size;
    private String id;
    public String getFileUrl() {
        return fileUrl;
    }
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
}
