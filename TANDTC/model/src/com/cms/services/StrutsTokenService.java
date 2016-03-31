package com.cms.services;


import com.cms.mappers.strutsTokenMapper;
import com.cms.models.StrutsToken;
import com.cms.utils.MyBatisSqlSessionFactory;

import org.apache.ibatis.session.SqlSession;


public class StrutsTokenService {
    public StrutsTokenService() {
        super();
    }
    public StrutsToken insertToken(StrutsToken strutsToken) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            strutsTokenMapper mapper =
                sqlSession.getMapper(strutsTokenMapper.class);
            mapper.insertStrutsToken(strutsToken);
            sqlSession.commit();
            return strutsToken;
        }catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
                throw new RuntimeException(e.getCause());
        } finally {
            sqlSession.close();
        }
    }
    
    public StrutsToken getStrutsToken(int id) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            strutsTokenMapper mapper =
                sqlSession.getMapper(strutsTokenMapper.class);            
            return mapper.getStrutsToken(id);
        } finally {
            sqlSession.close();
        }
    }
    
    public StrutsToken updateStrutsToken(StrutsToken strutsToken) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            strutsTokenMapper mapper =
                sqlSession.getMapper(strutsTokenMapper.class);            
            mapper.updateStrutsToken(strutsToken);
            return strutsToken;
        }catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
                throw new RuntimeException(e.getCause());
        } finally {
            sqlSession.close();
        }
    }
    
    public boolean deleteStrutsToken(int id) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            strutsTokenMapper mapper =
                sqlSession.getMapper(strutsTokenMapper.class);            
            int count = mapper.deleteStrutsToken(id);
            sqlSession.commit();
            return count>0;
        } catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
                throw new RuntimeException(e.getCause());
        }finally {
            sqlSession.close();
        }
    }
}
