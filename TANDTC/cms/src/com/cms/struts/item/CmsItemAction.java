package com.cms.struts.item;

import com.cms.models.CmsFile;
import com.cms.models.CmsItem;
import com.cms.models.CmsPerspective;
import com.cms.models.CmsProperty;
import com.cms.models.CmsPropertyValue;
import com.cms.models.CmsType;
import com.cms.models.CmsTypeProperty;
import com.cms.services.CmsCategoryService;
import com.cms.services.CmsItemService;
import com.cms.services.CmsPerspectiveService;
import com.cms.services.CmsTypeService;
import com.cms.struts.CommonAction;

import com.cms.utils.CmsConstants;
import com.cms.utils.DateTimeFormat;
import com.cms.utils.Page;

import com.cms.utils.PortletUtil;

import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CmsItemAction extends CommonAction {
    public CmsItemAction() {
        super();
    }
    public ActionForward Search(ActionMapping mapping,
                   ActionForm form, HttpServletRequest request,
                   HttpServletResponse response) throws Exception
    {
       CmsItemService service = new CmsItemService();
        CmsItemForm _form = (CmsItemForm)form;
        PrintWriter out = response.getWriter();
        int offset = CmsConstants.PAGE_SIZE * (_form.getPage() - 1);
        int limit = CmsConstants.PAGE_SIZE * _form.getPage();
        if (_form.getLanguage() == null||_form.getLanguage().length() == 0||_form.getLanguage().equalsIgnoreCase("null"))
            _form.setLanguage(CmsConstants.VIETNAMESE);
        if (_form.getSearchText() != null &&
            _form.getSearchText().length() == 0)
            _form.setSearchText(null);
        if (_form.getFromDate() != null &&
            _form.getFromDate().length() == 0)
            _form.setFromDate(null);
        if (_form.getToDate() != null &&
            _form.getToDate().length() == 0)
            _form.setToDate(null);
        
        List<CmsItem> list =
            service.findAllItems(_form.getLanguage(), _form.getSearchText(),
                                 _form.getOwner(), _form.getCategory(),
                                 _form.getType(), _form.getPerspective(),
                                 _form.getFromDate(), _form.getToDate(),
                                 offset, limit);
        int count =
            service.getFindAllItemsNumber(_form.getLanguage(), _form.getSearchText(),
                                          _form.getOwner(),
                                          _form.getCategory(),
                                          _form.getType(),
                                          _form.getPerspective(),
                                          _form.getFromDate(),
                                          _form.getToDate());
        
        request.setAttribute("items", list);   
        request.setAttribute("navigator",Page.pageNavigator(_form.getPage(),CmsConstants.PAGE_SIZE, count));
        request.setAttribute("form", _form);
        request.setAttribute("perspectives",new CmsPerspectiveService().findAllPerspectives(_form.getLanguage()));
        request.setAttribute("categories",new CmsCategoryService().findAllCategories(_form.getLanguage()));
        request.setAttribute("types",new CmsTypeService().getAllTypes(_form.getLanguage()));
        return mapping.findForward("home");
    }
    
    public ActionForward ChooseType(ActionMapping mapping,
                   ActionForm form, HttpServletRequest request,
                   HttpServletResponse response) throws Exception
    {
        CmsItemForm _form = (CmsItemForm)form;     
        if (_form.getLanguage() == null||_form.getLanguage().length() == 0||_form.getLanguage().equalsIgnoreCase("null"))
            _form.setLanguage(CmsConstants.VIETNAMESE);
        request.setAttribute("types",new CmsTypeService().getAllTypes(_form.getLanguage()));
        request.setAttribute("form",_form);
        return mapping.findForward("ChooseType");
    }
    public ActionForward ShowForm(ActionMapping mapping,
                   ActionForm form, HttpServletRequest request,
                   HttpServletResponse response) throws Exception
    {
        CmsItemForm _form = (CmsItemForm)form;  
        if (_form.getLanguage() == null||_form.getLanguage().length() == 0||_form.getLanguage().equalsIgnoreCase("null"))
            _form.setLanguage(CmsConstants.VIETNAMESE);
        CmsTypeService typeService = new CmsTypeService();
        CmsItemService itemService = new CmsItemService();
        CmsCategoryService categoryService = new CmsCategoryService();
        CmsItem item = new CmsItem();
        if(_form.getItemId()>0){
            item=itemService.getItemById(_form.getItemId());
            _form.setTypeId(item.getTypeId());
        }
        CmsType type = typeService.getTypeById(_form.getTypeId());
        List<CmsTypeProperty> typeProperties = typeService.getItemPropertiesByType(_form.getTypeId());
        
        ArrayList<CmsPropertyValue> values= new ArrayList<CmsPropertyValue>();
        if(typeProperties.size()>0){
            for(int i=0;i<typeProperties.size();i++){
                CmsPropertyValue value = new CmsPropertyValue();
                CmsPropertyValue temp = new CmsPropertyValue();
                CmsTypeProperty typeProperty = typeProperties.get(i);
                String propertyType=typeProperty.getCmsProperty().getPropertyType();
                if(_form.getItemId()>0&&typeProperty.getCmsProperty().getPropertyId()>0){                         
                    if("checkbox,selectbox,image,".indexOf(propertyType+",")>-1){
                        temp=itemService.getPropertyNumberValue(_form.getItemId(),typeProperty.getCmsProperty().getPropertyId());
                    }else if(propertyType.equalsIgnoreCase("text")){
                        temp=itemService.getPropertyTextValue(_form.getItemId(),typeProperty.getCmsProperty().getPropertyId());
                    }else if(propertyType.equalsIgnoreCase("textarea")){
                        temp=itemService.getPropertyLongTextValue(_form.getItemId(),typeProperty.getCmsProperty().getPropertyId());
                    }else if("simpledate,fulldate,".indexOf(propertyType+",")>-1){
                        temp = itemService.getPropertyDateValue(_form.getItemId(),typeProperty.getCmsProperty().getPropertyId(),propertyType);
                    }else if(propertyType.equalsIgnoreCase("file")){
                        List<CmsFile> files = itemService.getAllItemFiles(_form.getItemId());
                        temp.setMutiValues(files);
                        String strTemp = "";
                        if(files.size()>0){
                            for(int j=0;j<files.size();j++){
                                CmsFile file = files.get(j);
                                strTemp = strTemp+file.getFileId()+",";
                            }
                        }
                        temp.setStrValue(strTemp);
                    }
                }
                if(temp!=null) value=temp;
                value.setPropertyType(propertyType);
                value.setPropertyId(typeProperty.getCmsProperty().getPropertyId());
                if(propertyType.equalsIgnoreCase(CmsConstants.SELECT_BOX)&&typeProperty.getCmsProperty().getResourceId()>0){
                    value.setMutiValues(categoryService.findAllSubCategories(typeProperty.getCmsProperty().getResourceId()));                    
                }                
                switch(value.getPropertyId()){
                    case CmsConstants.TITLE_ID:
                        value.setPropertyLabel(type.getTitleLabel());
                        value.setStrValue(item.getTitle());
                        break;
                    case CmsConstants.CONTENT_ID:
                        value.setStrValue(item.getContent());
                        value.setPropertyLabel(type.getContentLabel());
                        break;
                    case CmsConstants.CATEGORY_ID:
                        request.setAttribute("categories",categoryService.findAllCategories(_form.getLanguage()));
                        value.setPropertyLabel(CmsConstants.getLabel("CATEGORY_LABEL"));
                        break;
                    case CmsConstants.PERSPECTIVE_ID:
                        List<CmsPerspective> itemPerspectives = itemService.getAllItemPerspectives(_form.getItemId());
                        String perspectiveIds ="";
                        List<Integer> list = new ArrayList<Integer>();
                        if(itemPerspectives.size()>0){
                            for(int j=0;j<itemPerspectives.size();j++){
                                perspectiveIds=perspectiveIds+itemPerspectives.get(j).getPerspectiveId();                    
                                if(j<itemPerspectives.size()-1)perspectiveIds+=",";
                                list.add(itemPerspectives.get(j).getPerspectiveId());
                            }                
                        }else{
                            perspectiveIds="0";
                        }
                        list.add(0);
                        request.setAttribute("itemPerspectives", itemPerspectives);
                        value.setStrValue(perspectiveIds);            
                        request.setAttribute("perspectivesSource", itemService.getSelectablePerspectives(list));
                        value.setPropertyLabel(CmsConstants.getLabel("PERSPECTIVE_LABEL"));
                        break;
                    case CmsConstants.PUBLISHDATE_ID:
                        value.setDateValue(item.getPublishDate());
                        if(item.getPublishDate()!=null)
                            value.setStrValue(DateTimeFormat.formatDate(value.getDateValue(),"dd/MM/yyyy HH:mm:ss"));
                        value.setPropertyLabel(CmsConstants.getLabel("PUBLISH_DATE"));
                        break;
                    default:
                        value.setPropertyLabel(typeProperty.getCmsProperty().getPropertyLabel());
                        break;
                } 
                value.setName(typeProperty.getCmsProperty().getPropertyName());
                value.setRequired(typeProperty.getIsRequired()==1);
                values.add(value);
            }
        }
        saveToken(request);
        request.setAttribute("item",item);
        request.setAttribute("form",_form);        
        request.setAttribute("propertyValues",values);
        return mapping.findForward("form");
    }
    
    public ActionForward update(ActionMapping mapping,
                   ActionForm form, HttpServletRequest request,
                   HttpServletResponse response) throws Exception
    {
        if(isValidToken(request)){
            CmsItemForm _form = (CmsItemForm)form;     
            CmsItem item = (CmsItem)form;        
            if (_form.getLanguage() == null||_form.getLanguage().length() == 0||_form.getLanguage().equalsIgnoreCase("null"))
                _form.setLanguage(CmsConstants.VIETNAMESE);
            PrintWriter out = response.getWriter();
            CmsItemService service = new CmsItemService();
            if(PortletUtil.getParameterValue(request,"publishedDate").length()>0){
                item.setPublishDate(DateTimeFormat.convertStringtoDate(PortletUtil.getParameterValue(request,"publishedDate"),"dd/MM/yyyy HH:mm"));
            }else{
                item.setPublishDate(new Date());
            }
            CmsTypeService typeService= new CmsTypeService();
            List<CmsTypeProperty> typeProperties = typeService.getItemPropertiesByType(_form.getTypeId());
            ArrayList<CmsPropertyValue> values= new ArrayList<CmsPropertyValue>();
            for(int i=0;i<typeProperties.size();i++){
                CmsPropertyValue value = new CmsPropertyValue();            
                CmsTypeProperty typeProperty = typeProperties.get(i);
                CmsProperty property=typeProperty.getCmsProperty();                        
                if(PortletUtil.getParameterValue(request, property.getPropertyName()).length()>0){
                    if(property.getPropertyId()>0){
                        if("checkbox,selectbox,image,".indexOf(property.getPropertyType()+",")>-1){                
                            value.setIntValue(Integer.parseInt(PortletUtil.getParameterValue(request, property.getPropertyName())));
                        }else if("text,textarea,file,".indexOf(property.getPropertyType()+",")>-1){
                            value.setStrValue(PortletUtil.getParameterValue(request, property.getPropertyName()));
                        }else if(property.getPropertyType().equalsIgnoreCase("simpledate")){
                            Date dateValue = DateTimeFormat.convertStringtoDate(PortletUtil.getParameterValue(request, property.getPropertyName()),"dd/MM/yyyy");
                            value.setDateValue(dateValue);
                        }else if(property.getPropertyType().equalsIgnoreCase("fulldate")){
                            Date dateValue = DateTimeFormat.convertStringtoDate(PortletUtil.getParameterValue(request, property.getPropertyName()),"dd/MM/yyyy HH:mm");
                            value.setDateValue(dateValue);
                        }
                        value.setPropertyId(property.getPropertyId());
                        value.setPropertyType(property.getPropertyType());
                        values.add(value);
                    }
                    if(property.getPropertyId()==CmsConstants.PERSPECTIVE_ID){
                        value.setStrValue(PortletUtil.getParameterValue(request, property.getPropertyName()));
                        value.setPropertyId(property.getPropertyId());
                        value.setPropertyType(property.getPropertyType());
                        values.add(value);
                    }                
                } 
            }
            if(item.getItemId()>0){
                service.updateItem(item,values);
            }else{
                service.insertItem(item,values);
            }
            resetToken(request);
        }
        return Search(mapping,form,request,response);
    }
    public ActionForward  delete(ActionMapping mapping,
                   ActionForm form, HttpServletRequest request,
                   HttpServletResponse response) throws Exception
    {
        CmsItemForm _form = (CmsItemForm)form;
        CmsItemService service = new CmsItemService();
        service.deleteItem(_form.getItemId());
        return Search(mapping,form,request,response);
    }
}
