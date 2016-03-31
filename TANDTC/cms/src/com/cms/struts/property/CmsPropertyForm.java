package com.cms.struts.property;

import com.cms.models.CmsProperty;
import com.cms.utils.CmsConstants;

public class CmsPropertyForm extends CmsProperty{
    private int page=1;
    private String searchText;
    public CmsPropertyForm() {
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
