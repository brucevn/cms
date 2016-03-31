package com.cms.models;

import org.apache.struts.action.ActionForm;

public class CmsType extends ActionForm{
    private int typeId;
    private String typeName;
    private String typeDescription;
    private byte usePerspective;
    private byte useCategory;
    private String titleLabel;    
    private String contentLabel;
    private String status="active";
    private String language;
    public CmsType() {
        super();
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setUsePerspective(byte usePerspective) {
        this.usePerspective = usePerspective;
    }

    public byte getUsePerspective() {
        return usePerspective;
    }

    public void setUseCategory(byte useCategory) {
        this.useCategory = useCategory;
    }

    public byte getUseCategory() {
        return useCategory;
    }

    public void setTitleLabel(String titleLable) {
        this.titleLabel = titleLable;
    }

    public String getTitleLabel() {
        return titleLabel;
    }

    public void setContentLabel(String contentLable) {
        this.contentLabel = contentLable;
    }

    public String getContentLabel() {
        return contentLabel;
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
}
