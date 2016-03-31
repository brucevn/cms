package com.cms.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CmsPropertyValue {
    private int id;
    private int itemId;
    private int propertyId;
    private String strValue;
    private int intValue;
    private Date dateValue;
    private List mutiValues = new ArrayList();
    private String name;
    private String propertyType;
    private boolean required;
    private String propertyLabel;
    public CmsPropertyValue() {
        super();
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setStrValue(String value) {
        this.strValue = value;
    }

    public String getStrValue() {
        return strValue;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMutiValues(List values) {
        this.mutiValues = values;
    }

    public List getMutiValues() {
        return mutiValues;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    public Date getDateValue() {
        return dateValue;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isRequired() {
        return required;
    }

    public void setPropertyLabel(String propertyLabel) {
        this.propertyLabel = propertyLabel;
    }

    public String getPropertyLabel() {
        return propertyLabel;
    }
}
