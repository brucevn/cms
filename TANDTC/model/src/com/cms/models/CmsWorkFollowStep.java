package com.cms.models;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;


public class CmsWorkFollowStep extends ActionForm {
    private int id;
    private CmsWorkFollow workFollow;
    private String stepName;
    private int orderNumber;
    private List<CmsStepOwner> owners = new ArrayList<CmsStepOwner>();
    public CmsWorkFollowStep() {
        super();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setWorkFollow(CmsWorkFollow workFollow) {
        this.workFollow = workFollow;
    }

    public CmsWorkFollow getWorkFollow() {
        return workFollow;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public String getStepName() {
        return stepName;
    }

    public void setOwners(List<CmsStepOwner> owners) {
        this.owners = owners;
    }

    public List<CmsStepOwner> getOwners() {
        return owners;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }
}
