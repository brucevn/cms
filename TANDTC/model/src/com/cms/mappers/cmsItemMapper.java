package com.cms.mappers;

import com.cms.models.CmsFile;
import com.cms.models.CmsItem;

import com.cms.models.CmsItemFiles;
import com.cms.models.CmsItemPerspective;
import com.cms.models.CmsPerspective;
import com.cms.models.CmsPropertyValue;

import java.util.List;

import java.util.Map;

import org.apache.ibatis.session.RowBounds;

public interface cmsItemMapper { 
    void updateItem(CmsItem item);
    void insertItem(CmsItem item);
    int deleteItem(int id);
    List<CmsItem> findAllItems(String language,String title,String owner,int categoryId,int typeId,int perspectiveId,String fromDate,String toDate,RowBounds rowBounds);
    int getFindAllItemsNumber(String language,String title,String owner,int categoryId,int typeId,int perspectiveId,String fromDate,String toDate);
    CmsItem getItemById(int id);
    int getMasterId();
    CmsPropertyValue getPropertyNumberValue(int itemId,int propertyId);
    void insertPropertyNumberValue(CmsPropertyValue propertyValue);
    void updatePropertyNumberValue(CmsPropertyValue propertyValue);
    CmsPropertyValue getPropertyTextValue(int itemId,int propertyId);
    void insertPropertyTextValue(CmsPropertyValue propertyValue);
    void updatePropertyTextValue(CmsPropertyValue propertyValue);
    CmsPropertyValue getPropertyLongTextValue(int itemId,int propertyId);
    void insertPropertyLongTextValue(CmsPropertyValue propertyValue);
    void updatePropertyLongTextValue(CmsPropertyValue propertyValue);
    CmsPropertyValue getPropertySimpleDateValue(int itemId,int propertyId);
    CmsPropertyValue getPropertyFullDateValue(int itemId,int propertyId);
    void insertPropertyDateValue(CmsPropertyValue propertyValue);
    void updatePropertyDateValue(CmsPropertyValue propertyValue);
    int deleteAllItemPerspectives(int itemId);
    int deleteItemPerspective(int id);
    void insertItemPerspective(CmsItemPerspective item);
    List<CmsPerspective> getAllItemPerspectives(int itemId);
    List<CmsPerspective> getSelectablePerspectives(Map<String,Object> map);
    int deleteAllItemFiles(int itemId);
    int deleteItemFile(int id);
    void insertItemFile(CmsItemFiles item);
    List<CmsFile> getAllItemFiles(int itemId);
    List<String> getAllPropertyNames(int typeId);
    int deletePropertyNumberValue(int itemId);
    int deletePropertyTextValue(int itemId);
    int deletePropertyLongTextValue(int itemId);
    int deletePropertyDateValue(int itemId);
}
