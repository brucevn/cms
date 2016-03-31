package com.cms.models;

import org.apache.struts.action.ActionForm;

public class CmsFolder extends ActionForm{
    private int folderId;
    private int parentId;
    private String folderName;
    private String owner;
    private int isPublic=1;
    public CmsFolder() {
        super();
    }
    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderName() {
        return folderName;
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

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getParentId() {
        return parentId;
    }
}
