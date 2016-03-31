package com.cms.models;

import java.util.Date;

import org.apache.struts.action.ActionForm;


public class CmsCategory extends ActionForm{    
    private int categoryId;
    private String categoryName;
    private int parentId;
    private String status="active";
    private Date updatedDate;
    private String language;    
    public CmsCategory() {
        super();
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
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
