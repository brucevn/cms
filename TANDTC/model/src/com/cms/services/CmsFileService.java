package com.cms.services;


import com.cms.mappers.cmsFileMapper;
import com.cms.models.CmsFile;
import com.cms.models.CmsFolder;
import com.cms.utils.MyBatisSqlSessionFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;


public class CmsFileService{
    private List<Integer> idList = new ArrayList<Integer>();
    public List<CmsFile> getFiles(Map<String,Object> map,int offset,int limit){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsFileMapper mapper =
                sqlSession.getMapper(cmsFileMapper.class);
            RowBounds rowBounds = new RowBounds(offset, limit);
            return mapper.getFiles(map,rowBounds);
        } finally {
            sqlSession.close();
        }
    }
    public int countFiles(Map<String,Object> map){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsFileMapper mapper =
                sqlSession.getMapper(cmsFileMapper.class);
            return mapper.countFiles(map);
        } finally {
            sqlSession.close();
        }
    }
    public boolean deleteFile(int fileId){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsFileMapper mapper =
                sqlSession.getMapper(cmsFileMapper.class);
            int count = mapper.isFileInUsed(fileId);
            int delete=0;
            if(count==0){
                delete=mapper.deleteFile(fileId);
            }
            sqlSession.commit();
            return delete>0;
        }catch(Exception e){
            sqlSession.rollback();
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }finally {
            sqlSession.close();
        }
    }
    public int deleteFolder(int folderId){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsFileMapper mapper =
                sqlSession.getMapper(cmsFileMapper.class);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("ids", getDescendents(folderId));
            map.put("name","");
            int count = mapper.countFiles(map);            
            int delete=0;
            if(count==0){
                delete = mapper.deleteFolder(folderId);
            }
            sqlSession.commit();
            return delete;
        }catch(Exception e){
            sqlSession.rollback();
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }finally {
            sqlSession.close();
        }
    }
    public CmsFile insertFile(CmsFile file){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsFileMapper mapper =
                sqlSession.getMapper(cmsFileMapper.class);
            mapper.insertFile(file);
            sqlSession.commit();
            return file;
        }catch(Exception e){
            sqlSession.rollback();
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }finally {
            sqlSession.close();
        }
    }
    public CmsFolder insertFolder(CmsFolder folder){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsFileMapper mapper =
                sqlSession.getMapper(cmsFileMapper.class);
            List<CmsFolder> folders = mapper.getFolderByName(folder);
            CmsFolder newFolder = folder;
            if(folders.size()>0){
                newFolder = folders.get(0);
                return newFolder;
            }else{
                mapper.insertFolder(folder);
                sqlSession.commit();
                return folder;
            }            
        }catch(Exception e){
            sqlSession.rollback();
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }finally {
            sqlSession.close();
        }
    }
    public CmsFile getFile(int fileId){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsFileMapper mapper =
                sqlSession.getMapper(cmsFileMapper.class);
            return mapper.getFile(fileId);
        }finally {
            sqlSession.close();
        }
    }
    public CmsFolder getFolder(int folderId){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsFileMapper mapper =
                sqlSession.getMapper(cmsFileMapper.class);
            return mapper.getFolder(folderId);
        }finally {
            sqlSession.close();
        }
    }
    public List<CmsFolder> getSubFolders(int folderId){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsFileMapper mapper =
                sqlSession.getMapper(cmsFileMapper.class);
            return mapper.getSubFolders(folderId);
        }finally {
            sqlSession.close();
        }
    }
    public List getDescendents(int folderId){        
        idList.add(folderId);
        List<CmsFolder> list = getSubFolders(folderId);
        if(list.size()==0){
            return idList;
        }else{            
            for(int i=0;i<list.size();i++){
                CmsFolder folder = list.get(i);
                getDescendents(folder.getFolderId());
            }
        } 
        return idList;
    }
    public boolean isFileInUsed(int fileId){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsFileMapper mapper =
                sqlSession.getMapper(cmsFileMapper.class);
            int count = mapper.isFileInUsed(fileId);
            return count>0;
        }finally {
            sqlSession.close();
        }
    }
    public static void main(String[] args){
        CmsFileService service = new CmsFileService();
        System.out.println(service.deleteFolder(21));
    }
}