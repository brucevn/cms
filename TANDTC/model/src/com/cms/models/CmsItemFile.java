package com.cms.models;

public class CmsItemFile {
    private int id;
    private int itemId;
    private int fileId;
    private int orderNumber;
    public CmsItemFile() {
        super();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getFileId() {
        return fileId;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }
}
