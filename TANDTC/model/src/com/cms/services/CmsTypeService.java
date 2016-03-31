package com.cms.services;


import com.cms.mappers.cmsTypeMapper;
import com.cms.models.CmsItemUserRole;
import com.cms.models.CmsProperty;
import com.cms.models.CmsType;
import com.cms.models.CmsTypeProperty;
import com.cms.utils.CmsConstants;
import com.cms.utils.MyBatisSqlSessionFactory;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;


public class CmsTypeService {
    public CmsTypeService() {
        super();
    }
    public List<CmsType> findTypes(String language,String typeName,int offset,int limit) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsTypeMapper mapper =
                sqlSession.getMapper(cmsTypeMapper.class);
            RowBounds rowBounds = new RowBounds(offset,limit);
            return mapper.findTypes(language, typeName, rowBounds);
        } finally {
            sqlSession.close();
        }
    }
    
    public List<CmsType> getAllTypes(String language) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsTypeMapper mapper =
                sqlSession.getMapper(cmsTypeMapper.class);            
            return mapper.getAllTypes(language);
        } finally {
            sqlSession.close();
        }
    }
    
    public List<CmsTypeProperty> getItemPropertiesByType(int typeId){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsTypeMapper mapper =
                sqlSession.getMapper(cmsTypeMapper.class);            
            return mapper.getItemPropertiesByType(typeId);
        } finally {
            sqlSession.close();
        }
    }
    public int getTypesNumber(String language,String typeName) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsTypeMapper mapper =
                sqlSession.getMapper(cmsTypeMapper.class);           
            return mapper.getTypesNumber(language, typeName);
        } finally {
            sqlSession.close();
        }
    }
    public CmsType getTypeById(int id) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsTypeMapper mapper =
                sqlSession.getMapper(cmsTypeMapper.class);           
            return mapper.getTypeById(id);
        } finally {
            sqlSession.close();
        }
    }
    public List<CmsProperty> getPropertiesList(int typeId) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsTypeMapper mapper =
                sqlSession.getMapper(cmsTypeMapper.class);           
            return mapper.getPropertiesList(typeId);
        } finally {
            sqlSession.close();
        }
    }
    
    public int getOrder(int typeId) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsTypeMapper mapper =
                sqlSession.getMapper(cmsTypeMapper.class);           
            return mapper.getOrder(typeId);
        } finally {
            sqlSession.close();
        }
    }
    
    public CmsType insertType(CmsType type){        
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();        
        try {
            cmsTypeMapper mapper =
                sqlSession.getMapper(cmsTypeMapper.class);
            CmsProperty property = new CmsProperty();
            mapper.insertType(type);       
            int order=1;
            CmsTypeProperty typeProperty = new CmsTypeProperty();            
            typeProperty.setOrderNumber(order++);
            typeProperty.setPropertyLabel("");
            property.setPropertyId(CmsConstants.TITLE_ID);
            typeProperty.setCmsProperty(property);
            typeProperty.setTypeId(type.getTypeId());
            typeProperty.setIsRequired(1);
            mapper.insertTypeProperty(typeProperty);            
            property.setPropertyId(CmsConstants.CONTENT_ID);
            typeProperty.setCmsProperty(property);
            typeProperty.setOrderNumber(order++);
            typeProperty.setIsRequired(1);
            mapper.insertTypeProperty(typeProperty);
            property.setPropertyId(CmsConstants.PUBLISHDATE_ID);
            typeProperty.setCmsProperty(property);
            typeProperty.setOrderNumber(order++);
            typeProperty.setIsRequired(1);
            mapper.insertTypeProperty(typeProperty);
            if(type.getUseCategory()==1){                
                property.setPropertyId(CmsConstants.CATEGORY_ID);
                typeProperty.setCmsProperty(property);
                typeProperty.setOrderNumber(order++);
                typeProperty.setIsRequired(1);
                mapper.insertTypeProperty(typeProperty);
            }
            if(type.getUsePerspective()==1){
                property.setPropertyId(CmsConstants.PERSPECTIVE_ID);
                typeProperty.setCmsProperty(property);
                typeProperty.setOrderNumber(order++);
                typeProperty.setIsRequired(1);
                mapper.insertTypeProperty(typeProperty);
            }
            sqlSession.commit();
            return type;
        }catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
                throw new RuntimeException(e.getCause());
        }
        finally {
            sqlSession.close();
        }
    }
    public CmsTypeProperty insertTypeProperty(CmsTypeProperty typeProperty){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();        
        try {
            cmsTypeMapper mapper =
                sqlSession.getMapper(cmsTypeMapper.class);            
            mapper.insertTypeProperty(typeProperty);
            sqlSession.commit();
            return typeProperty;
        }catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
                throw new RuntimeException(e.getCause());
        }
        finally {
            sqlSession.close();
        }
    }
    public CmsType updateType(CmsType type){        
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsTypeMapper mapper =
                sqlSession.getMapper(cmsTypeMapper.class);
            CmsProperty property = new CmsProperty();
            CmsTypeProperty typeProperty = new CmsTypeProperty();
            mapper.updateType(type);
            mapper.removeProperty(type.getTypeId(), CmsConstants.CATEGORY_ID);
            mapper.removeProperty(type.getTypeId(), CmsConstants.PERSPECTIVE_ID);
            int order=mapper.getOrder(type.getTypeId());
            typeProperty.setTypeId(type.getTypeId());
            if(type.getUseCategory()==1){                
                property.setPropertyId(CmsConstants.CATEGORY_ID);
                typeProperty.setCmsProperty(property);
                typeProperty.setOrderNumber(order++);
                typeProperty.setIsRequired(1);
                mapper.insertTypeProperty(typeProperty);
            }
            if(type.getUsePerspective()==1){
                property.setPropertyId(CmsConstants.PERSPECTIVE_ID);
                typeProperty.setCmsProperty(property);
                typeProperty.setOrderNumber(order++);
                typeProperty.setIsRequired(1);
                mapper.insertTypeProperty(typeProperty);
            }
            sqlSession.commit();
            return type;
        }catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
                throw new RuntimeException(e.getCause());
        }
        finally {
            sqlSession.close();
        }
    }
    public boolean deleteType(int id) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsTypeMapper mapper =
                sqlSession.getMapper(cmsTypeMapper.class);
                    int count = mapper.removeProperties(id);
                    count = mapper.deleteType(id);                    
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
    public boolean removePropertyById(int id) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsTypeMapper mapper =
                sqlSession.getMapper(cmsTypeMapper.class);
                    int count = mapper.removePropertyById(id);                    
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
    
    public boolean updateOrder(int id,int order) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsTypeMapper mapper =
                sqlSession.getMapper(cmsTypeMapper.class);
                int count = mapper.updateOrder(id,order);
                sqlSession.commit();
                return count>0;
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
    
    public boolean updateRequired(int id,int order) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsTypeMapper mapper =
                sqlSession.getMapper(cmsTypeMapper.class);
                int count = mapper.updateRequired(id,order);
                sqlSession.commit();
                return count>0;
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
    
    public CmsTypeProperty getTypePropertyById(int id) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsTypeMapper mapper =
                sqlSession.getMapper(cmsTypeMapper.class);           
            return mapper.getTypePropertyById(id);
        } finally {
            sqlSession.close();
        }
    }
    
    
    public CmsTypeProperty getUpTypeProperty(CmsTypeProperty typeProperty) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsTypeMapper mapper =
                sqlSession.getMapper(cmsTypeMapper.class);           
            return mapper.getUpTypeProperty(typeProperty);
        } finally {
            sqlSession.close();
        }
    }
    
    public CmsTypeProperty getDownTypeProperty(CmsTypeProperty typeProperty) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsTypeMapper mapper =
                sqlSession.getMapper(cmsTypeMapper.class);           
            return mapper.getDownTypeProperty(typeProperty);
        } finally {
            sqlSession.close();
        }
    }
    //USER-GROUP FUNCTION START
    public List<CmsItemUserRole> getAllItemRole(CmsItemUserRole cmsItemUserRole){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsTypeMapper mapper =
                sqlSession.getMapper(cmsTypeMapper.class);           
            return mapper.getAllItemRole(cmsItemUserRole);
        } finally {
            sqlSession.close();
        }
    }
    public CmsItemUserRole inserItemRole(CmsItemUserRole cmsItemUserRole){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsTypeMapper mapper =
                sqlSession.getMapper(cmsTypeMapper.class);
                mapper.inserItemRole(cmsItemUserRole);
                sqlSession.commit();
                return cmsItemUserRole;
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
    public boolean removeItemRole(CmsItemUserRole cmsItemUserRole){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsTypeMapper mapper =
                sqlSession.getMapper(cmsTypeMapper.class);
                int count = mapper.removeItemRole(cmsItemUserRole);
                sqlSession.commit();
                return count>0;
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
    //USER-GROUP FUNCTION END
}
