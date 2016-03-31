package com.cms.models;

public class CmsItemPerspective {
    private int id;
    private int itemId;
    private int perspectiveId;
    private int orderNumber;
    public CmsItemPerspective() {
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

    public void setPerspectiveId(int perspectiveId) {
        this.perspectiveId = perspectiveId;
    }

    public int getPerspectiveId() {
        return perspectiveId;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }
}
