package com.cms.struts.type;

import com.cms.models.CmsType;
import com.cms.models.CmsProperty;
import com.cms.utils.CmsConstants;

public class CmsTypeForm extends CmsType{
    private int page=1;
    private String searchText;
    private int propertyId;
    private int typePropertyId;
    private int isRequired;
    private int roleId;
    private int isUser;
    private String userGroup;
    private String userRole;
    public CmsTypeForm() {
        this.setLanguage(CmsConstants.VIETNAMESE);
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setTypePropertyId(int typePropertyId) {
        this.typePropertyId = typePropertyId;
    }

    public int getTypePropertyId() {
        return typePropertyId;
    }

    public void setIsRequired(int isRequired) {
        this.isRequired = isRequired;
    }

    public int getIsRequired() {
        return isRequired;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setIsUser(int isUser) {
        this.isUser = isUser;
    }

    public int getIsUser() {
        return isUser;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserRole() {
        return userRole;
    }
}
