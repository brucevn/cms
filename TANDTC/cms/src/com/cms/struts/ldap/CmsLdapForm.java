package com.cms.struts.ldap;

import com.cms.utils.CmsConstants;

import org.apache.struts.action.ActionForm;

public class CmsLdapForm extends ActionForm {
    @SuppressWarnings("compatibility:1566985801846979195")
    public final static long serialVersionUID = 322323232;
    private String text;
    private int page=1;
    private int start=1;
    private int end=10;    
    private String type="user";    
    public CmsLdapForm() {
        super();
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getStart() {
        return (page-1)*CmsConstants.PAGE_SIZE+1;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getEnd() {
        return page*CmsConstants.PAGE_SIZE;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
