package com.cms.services;


import com.cms.mappers.cmsWorkFollowMapper;
import com.cms.models.CmsStepOwner;
import com.cms.models.CmsWorkFollow;
import com.cms.models.CmsWorkFollowStep;
import com.cms.utils.MyBatisSqlSessionFactory;

import java.util.List;

import org.apache.ibatis.session.SqlSession;


public class CmsWorkFollowService {
    public CmsWorkFollowService() {
        super();
    }
    public CmsWorkFollow getWorkFollowById(int id){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsWorkFollowMapper mapper= sqlSession.getMapper(cmsWorkFollowMapper.class);
            return mapper.getWorkFollowById(id);
        } finally {
            sqlSession.close();
        }
    }
    public CmsWorkFollowStep getStepById(int id){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsWorkFollowMapper mapper= sqlSession.getMapper(cmsWorkFollowMapper.class);
            return mapper.getStepById(id);
        } finally {
            sqlSession.close();
        }
    }
    public CmsStepOwner getStepOwnerById(int id){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsWorkFollowMapper mapper= sqlSession.getMapper(cmsWorkFollowMapper.class);
            return mapper.getStepOwnerById(id);
        } finally {
            sqlSession.close();
        }
    }
    public List<CmsWorkFollow> getAllWorkFollows(String txtName){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsWorkFollowMapper mapper= sqlSession.getMapper(cmsWorkFollowMapper.class);
            return mapper.getAllWorkFollows(txtName,1);
        } finally {
            sqlSession.close();
        }
    }
    public List<CmsWorkFollowStep> getAllSteps(int workFollowId){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsWorkFollowMapper mapper= sqlSession.getMapper(cmsWorkFollowMapper.class);
            return mapper.getAllSteps(workFollowId);
        } finally {
            sqlSession.close();
        }
    }
    public List<CmsStepOwner> getAllOwners(int stepId){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsWorkFollowMapper mapper= sqlSession.getMapper(cmsWorkFollowMapper.class);
            return mapper.getAllOwners(stepId);
        } finally {
            sqlSession.close();
        }
    }
    public CmsWorkFollow insertWorkFollow(CmsWorkFollow cmsWorkFollow){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsWorkFollowMapper mapper= sqlSession.getMapper(cmsWorkFollowMapper.class);
            mapper.insertWorkFollow(cmsWorkFollow);
            sqlSession.commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
        return cmsWorkFollow;
    }
    public CmsWorkFollow updateWorkFollow(CmsWorkFollow cmsWorkFollow){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsWorkFollowMapper mapper= sqlSession.getMapper(cmsWorkFollowMapper.class);
            mapper.updateWorkFollow(cmsWorkFollow);
            sqlSession.commit();
        } catch(Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
        return cmsWorkFollow;
    }
    public boolean deleteWorkFollow(CmsWorkFollow workFollow){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        int count=0;
        try {
            cmsWorkFollowMapper mapper= sqlSession.getMapper(cmsWorkFollowMapper.class);
            count= mapper.deleteWorkFollow(workFollow);
            sqlSession.commit();
        } catch(Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
        return count>0;
    }
    public CmsWorkFollowStep insertStep(CmsWorkFollowStep cmsWorkFollowStep){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsWorkFollowMapper mapper= sqlSession.getMapper(cmsWorkFollowMapper.class);
            mapper.insertStep(cmsWorkFollowStep);
            sqlSession.commit();
        } catch(Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
        return cmsWorkFollowStep;
    }
    public CmsWorkFollowStep updateStep(CmsWorkFollowStep cmsWorkFollowStep){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsWorkFollowMapper mapper= sqlSession.getMapper(cmsWorkFollowMapper.class);
            mapper.updateStep(cmsWorkFollowStep);
            sqlSession.commit();
        } catch(Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
        return cmsWorkFollowStep;
    }
    public boolean deleteStep(CmsWorkFollowStep step){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        int count=0;
        try {
            cmsWorkFollowMapper mapper= sqlSession.getMapper(cmsWorkFollowMapper.class);
            count=mapper.deleteStep(step);
            sqlSession.commit();
        } catch(Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
        return count>0;
    }
    public CmsStepOwner insertStepOwner(CmsStepOwner cmsStepOwner){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();        
        try {
            cmsWorkFollowMapper mapper= sqlSession.getMapper(cmsWorkFollowMapper.class);
            mapper.insertStepOwner(cmsStepOwner);
            sqlSession.commit();
        } catch(Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
        return cmsStepOwner;
    }
    public CmsStepOwner updateStepOwner(CmsStepOwner cmsStepOwner){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();        
        try {
            cmsWorkFollowMapper mapper= sqlSession.getMapper(cmsWorkFollowMapper.class);
            mapper.updateStepOwner(cmsStepOwner);
            sqlSession.commit();
        } catch(Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
        return cmsStepOwner;
    }
    public boolean deleteStepOwner(CmsStepOwner cmsStepOwner){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        int count=0;
        try {
            cmsWorkFollowMapper mapper= sqlSession.getMapper(cmsWorkFollowMapper.class);
            count=mapper.deleteStepOwner(cmsStepOwner);
            sqlSession.commit();
        } catch(Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
        return count>0;
    }
}
