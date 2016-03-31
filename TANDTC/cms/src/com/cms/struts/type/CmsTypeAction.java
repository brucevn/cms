package com.cms.struts.type;

import com.cms.models.CmsItemUserRole;
import com.cms.models.CmsType;
import com.cms.models.CmsTypeProperty;
import com.cms.models.CmsUserGroup;
import com.cms.services.CmsPropertyService;
import com.cms.services.CmsTypeService;
import com.cms.struts.CommonAction;
import com.cms.struts.type.CmsTypeForm;
import com.cms.utils.CmsConstants;
import com.cms.utils.Page;

import java.io.PrintWriter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class CmsTypeAction extends CommonAction{
    public CmsTypeAction() {
        super();
    }
    public ActionForward Search(ActionMapping mapping,
                    ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
    {       
        CmsTypeService service = new CmsTypeService();
        CmsTypeForm typeForm = (CmsTypeForm)form;      
        PrintWriter out = response.getWriter();      
        int offset = CmsConstants.PAGE_SIZE*(typeForm.getPage()-1);
        int limit = CmsConstants.PAGE_SIZE*typeForm.getPage();
        request.setAttribute("form",typeForm);
        if(typeForm.getLanguage()!=null&&typeForm.getLanguage().length()==0)
             typeForm.setLanguage("vi");
        if(typeForm.getSearchText()!=null&&typeForm.getSearchText().length()==0)
             typeForm.setSearchText(null);         
        request.setAttribute("types",service.findTypes(typeForm.getLanguage(),typeForm.getSearchText(),offset, limit));       
        String nav = Page.pageNavigator(typeForm.getPage(),CmsConstants.PAGE_SIZE,service.getTypesNumber(typeForm.getLanguage(),typeForm.getSearchText()));
        request.setAttribute("navigator", nav);                 
        return mapping.findForward("home");           
    }
     public ActionForward ShowForm(ActionMapping mapping,
                    ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
     {
         saveToken(request);
         CmsTypeService service = new CmsTypeService();
         CmsTypeForm typeForm = (CmsTypeForm)form;        
         if(typeForm.getTypeId()!=0){                        
              CmsType type = service.getTypeById(typeForm.getTypeId());
              request.setAttribute("type",type);
         }else{
              CmsType type = new CmsType();             
              type.setLanguage(typeForm.getLanguage());
              request.setAttribute("type",type);            
         }
         request.setAttribute("searchText",typeForm.getSearchText());
         return mapping.findForward("form");
     }
     public ActionForward UpdateData(ActionMapping mapping,
                    ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
     {
         CmsTypeService service = new CmsTypeService();
         CmsType type = (CmsType)form;
         if(isValidToken(request)){
             resetToken(request);
             if(type.getTypeId()>0){            
                service.updateType(type);
             }else{
                 service.insertType(type);
             }
         }
         return Search(mapping,form,request,response);        
     }
     public ActionForward Delete(ActionMapping mapping,
                    ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
     {
         CmsTypeService service = new CmsTypeService();
         CmsType type = (CmsType)form;
         service.deleteType(type.getTypeId());         
         return Search(mapping,form,request,response);
     }
    public ActionForward showProperties(ActionMapping mapping,
                   ActionForm form, HttpServletRequest request,
                   HttpServletResponse response) throws Exception
    {
        CmsTypeService service = new CmsTypeService();
        CmsTypeForm typeForm = (CmsTypeForm)form;
        request.setAttribute("form",typeForm);
        request.setAttribute("type",service.getTypeById(typeForm.getTypeId()));
        List<CmsTypeProperty> list = service.getItemPropertiesByType(typeForm.getTypeId());
        request.setAttribute("typeProperties",list); 
        request.setAttribute("NumOfProperties",list.size());
        request.setAttribute("properties",service.getPropertiesList(typeForm.getTypeId()));
        return mapping.findForward("properties");        
    }
    public ActionForward insertProperty(ActionMapping mapping,
                   ActionForm form, HttpServletRequest request,
                   HttpServletResponse response) throws Exception
    {
        CmsTypeService service = new CmsTypeService();
        CmsTypeForm type = (CmsTypeForm)form;
        CmsTypeProperty typeProperty = new CmsTypeProperty();
        typeProperty.setTypeId(type.getTypeId());
        typeProperty.setIsRequired(type.getIsRequired());
        typeProperty.setOrderNumber(service.getOrder(type.getTypeId()));
        typeProperty.setCmsProperty(new CmsPropertyService().getPropertyById(type.getPropertyId()));
        service.insertTypeProperty(typeProperty);
        return showProperties(mapping,form,request,response);
    }
    public ActionForward deleteProperty(ActionMapping mapping,
                   ActionForm form, HttpServletRequest request,
                   HttpServletResponse response) throws Exception
    {
        CmsTypeService service = new CmsTypeService();
        CmsTypeForm type = (CmsTypeForm)form;       
        service.removePropertyById(type.getTypePropertyId());
        return showProperties(mapping,form,request,response);
    }
    
    public ActionForward Up(ActionMapping mapping,
                   ActionForm form, HttpServletRequest request,
                   HttpServletResponse response) throws Exception
    {
        CmsTypeService service = new CmsTypeService();
        CmsTypeForm type = (CmsTypeForm)form;       
        CmsTypeProperty currentTypeProperty = service.getTypePropertyById(type.getTypePropertyId());
        CmsTypeProperty upTypeProperty = service.getUpTypeProperty(currentTypeProperty);        
        service.updateOrder(currentTypeProperty.getId(),currentTypeProperty.getOrderNumber()-1);        
        service.updateOrder(upTypeProperty.getId(),upTypeProperty.getOrderNumber()+1);
        PrintWriter out = response.getWriter(); 
        out.println(currentTypeProperty.getCmsProperty().getPropertyLabel()+":"+currentTypeProperty.getOrderNumber());
        out.println(upTypeProperty.getCmsProperty().getPropertyLabel()+":"+upTypeProperty.getOrderNumber());
        return showProperties(mapping,form,request,response);
    }
    
    public ActionForward Down(ActionMapping mapping,
                   ActionForm form, HttpServletRequest request,
                   HttpServletResponse response) throws Exception
    {
        CmsTypeService service = new CmsTypeService();
        CmsTypeForm type = (CmsTypeForm)form;       
        CmsTypeProperty currentTypeProperty = service.getTypePropertyById(type.getTypePropertyId());
        CmsTypeProperty downTypeProperty = service.getDownTypeProperty(currentTypeProperty);                
        service.updateOrder(currentTypeProperty.getId(),currentTypeProperty.getOrderNumber()+1);
        service.updateOrder(downTypeProperty.getId(),downTypeProperty.getOrderNumber()-1);
        return showProperties(mapping,form,request,response);
    }
    
    public ActionForward require(ActionMapping mapping,
                   ActionForm form, HttpServletRequest request,
                   HttpServletResponse response) throws Exception
    {
        CmsTypeService service = new CmsTypeService();
        CmsTypeForm type = (CmsTypeForm)form;       
        CmsTypeProperty typeProperty = service.getTypePropertyById(type.getTypePropertyId());        
        service.updateRequired(typeProperty.getId(),Math.abs(typeProperty.getIsRequired()-1));
        return showProperties(mapping,form,request,response);
    }
    
    public ActionForward itemRole(ActionMapping mapping,
                   ActionForm form, HttpServletRequest request,
                   HttpServletResponse response) throws Exception
    {
        CmsTypeService service = new CmsTypeService();
        CmsTypeForm type = (CmsTypeForm)form;       
        CmsItemUserRole cmsItemUserRole = new CmsItemUserRole();
        cmsItemUserRole.setTypeId(type.getTypeId());
        cmsItemUserRole.setIsUser(1);
        List users = service.getAllItemRole(cmsItemUserRole);
        cmsItemUserRole.setIsUser(0);
        List groups = service.getAllItemRole(cmsItemUserRole);
        saveToken(request);
        request.setAttribute("users", users);
        request.setAttribute("groups",groups);
        request.setAttribute("form",type);
        return mapping.findForward("itemRole");
    }
    public ActionForward addItemRole(ActionMapping mapping,
                   ActionForm form, HttpServletRequest request,
                   HttpServletResponse response) throws Exception
    {
        CmsTypeService service = new CmsTypeService();
        CmsTypeForm type = (CmsTypeForm)form;   
        if(isValidToken(request)&&type.getUserGroup()!=null&&type.getUserGroup().length()>0){
            CmsItemUserRole cmsItemUserRole = new CmsItemUserRole();
            cmsItemUserRole.setTypeId(type.getTypeId());
            cmsItemUserRole.setIsUser(type.getIsUser());
            cmsItemUserRole.setUserGroup(type.getUserGroup());
            cmsItemUserRole.setUserRole(type.getUserRole());
            service.inserItemRole(cmsItemUserRole);
            resetToken(request);
        }
        return itemRole(mapping,form, request,response);
    }
    public ActionForward removeItemRole(ActionMapping mapping,
                   ActionForm form, HttpServletRequest request,
                   HttpServletResponse response) throws Exception
    {
        CmsTypeService service = new CmsTypeService();
        CmsTypeForm type = (CmsTypeForm)form;   
        if(isValidToken(request)){
            if(type.getRoleId()>0){
                CmsItemUserRole cmsItemUserRole = new CmsItemUserRole();
                cmsItemUserRole.setId(type.getRoleId());
                service.removeItemRole(cmsItemUserRole);
            }
            resetToken(request);
        }
        return itemRole(mapping,form, request,response);
    }
}
