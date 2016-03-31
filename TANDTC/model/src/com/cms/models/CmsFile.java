package com.cms.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;


@JsonIgnoreProperties({"content"})
public class CmsFile {
    private int fileId;
    private String fileName;
    private int fileSize;
    private String fileType;
    private int isImage;    
    private String owner; 
    private int folderId;
    private String fileExt;
    private byte[] content;
    private int isPublic;
    private Date createdDate;
    private String shortName;
 
//getters and setters...

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public byte[] getContent() {
        return content;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getFileId() {
        return fileId;
    }

    public void setIsImage(int isImage) {
        this.isImage = isImage;
    }

    public int getIsImage() {
        return isImage;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void setIsPublic(int isPublic) {
        this.isPublic = isPublic;
    }

    public int getIsPublic() {
        return isPublic;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    public String getFileExt() {
        return fileExt;
    }
    public void isImage(){
        String[] extArray = {"jpg","jpeg","png","tif","tiff","gif","bmp"};
        for(int i=0;i<extArray.length;i++){
            if(extArray[i].equalsIgnoreCase(getFileExt())){
                setIsImage(1);
                return;
            }
        }
        setIsImage(0);
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return fileName.length()>15?fileName.substring(0,15)+"...":fileName;
    }
}
