package com.cms.models;

import com.cms.services.CmsLdapService;

import org.apache.struts.action.ActionForm;

public class CmsStepOwner extends ActionForm {
    @SuppressWarnings("compatibility:-3620186542196459670")
    private static final long serialVersionUID = 1L;
    private int id;
    private CmsWorkFollowStep step;
    private int owner;
    private String name;
    private String ownerType;
    private int order;
    
    public CmsStepOwner() {
        super();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setStep(CmsWorkFollowStep step) {
        this.step = step;
    }

    public CmsWorkFollowStep getStep() {
        return step;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return CmsLdapService.getUserGroupName(owner, ownerType.equals("user") ?
                1:0);
    }
}
