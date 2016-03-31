package com.cms.mappers;

import java.util.List;
import com.cms.models.CmsPerspective;
import com.cms.models.CmsItem;

import org.apache.ibatis.session.RowBounds;

public interface cmsPerspectiveMapper {
    List<CmsPerspective> findAllPerspectives(String language);
    List<CmsPerspective> findPerspectives(String language,String perspectiveName,int parentId,RowBounds rowBounds);
    int getPerspectivesNumber(String language,String perspectiveName,int parentId);
    CmsPerspective findPerspectiveById(int perspectiveId);
    int findAllItemsNumber(int persepectiveId);
    List<CmsItem> findAllItems(int perspectiveId,RowBounds rowBounds);
    void insertPerspective(CmsPerspective cmsperspective);
    void updatePerspective(CmsPerspective cmsperspective);
    int deletePerspective(int perspectiveId);
    List<CmsPerspective> findAllSubPerspectives(int id);
}
