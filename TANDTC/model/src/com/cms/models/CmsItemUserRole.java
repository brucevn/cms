package com.cms.models;

import com.cms.services.CmsLdapService;

public class CmsItemUserRole {
    private int id;
    private String userGroup;
    private String name;
    private int isUser;
    private String userRole;
    private int typeId;
    public CmsItemUserRole() {
        super();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setIsUser(int isUser) {
        this.isUser = isUser;
    }

    public int getIsUser() {
        return isUser;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return CmsLdapService.getUserGroupName(Integer.valueOf(this.userGroup),this.isUser);
    }
}
