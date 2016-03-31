package com.cms.services;

import com.cms.mappers.cmsCategoryMapper;
import com.cms.models.CmsCategory;
import com.cms.models.CmsItem;
import com.cms.utils.MyBatisSqlSessionFactory;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;


public class CmsCategoryService {
    public CmsCategoryService() {
        super();
    }

    public List<CmsCategory> findAllCategories(String language) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsCategoryMapper cmsCategoryMapper =
                sqlSession.getMapper(cmsCategoryMapper.class);
            return cmsCategoryMapper.findAllCategories(language);
        } finally {
            sqlSession.close();
        }
    }
    public List<CmsCategory> findCategories(String language,String name,int parentId,int offset,int limit) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsCategoryMapper cmsCategoryMapper =
                sqlSession.getMapper(cmsCategoryMapper.class);
            RowBounds rowBounds = new RowBounds(offset, limit);
            return cmsCategoryMapper.findCategories(language,name,parentId,rowBounds);
        } finally {
            sqlSession.close();
        }
    }
    
    public int getFindCategoriesNumber(String language,String name,int parentId,int offset,int limit) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsCategoryMapper cmsCategoryMapper =
                sqlSession.getMapper(cmsCategoryMapper.class);            
            return cmsCategoryMapper.getFindCategoriesNumber(language,name,parentId);
        } finally {
            sqlSession.close();
        }
    }
    
    public List<CmsCategory> findAllSubCategories(int categoryId) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsCategoryMapper cmsCategoryMapper =
                sqlSession.getMapper(cmsCategoryMapper.class);            
            return cmsCategoryMapper.findAllSubCategories(categoryId);
        } finally {
            sqlSession.close();
        }
    }
    public List<CmsItem> findAllItems(int id,int offset,int limit) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsCategoryMapper cmsCategoryMapper =
                sqlSession.getMapper(cmsCategoryMapper.class);
            RowBounds rowBounds = new RowBounds(offset, limit);
            return cmsCategoryMapper.findAllItems(id,rowBounds);
        } finally {
            sqlSession.close();
        }
    }
    public CmsCategory getCategoryById(int id) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsCategoryMapper cmsCategoryMapper =
                sqlSession.getMapper(cmsCategoryMapper.class);            
            return cmsCategoryMapper.findCategoryById(id);
        } finally {
            sqlSession.close();
        }
    }
    
    
    public CmsCategory insertCategory(CmsCategory category){        
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsCategoryMapper mapper =
                sqlSession.getMapper(cmsCategoryMapper.class);
            mapper.insertCategory(category);
            sqlSession.commit();
            return category;
        }catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
                throw new RuntimeException(e.getCause());
        }
        finally {
            sqlSession.close();
        }
    }
    public CmsCategory updateCategory(CmsCategory category){        
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsCategoryMapper mapper =
                sqlSession.getMapper(cmsCategoryMapper.class);
            mapper.updateCategory(category);
            sqlSession.commit();
            return category;
        }catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
                throw new RuntimeException(e.getCause());
        }
        finally {
            sqlSession.close();
        }
    }
    public boolean deleteCategory(int id) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsCategoryMapper mapper =
                sqlSession.getMapper(cmsCategoryMapper.class);
                    int count = mapper.deleteCategory(id);
                    sqlSession.commit();
                    return count > 0;
            } 
            catch (Exception e) {
                    sqlSession.rollback();
                    e.printStackTrace();
                    throw new RuntimeException(e.getCause());
            }
            finally {
                    sqlSession.close();
            }
    }   
}
