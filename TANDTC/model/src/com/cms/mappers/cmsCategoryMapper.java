package com.cms.mappers;

import java.util.List;
import com.cms.models.CmsCategory;
import com.cms.models.CmsItem;

import org.apache.ibatis.session.RowBounds;

public interface cmsCategoryMapper {
    List<CmsCategory> findAllCategories(String language);
    List<CmsCategory> findCategories(String lanaguage,String categoryName,int parentId,RowBounds rowBounds);
    int getFindCategoriesNumber(String lanaguage,String categoryName,int parentId);
    CmsCategory findCategoryById(int categoryId);
    List<CmsItem> findAllItems(int categoryId,RowBounds rowBounds);
    void insertCategory(CmsCategory cmscategory);
    void updateCategory(CmsCategory cmscategory);
    int deleteCategory(int categoryId);
    List<CmsCategory> findAllSubCategories(int id);        
}
