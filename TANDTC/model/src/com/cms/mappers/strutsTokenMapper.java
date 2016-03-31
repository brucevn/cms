package com.cms.mappers;

import java.util.List;
import com.cms.models.CmsPerspective;
import com.cms.models.CmsType;

import com.cms.models.CmsType;

import com.cms.models.CmsTypeProperty;

import com.cms.models.StrutsToken;

import org.apache.ibatis.session.RowBounds;

public interface strutsTokenMapper{
    void insertStrutsToken(StrutsToken strutsToken);
    StrutsToken getStrutsToken(int id);
    void updateStrutsToken(StrutsToken strutsToken);
    int deleteStrutsToken(int id);
}
