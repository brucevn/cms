package com.cms.mappers;

import java.util.List;
import com.cms.models.CmsProperty;

import org.apache.ibatis.session.RowBounds;

public interface cmsPropertyMapper {
   int deleteProperty(int propertyId);
   void insertProperty(CmsProperty cmsProperty);
   void updateProperty(CmsProperty cmsProperty);
   List<CmsProperty> findProperties(String language, String propertyName,RowBounds rowBounds);
   int getPropertiesNumber(String language, String propertyName);
   int getSearchNumber(String language, String propertyName);
   CmsProperty getPropertyById(int id);
}
