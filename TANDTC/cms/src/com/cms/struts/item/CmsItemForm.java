package com.cms.struts.item;

import com.cms.models.CmsItem;

public class CmsItemForm extends CmsItem{
    private int page=1;
    private String searchText; 
    private int category;
    private int perspective;
    private int type;
    private String owner;
    private String fromDate;
    private String toDate;
    public CmsItemForm() {
        super();
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

    public void setCategory(int sCategory) {
        this.category = sCategory;
    }

    public int getCategory() {
        return category;
    }

    public void setPerspective(int sPerspective) {
        this.perspective = sPerspective;
    }

    public int getPerspective() {
        return perspective;
    }

    public void setType(int sType) {
        this.type = sType;
    }

    public int getType() {
        return type;
    }

    public void setOwner(String sOwner) {
        this.owner = sOwner;
    }

    public String getOwner() {
        return owner;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getToDate() {
        return toDate;
    }
}
