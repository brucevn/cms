package com.cms.models;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;


public class CmsWorkFollow extends ActionForm {
    private int id;
    private String name;
    private String description;
    private List<CmsWorkFollowStep> steps=new ArrayList<CmsWorkFollowStep>();
    public CmsWorkFollow() {
        super();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setSteps(List<CmsWorkFollowStep> steps) {
        this.steps = steps;
    }

    public List<CmsWorkFollowStep> getSteps() {
        return steps;
    }
}
