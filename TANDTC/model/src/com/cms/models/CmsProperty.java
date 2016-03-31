package com.cms.models;


import com.cms.utils.AccentRemover;

import java.util.Date;

import org.apache.struts.action.ActionForm;


public class CmsProperty extends ActionForm{
    private int propertyId;
    private String propertyLabel;
    private String propertyName="";
    private String propertyType="";
    private Date updatedDate;
    private int resourceId=0;
    private String language;
                    
    public CmsProperty() {
        super();
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyLabel(String propertyLabel) {
        this.propertyLabel = propertyLabel.trim();
    }

    public String getPropertyLabel() {
        return propertyLabel;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType.trim();
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }
    public void propertyLableToName(){
        if(getPropertyLabel()!=null){
           this.propertyName= AccentRemover.removeAccent(getPropertyLabel().toLowerCase()).trim().replaceAll(" ","_");
        }
    }
}
