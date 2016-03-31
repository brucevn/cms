package com.cms.models;


import com.cms.utils.CmsConstants;

import java.util.Date;

import org.apache.struts.action.ActionForm;


public class CmsItem extends ActionForm{

    private int itemId;
    private String title;
    private String content;
    private int categoryId;
    private int typeId;
    private int masterId;
    private int version;
    private Date publishDate;
    private String owner;
    private Date createdDate;
    private String updater;
    private Date updatedDate;
    private String status= CmsConstants.DRAFT;
    private String statusValue;
    private String language;

    public CmsItem() {
        super();
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public String getStatusValue() {
        return CmsConstants.itemStatusValue(this.status);
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getPublishDate() {
        return publishDate;
    }
}
