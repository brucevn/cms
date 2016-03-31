package com.cms.services;


import com.cms.mappers.cmsItemMapper;
import com.cms.models.CmsFile;
import com.cms.models.CmsItem;
import com.cms.models.CmsItemFiles;
import com.cms.models.CmsItemPerspective;
import com.cms.models.CmsPerspective;
import com.cms.models.CmsPropertyValue;
import com.cms.utils.CmsConstants;
import com.cms.utils.MyBatisSqlSessionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;


public class CmsItemService {
    public CmsItemService() {
        super();
    }
    public List<CmsItem> findAllItems(String language,String title,String owner,int categoryId,int typeId,int perspectiveId,String fromDate,String toDate,int offset,int limit) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsItemMapper mapper =
                sqlSession.getMapper(cmsItemMapper.class);
            RowBounds rowBounds = new RowBounds(offset, limit);
            return mapper.findAllItems(language,title,owner,categoryId,typeId,perspectiveId,fromDate,toDate,rowBounds);
        } finally {
            sqlSession.close();
        }
    }
    public int getFindAllItemsNumber(String language,String title,String owner,int categoryId,int typeId,int perspectiveId,String fromDate,String toDate){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsItemMapper mapper =
                sqlSession.getMapper(cmsItemMapper.class);            
            return mapper.getFindAllItemsNumber(language,title,owner,categoryId,typeId,perspectiveId,fromDate,toDate);
        } finally {
            sqlSession.close();
        }
    }
    public int getMasterId() {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsItemMapper mapper =
                sqlSession.getMapper(cmsItemMapper.class);            
            return mapper.getMasterId();
        } finally {
            sqlSession.close();
        }
    }
    
    public CmsItem getItemById(int id) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsItemMapper mapper =
                sqlSession.getMapper(cmsItemMapper.class);            
            return mapper.getItemById(id);
        } finally {
            sqlSession.close();
        }
    }
    
    public CmsItem insertItem(CmsItem item,List<CmsPropertyValue> propertyValues){        
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsItemMapper mapper =
                sqlSession.getMapper(cmsItemMapper.class);            
            mapper.insertItem(item);            
            for(int i=0;i<propertyValues.size();i++){
                CmsPropertyValue pValue = propertyValues.get(i);
                pValue.setItemId(item.getItemId());
                if(pValue.getPropertyId()==CmsConstants.PERSPECTIVE_ID){
                    CmsItemPerspective itemPers = new CmsItemPerspective();
                    if(pValue.getStrValue().indexOf(",")>0){
                        String[] ids = pValue.getStrValue().split(",");
                        for(int j=0;j<ids.length;j++){
                            itemPers.setPerspectiveId(Integer.parseInt(ids[j]));
                            itemPers.setItemId(item.getItemId());
                            itemPers.setOrderNumber(j);
                            mapper.insertItemPerspective(itemPers);
                        }
                    }else{
                        itemPers.setPerspectiveId(Integer.parseInt(pValue.getStrValue()));
                        itemPers.setItemId(item.getItemId());
                        itemPers.setOrderNumber(0);
                        mapper.insertItemPerspective(itemPers);
                    }                    
                }
                if("checkbox,selectbox,image,".indexOf(pValue.getPropertyType()+",")>-1){
                    mapper.insertPropertyNumberValue(pValue);
                }else if(pValue.getPropertyType().equalsIgnoreCase("text")){
                    mapper.insertPropertyTextValue(pValue);
                }else if(pValue.getPropertyType().equalsIgnoreCase("textarea")){
                    mapper.insertPropertyLongTextValue(pValue);
                }else if("simpledate,fulldate,".indexOf(pValue.getPropertyType()+",")>-1){
                    mapper.insertPropertyDateValue(pValue);
                }else if(pValue.getPropertyType().equalsIgnoreCase("file")){
                    CmsItemFiles itemFile = new CmsItemFiles();
                    if(pValue.getStrValue()!=null&&pValue.getStrValue().indexOf(",")>0){
                        String[] ids = pValue.getStrValue().split(",");
                        for(int j=0;j<ids.length;j++){
                            itemFile.setFileId(Integer.parseInt(ids[j]));
                            itemFile.setItemId(item.getItemId());
                            itemFile.setOrderNumber(j);
                            mapper.insertItemFile(itemFile);
                        }
                    }else{
                        itemFile.setFileId(Integer.parseInt(pValue.getStrValue()));
                        itemFile.setItemId(item.getItemId());
                        itemFile.setOrderNumber(0);
                        mapper.insertItemFile(itemFile);
                    }
                }
            }
            sqlSession.commit();
            return item;
        }catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
                throw new RuntimeException(e.getCause());
        }
        finally {
            sqlSession.close();
        }
    }
    public CmsItem updateItem(CmsItem item,List<CmsPropertyValue> propertyValues){        
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsItemMapper mapper =
                sqlSession.getMapper(cmsItemMapper.class);
            mapper.updateItem(item);
            mapper.deletePropertyNumberValue(item.getItemId());
            mapper.deletePropertyTextValue(item.getItemId());
            mapper.deletePropertyLongTextValue(item.getItemId());
            mapper.deletePropertyDateValue(item.getItemId());
            mapper.deleteAllItemPerspectives(item.getItemId());
            mapper.deleteAllItemFiles(item.getItemId());
            for(int i=0;i<propertyValues.size();i++){
                CmsPropertyValue pValue = propertyValues.get(i);
                pValue.setItemId(item.getItemId());
                if(pValue.getPropertyId()==CmsConstants.PERSPECTIVE_ID){                    
                    CmsItemPerspective itemPers = new CmsItemPerspective();
                    if(pValue.getStrValue().indexOf(",")>0){
                        String[] ids = pValue.getStrValue().split(",");
                        for(int j=0;j<ids.length;j++){
                            itemPers.setPerspectiveId(Integer.parseInt(ids[j]));
                            itemPers.setItemId(item.getItemId());
                            itemPers.setOrderNumber(j);
                            mapper.insertItemPerspective(itemPers);
                        }
                    }else{
                        itemPers.setPerspectiveId(Integer.parseInt(pValue.getStrValue()));
                        itemPers.setItemId(item.getItemId());
                        itemPers.setOrderNumber(0);
                        mapper.insertItemPerspective(itemPers);
                    }                    
                }
                if("checkbox,selectbox,image,".indexOf(pValue.getPropertyType()+",")>-1){                                        
                    mapper.updatePropertyNumberValue(pValue);
                    if(pValue.getId()==0){
                        mapper.insertPropertyNumberValue(pValue);
                    }
                }else if(pValue.getPropertyType().equalsIgnoreCase("text")){                    
                    mapper.updatePropertyTextValue(pValue);
                    if(pValue.getId()==0){
                        mapper.insertPropertyTextValue(pValue);
                    }
                }else if(pValue.getPropertyType().equalsIgnoreCase("textarea")){                    
                    mapper.updatePropertyLongTextValue(pValue);
                    if(pValue.getId()==0){
                        mapper.insertPropertyLongTextValue(pValue);
                    }
                }else if("simpledate,fulldate,".indexOf(pValue.getPropertyType()+",")>-1){                    
                    mapper.updatePropertyDateValue(pValue);
                    if(pValue.getId()==0){
                        mapper.insertPropertyDateValue(pValue);
                    }
                }else if(pValue.getPropertyType().equalsIgnoreCase("file")){                    
                    CmsItemFiles itemFile = new CmsItemFiles();
                    if(pValue.getStrValue()!=null&&pValue.getStrValue().indexOf(",")>0){
                        String[] ids = pValue.getStrValue().split(",");
                        for(int j=0;j<ids.length;j++){
                            itemFile.setFileId(Integer.parseInt(ids[j]));
                            itemFile.setItemId(item.getItemId());
                            itemFile.setOrderNumber(j);
                            mapper.insertItemFile(itemFile);
                        }
                    }else{
                        itemFile.setFileId(Integer.parseInt(pValue.getStrValue()));
                        itemFile.setItemId(item.getItemId());
                        itemFile.setOrderNumber(0);
                        mapper.insertItemFile(itemFile);
                    }
                }
            }
            sqlSession.commit();
            return item;
        }catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
                throw new RuntimeException(e.getCause());
        }
        finally {
            sqlSession.close();
        }
    }
    public boolean deleteItem(int id) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            cmsItemMapper mapper =
                sqlSession.getMapper(cmsItemMapper.class);
                mapper.deletePropertyNumberValue(id);
                mapper.deletePropertyTextValue(id);
                mapper.deletePropertyLongTextValue(id);
                mapper.deletePropertyDateValue(id);
                mapper.deleteAllItemPerspectives(id);
                mapper.deleteAllItemFiles(id);
                int count = mapper.deleteItem(id);
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
    public CmsPropertyValue getPropertyNumberValue(int itemId,int propertyId){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
                cmsItemMapper mapper =
                    sqlSession.getMapper(cmsItemMapper.class);
                return mapper.getPropertyNumberValue(itemId, propertyId);
        }
        finally {
                sqlSession.close();
        }
    }
    public CmsPropertyValue insertPropertyNumberValue(CmsPropertyValue propertyValue){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
                cmsItemMapper mapper =
                    sqlSession.getMapper(cmsItemMapper.class);                    
                mapper.insertPropertyNumberValue(propertyValue);
                sqlSession.commit();
            } 
            catch (Exception e) {
                    sqlSession.rollback();
                    e.printStackTrace();
                    throw new RuntimeException(e.getCause());
            }
            finally {
                    sqlSession.close();
            }
        return propertyValue;
    }
    public CmsPropertyValue updatePropertyNumberValue(CmsPropertyValue propertyValue){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
                cmsItemMapper mapper =
                    sqlSession.getMapper(cmsItemMapper.class);                    
                mapper.updatePropertyNumberValue(propertyValue);
                sqlSession.commit();
            } 
            catch (Exception e) {
                    sqlSession.rollback();
                    e.printStackTrace();
                    throw new RuntimeException(e.getCause());
            }
            finally {
                    sqlSession.close();
            }
        return propertyValue;
    }

    public CmsPropertyValue getPropertyTextValue(int itemId,int propertyId){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
                cmsItemMapper mapper =
                    sqlSession.getMapper(cmsItemMapper.class);                    
                return mapper.getPropertyTextValue(itemId,propertyId);
            }
            finally {
                    sqlSession.close();
            }        
    }
    public CmsPropertyValue insertPropertyTextValue(CmsPropertyValue propertyValue){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
                cmsItemMapper mapper =
                    sqlSession.getMapper(cmsItemMapper.class);                    
                mapper.insertPropertyTextValue(propertyValue);
                sqlSession.commit();
            } 
            catch (Exception e) {
                    sqlSession.rollback();
                    e.printStackTrace();
                    throw new RuntimeException(e.getCause());
            }
            finally {
                    sqlSession.close();
            }
        return propertyValue;
    }
    public CmsPropertyValue updatePropertyTextValue(CmsPropertyValue propertyValue){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
                cmsItemMapper mapper =
                    sqlSession.getMapper(cmsItemMapper.class);                    
                mapper.updatePropertyTextValue(propertyValue);
                sqlSession.commit();
            } 
            catch (Exception e) {
                    sqlSession.rollback();
                    e.printStackTrace();
                    throw new RuntimeException(e.getCause());
            }
            finally {
                    sqlSession.close();
            }
        return propertyValue;
    }
    public CmsPropertyValue getPropertyLongTextValue(int itemId,int propertyId){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
                cmsItemMapper mapper =
                    sqlSession.getMapper(cmsItemMapper.class);                    
                return mapper.getPropertyLongTextValue(itemId,propertyId);
            }
            finally {
                    sqlSession.close();
            }        
    }
    public CmsPropertyValue insertPropertyLongTextValue(CmsPropertyValue propertyValue){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
                cmsItemMapper mapper =
                    sqlSession.getMapper(cmsItemMapper.class);                    
                mapper.insertPropertyLongTextValue(propertyValue);
                sqlSession.commit();
            } 
            catch (Exception e) {
                    sqlSession.rollback();
                    e.printStackTrace();
                    throw new RuntimeException(e.getCause());
            }
            finally {
                    sqlSession.close();
            }
        return propertyValue;
    }
    public CmsPropertyValue updatePropertyLongTextValue(CmsPropertyValue propertyValue){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
                cmsItemMapper mapper =
                    sqlSession.getMapper(cmsItemMapper.class);                    
                mapper.updatePropertyLongTextValue(propertyValue);
                sqlSession.commit();
            } 
            catch (Exception e) {
                    sqlSession.rollback();
                    e.printStackTrace();
                    throw new RuntimeException(e.getCause());
            }
            finally {
                    sqlSession.close();
            }
        return propertyValue;
    }
    
    public CmsPropertyValue getPropertyDateValue(int itemId,int propertyId,String type){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
                cmsItemMapper mapper =
                    sqlSession.getMapper(cmsItemMapper.class);                    
                if(type.equalsIgnoreCase(CmsConstants.SIMPLE_DATE)){
                    return mapper.getPropertySimpleDateValue(itemId,propertyId);
                }else{
                    return mapper.getPropertyFullDateValue(itemId,propertyId);
                }
            }finally {
                    sqlSession.close();
            }        
    }
    public CmsPropertyValue insertPropertyDateValue(CmsPropertyValue propertyValue){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
                cmsItemMapper mapper =
                    sqlSession.getMapper(cmsItemMapper.class);                    
                mapper.insertPropertyDateValue(propertyValue);
                sqlSession.commit();
            } 
            catch (Exception e) {
                    sqlSession.rollback();
                    e.printStackTrace();
                    throw new RuntimeException(e.getCause());
            }
            finally {
                    sqlSession.close();
            }
        return propertyValue;
    }
    public CmsPropertyValue updatePropertyDateValue(CmsPropertyValue propertyValue){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
                cmsItemMapper mapper =
                    sqlSession.getMapper(cmsItemMapper.class);                    
                mapper.updatePropertyDateValue(propertyValue);
                sqlSession.commit();
            } 
            catch (Exception e) {
                    sqlSession.rollback();
                    e.printStackTrace();
                    throw new RuntimeException(e.getCause());
            }
            finally {
                    sqlSession.close();
            }
        return propertyValue;
    }
    
    //Item perspecteive function
    public List<CmsPerspective> getAllItemPerspectives(int itemId){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
                cmsItemMapper mapper =
                    sqlSession.getMapper(cmsItemMapper.class);                    
                return mapper.getAllItemPerspectives(itemId);
            }
            finally {
                    sqlSession.close();
            }        
    }
    
    public List<CmsPerspective> getSelectablePerspectives(List list){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
                cmsItemMapper mapper =
                    sqlSession.getMapper(cmsItemMapper.class);
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("perspectiveIds",list);
                return mapper.getSelectablePerspectives(map);
            }
            finally {
                    sqlSession.close();
            }        
    }
    
    public CmsItemPerspective insertItemPerspective(CmsItemPerspective itemPerpsecive){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
                cmsItemMapper mapper =
                    sqlSession.getMapper(cmsItemMapper.class);                    
                mapper.insertItemPerspective(itemPerpsecive);
                sqlSession.commit();
            } 
            catch (Exception e) {
                    sqlSession.rollback();
                    e.printStackTrace();
                    throw new RuntimeException(e.getCause());
            }
            finally {
                    sqlSession.close();
            }
        return itemPerpsecive;
    }
    
    public boolean deleteAllItemPerspectives(int itemId){
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
                cmsItemMapper mapper =
                    sqlSession.getMapper(cmsItemMapper.class);                    
                int count=mapper.deleteAllItemPerspectives(itemId);
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
    //End Item Perspective
    //Item file function
        public List<CmsFile> getAllItemFiles(int itemId){
            SqlSession sqlSession =
                MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
            try {
                    cmsItemMapper mapper =
                        sqlSession.getMapper(cmsItemMapper.class);                    
                    return mapper.getAllItemFiles(itemId);
                }
                finally {
                        sqlSession.close();
                }        
        }
        public CmsItemFiles insertItemFile(CmsItemFiles itemFile){
            SqlSession sqlSession =
                MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
            try {
                    cmsItemMapper mapper =
                        sqlSession.getMapper(cmsItemMapper.class);                    
                    mapper.insertItemFile(itemFile);
                    sqlSession.commit();
                } 
                catch (Exception e) {
                        sqlSession.rollback();
                        e.printStackTrace();
                        throw new RuntimeException(e.getCause());
                }
                finally {
                        sqlSession.close();
                }
            return itemFile;
        }
        
        public boolean deleteAllItemFiles(int itemId){
            SqlSession sqlSession =
                MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
            try {
                    cmsItemMapper mapper =
                        sqlSession.getMapper(cmsItemMapper.class);                    
                    int count=mapper.deleteAllItemFiles(itemId);
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
}
