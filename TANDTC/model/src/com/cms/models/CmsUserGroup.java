package com.cms.models;

public class CmsUserGroup {
    private String id;
    private String name;
    private String extension;
    public CmsUserGroup() {
        super();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
