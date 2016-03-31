package com.cms.mappers;

import com.cms.models.CmsItemPerspective;

import java.util.List;

public interface cmsItemPerspectiveMapper {
    int deleteAllItemPerspectives(int itemId);
    int deleteItemPerspectives(int id);
    CmsItemPerspective insertItemPerspectives(CmsItemPerspective item);
    List<CmsItemPerspective> getAllItemPerspectives(int itemId);
}
