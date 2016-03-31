package com.cms.struts.property;

import com.cms.models.CmsProperty;
import com.cms.services.CmsPropertyService;
import com.cms.struts.property.CmsPropertyForm;
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

public class CmsPropertyAction extends DispatchAction{
    public CmsPropertyAction() {
        super();
    }
    public ActionForward Search(ActionMapping mapping,
                    ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
    {       
        CmsPropertyService service = new CmsPropertyService();
        CmsPropertyForm propertyForm = (CmsPropertyForm)form;      
        PrintWriter out = response.getWriter();      
        int offset = CmsConstants.PAGE_SIZE*(propertyForm.getPage()-1);
        int limit = CmsConstants.PAGE_SIZE*propertyForm.getPage();
        request.setAttribute("form",propertyForm);
        if(propertyForm.getLanguage()!=null&&propertyForm.getLanguage().length()==0)
             propertyForm.setLanguage("vi");
        if(propertyForm.getSearchText()!=null&&propertyForm.getSearchText().length()==0)
             propertyForm.setSearchText(null);         
        request.setAttribute("properties",service.findProperties(propertyForm.getLanguage(),propertyForm.getSearchText(),offset, limit));       
        String nav = Page.pageNavigator(propertyForm.getPage(),CmsConstants.PAGE_SIZE,service.getPropertiesNumber(propertyForm.getLanguage(),propertyForm.getSearchText()));
        request.setAttribute("navigator", nav);                 
        return mapping.findForward("home");           
    }
     public ActionForward ShowForm(ActionMapping mapping,
                    ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
     {
         CmsPropertyService service = new CmsPropertyService();
         CmsPropertyForm propertyForm = (CmsPropertyForm)form;
         if(propertyForm.getPropertyId()>0){                        
             CmsProperty property = service.getPropertyById(propertyForm.getPropertyId());
             request.setAttribute("property",property);
         }else{
             CmsProperty property = new CmsProperty();             
             property.setLanguage(propertyForm.getLanguage());
             request.setAttribute("property",property);            
         }
         request.setAttribute("searchText",propertyForm.getSearchText());
         return mapping.findForward("form");
     }
     public ActionForward UpdateData(ActionMapping mapping,
                    ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
     {
         CmsPropertyService service = new CmsPropertyService();
         CmsProperty property = (CmsProperty)form;
         if(property.getPropertyId()>0){  
             property.propertyLableToName();
            service.updateProperty(property);
         }else{
             property.propertyLableToName();
             service.insertProperty(property);
         }
         return Search(mapping,form,request,response);        
     }
     public ActionForward Delete(ActionMapping mapping,
                    ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
     {
         CmsPropertyService service = new CmsPropertyService();
         CmsProperty property = (CmsProperty)form;
         service.deleteProperty(property.getPropertyId());
         return Search(mapping,form,request,response);
     }
}
