package com.cms.mappers;

import com.cms.models.CmsItemUserRole;

import java.util.List;
import com.cms.models.CmsPerspective;
import com.cms.models.CmsProperty;
import com.cms.models.CmsType;

import com.cms.models.CmsType;

import com.cms.models.CmsTypeProperty;

import com.cms.models.CmsUserGroup;

import org.apache.ibatis.session.RowBounds;

public interface cmsTypeMapper{
    int deleteType(int typeId);
    int removeProperties(int typeId);
    void insertType(CmsType type);
    void insertTypeProperty(CmsTypeProperty typeProperty);
    void insertDefaultProperty(CmsTypeProperty typeProperty);
    void updateType(CmsType type);
    List<CmsType> findTypes(String language, String typeName,RowBounds rowBounds);
    List<CmsType> getAllTypes(String language);
    int getTypesNumber(String language, String typeName);
    CmsType getTypeById(int id);
    List<CmsTypeProperty> getItemPropertiesByType(int typeId);
    int getOrder(int typeId);
    int removeProperty(int typeId,int propertyId);
    int removePropertyById(int id);  
    List<CmsProperty> getPropertiesList(int typeId);
    int updateOrder(int id,int order);
    int updateRequired(int id,int require);
    CmsTypeProperty getUpTypeProperty(CmsTypeProperty typeProperty);
    CmsTypeProperty getDownTypeProperty(CmsTypeProperty typeProperty);
    CmsTypeProperty getTypePropertyById(int id);
    List<CmsItemUserRole> getAllItemRole(CmsItemUserRole cmsItemUserRole);
    void inserItemRole(CmsItemUserRole cmsItemUserRole);
    int removeItemRole(CmsItemUserRole cmsItemUserRole);
}
