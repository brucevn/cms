package com.cms.struts.perspective;

import com.cms.models.CmsPerspective;
import com.cms.utils.CmsConstants;


public class CmsPerspectiveForm extends CmsPerspective{
    private int page=1;
    private String searchText;    
    public CmsPerspectiveForm() {
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
