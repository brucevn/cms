package com.cms.struts.filebrowser;

import org.apache.struts.action.ActionForm;

public class CmsFileBrowserForm extends ActionForm {
    private int id;
    private int dir;
    private int page;
    private String name;
    private String owner;
    private String folderName;
    private String type;
    public CmsFileBrowserForm() {
        super();
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getDir() {
        return dir;
    }

    public void setPage(int _page) {
        this.page = _page;
    }

    public int getPage() {
        return page;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

