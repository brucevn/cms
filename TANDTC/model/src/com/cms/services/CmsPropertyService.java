package com.cms.services;


import com.cms.mappers.cmsPropertyMapper;
import com.cms.models.CmsProperty;
import com.cms.utils.MyBatisSqlSessionFactory;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;


public class CmsPropertyService {
    public CmsPropertyService() {
        super();
    }
    public List<CmsProperty> findProperties(String lang,String name,int offset,int limit) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsPropertyMapper mapper =
                sqlSession.getMapper(cmsPropertyMapper.class);
            RowBounds rowBounds = new RowBounds(offset, limit);
            return mapper.findProperties(lang,name,rowBounds);
        } finally {
            sqlSession.close();
        }
    }
    public int getPropertiesNumber(String lang,String name) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsPropertyMapper mapper =
                sqlSession.getMapper(cmsPropertyMapper.class);            
            return mapper.getPropertiesNumber(lang,name);
        } finally {
            sqlSession.close();
        }
    }
    public CmsProperty getPropertyById(int id) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsPropertyMapper mapper =
                sqlSession.getMapper(cmsPropertyMapper.class);            
            return mapper.getPropertyById(id);
        } finally {
            sqlSession.close();
        }
    }
    public CmsProperty insertProperty(CmsProperty property){        
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsPropertyMapper mapper =
                sqlSession.getMapper(cmsPropertyMapper.class);
            mapper.insertProperty(property);
            sqlSession.commit();
            return property;
        }catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
                throw new RuntimeException(e.getCause());
        }
        finally {
            sqlSession.close();
        }
    }
    public CmsProperty updateProperty(CmsProperty property){        
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsPropertyMapper mapper =
                sqlSession.getMapper(cmsPropertyMapper.class);
            mapper.updateProperty(property);
            sqlSession.commit();
            return property;
        }catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
                throw new RuntimeException(e.getCause());
        }
        finally {
            sqlSession.close();
        }
    }
    public boolean deleteProperty(int id) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsPropertyMapper mapper =
                sqlSession.getMapper(cmsPropertyMapper.class);
                    int count = mapper.deleteProperty(id);
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
