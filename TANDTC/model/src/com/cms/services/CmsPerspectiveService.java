package com.cms.services;

import com.cms.mappers.cmsPerspectiveMapper;
import com.cms.models.CmsItem;
import com.cms.models.CmsPerspective;
import com.cms.utils.MyBatisSqlSessionFactory;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;


public class CmsPerspectiveService {
    public CmsPerspectiveService() {
        super();
    }

    public List<CmsPerspective> findAllPerspectives(String language) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsPerspectiveMapper cmsPerspectiveMapper =
                sqlSession.getMapper(cmsPerspectiveMapper.class);
            return cmsPerspectiveMapper.findAllPerspectives(language);
        } finally {
            sqlSession.close();
        }
    }
    public List<CmsPerspective> findPerspectives(String language,String name,int parentId,int offset,int limit) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsPerspectiveMapper cmsPerspectiveMapper =
                sqlSession.getMapper(cmsPerspectiveMapper.class);
            RowBounds rowBounds = new RowBounds(offset, limit);
            return cmsPerspectiveMapper.findPerspectives(language,name,parentId,rowBounds);
        } finally {
            sqlSession.close();
        }
    }
    
    public int getPerspectivesNumber(String language,String name,int parentId) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsPerspectiveMapper cmsPerspectiveMapper =
                sqlSession.getMapper(cmsPerspectiveMapper.class);            
            return cmsPerspectiveMapper.getPerspectivesNumber(language,name,parentId);
        } finally {
            sqlSession.close();
        }
    }
    public List<CmsItem> findAllItems(int id,int offset,int limit) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsPerspectiveMapper cmsPerspectiveMapper =
                sqlSession.getMapper(cmsPerspectiveMapper.class);
            RowBounds rowBounds = new RowBounds(offset, limit);
            int total = cmsPerspectiveMapper.findAllItemsNumber(id);
            if(total<limit){
                rowBounds = new RowBounds(offset,total);
            }
            return cmsPerspectiveMapper.findAllItems(id,rowBounds);
        } finally {
            sqlSession.close();
        }
    }
    public CmsPerspective getPerspectiveById(int id) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsPerspectiveMapper cmsPerspectiveMapper =
                sqlSession.getMapper(cmsPerspectiveMapper.class);            
            return cmsPerspectiveMapper.findPerspectiveById(id);
        } finally {
            sqlSession.close();
        }
    }
    
    
    public CmsPerspective insertPerspective(CmsPerspective perspective){        
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsPerspectiveMapper mapper =
                sqlSession.getMapper(cmsPerspectiveMapper.class);
            mapper.insertPerspective(perspective);
            sqlSession.commit();
            return perspective;
        }catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
                throw new RuntimeException(e.getCause());
        }
        finally {
            sqlSession.close();
        }
    }
    public CmsPerspective updatePerspective(CmsPerspective perspective){        
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsPerspectiveMapper mapper =
                sqlSession.getMapper(cmsPerspectiveMapper.class);
            mapper.updatePerspective(perspective);
            sqlSession.commit();
            return perspective;
        }catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
                throw new RuntimeException(e.getCause());
        }
        finally {
            sqlSession.close();
        }
    }
    public boolean deletePerspective(int id) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsPerspectiveMapper mapper =
                sqlSession.getMapper(cmsPerspectiveMapper.class);
                    int count = mapper.deletePerspective(id);
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
