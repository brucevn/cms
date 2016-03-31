package com.cms.models;

import java.util.Date;

import org.apache.struts.action.ActionForm;


public class CmsPerspective extends ActionForm{
    public static final long serialVersionUID = 1;
    public CmsPerspective() {
        super();
    }
    private int perspectiveId;
    private String perspectiveName;
    private int parentId;
    private String status="active";
    private Date updatedDate;
    private String language;

    public void setPerspectiveId(int perspectiveId) {
        this.perspectiveId = perspectiveId;
    }

    public int getPerspectiveId() {
        return perspectiveId;
    }

    public void setPerspectiveName(String perspectiveName) {
        this.perspectiveName = perspectiveName;
    }

    public String getPerspectiveName() {
        return perspectiveName;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }
}
