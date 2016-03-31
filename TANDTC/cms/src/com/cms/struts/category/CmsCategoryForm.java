package com.cms.struts.category;

import com.cms.models.CmsCategory;

import com.cms.utils.CmsConstants;

import org.apache.struts.action.ActionForm;

public class CmsCategoryForm extends CmsCategory{
    private int page=1;
    private String searchText;    
    public CmsCategoryForm() {
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
}
