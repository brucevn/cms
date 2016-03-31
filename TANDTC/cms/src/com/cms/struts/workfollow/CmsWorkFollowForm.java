package com.cms.struts.workfollow;

import com.cms.models.CmsWorkFollow;

import org.apache.struts.action.ActionForm;

public class CmsWorkFollowForm extends CmsWorkFollow{
    private String txtName;
    public CmsWorkFollowForm() {
        super();
    }

    public void setTxtName(String txtName) {
        this.txtName = txtName;
    }

    public String getTxtName() {
        return txtName;
    }
}
