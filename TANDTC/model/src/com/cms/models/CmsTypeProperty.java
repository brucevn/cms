package com.cms.models;

public class CmsTypeProperty {
    private int id;
    private int typeId;    
    private String propertyLabel="";
    private int orderNumber;
    private int isRequired;
    private CmsProperty cmsProperty;
    public CmsTypeProperty() {
        super();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setPropertyLabel(String propertyLabel) {
        this.propertyLabel = propertyLabel;
    }

    public String getPropertyLabel() {
        return propertyLabel;
    }

    public void setCmsProperty(CmsProperty cmsProperty) {
        this.cmsProperty = cmsProperty;
    }

    public CmsProperty getCmsProperty() {
        return cmsProperty;
    }

    public void setIsRequired(int isRequired) {
        this.isRequired = isRequired;
    }

    public int getIsRequired() {
        return isRequired;
    }
}
