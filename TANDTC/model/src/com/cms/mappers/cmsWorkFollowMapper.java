package com.cms.mappers;

import com.cms.models.CmsStepOwner;
import com.cms.models.CmsWorkFollow;
import com.cms.models.CmsWorkFollowStep;

import java.util.List;


public interface cmsWorkFollowMapper {
    CmsWorkFollow getWorkFollowById(int id);

    CmsWorkFollowStep getStepById(int id);

    CmsStepOwner getStepOwnerById(int id);

    List<CmsWorkFollow> getAllWorkFollows(String txtName,int bSearch);

    List<CmsWorkFollowStep> getAllSteps(int workFollowId);

    List<CmsStepOwner> getAllOwners(int stepId);

    void insertWorkFollow(CmsWorkFollow cmsWorkFollow);

    void updateWorkFollow(CmsWorkFollow cmsWorkFollow);

    int deleteWorkFollow(CmsWorkFollow workFollow);

    void insertStep(CmsWorkFollowStep cmsWorkFollowStep);

    void updateStep(CmsWorkFollowStep cmsWorkFollowStep);

    int deleteStep(CmsWorkFollowStep step);

    void insertStepOwner(CmsStepOwner cmsStepOwner);

    void updateStepOwner(CmsStepOwner cmsStepOwner);

    int deleteStepOwner(CmsStepOwner cmsStepOwner);
}
