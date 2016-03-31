package com.cms.struts.category;

import com.cms.models.CmsCategory;
import com.cms.services.CmsCategoryService;

import com.cms.utils.CmsConstants;

import com.cms.utils.Page;
import com.cms.utils.PortletUtil;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class CmsCategoryAction extends DispatchAction{
    public CmsCategoryAction() {
        super();
    }
    public ActionForward Search(ActionMapping mapping,
                   ActionForm form, HttpServletRequest request,
                   HttpServletResponse response) throws Exception
   {       
       CmsCategoryService service = new CmsCategoryService();
       CmsCategoryForm categoryForm = (CmsCategoryForm)form;      
       PrintWriter out = response.getWriter();       
       int offset = CmsConstants.PAGE_SIZE*(categoryForm.getPage()-1);
       int limit = CmsConstants.PAGE_SIZE*categoryForm.getPage();
       request.setAttribute("form",categoryForm);
       if(categoryForm.getLanguage()!=null&&categoryForm.getLanguage().length()==0)
            categoryForm.setLanguage("vi");
       if(categoryForm.getSearchText()!=null&&categoryForm.getSearchText().length()==0)
            categoryForm.setSearchText(null);         
       request.setAttribute("categories",service.findCategories(categoryForm.getLanguage(),categoryForm.getSearchText(),categoryForm.getParentId(),offset, limit));       
       String nav = Page.pageNavigator(categoryForm.getPage(),CmsConstants.PAGE_SIZE,service.getFindCategoriesNumber(categoryForm.getLanguage(),categoryForm.getSearchText(),categoryForm.getParentId(),offset, limit));
       request.setAttribute("navigator", nav);  
       if(categoryForm.getParentId()>0){       
            request.setAttribute("uplevel",service.getCategoryById(categoryForm.getParentId()).getParentId());
       } else 
            request.setAttribute("uplevel","0");
       
       return mapping.findForward("home");        
   }
    public ActionForward ShowForm(ActionMapping mapping,
                   ActionForm form, HttpServletRequest request,
                   HttpServletResponse response) throws Exception
    {
        CmsCategoryService service = new CmsCategoryService();
        CmsCategoryForm categoryForm = (CmsCategoryForm)form;
        if(categoryForm.getCategoryId()>0){                        
            CmsCategory category = service.getCategoryById(categoryForm.getCategoryId());
            request.setAttribute("category",category);
        }else{
            CmsCategory category = new CmsCategory();
            category.setParentId(categoryForm.getParentId());
            category.setLanguage(categoryForm.getLanguage());
            request.setAttribute("category",category);            
        }
        request.setAttribute("searchText",categoryForm.getSearchText());
        return mapping.findForward("form");
    }
    public ActionForward UpdateData(ActionMapping mapping,
                   ActionForm form, HttpServletRequest request,
                   HttpServletResponse response) throws Exception
    {
        CmsCategoryService service = new CmsCategoryService();
        CmsCategory category = (CmsCategory)form;
        if(category.getCategoryId()>0){            
           service.updateCategory(category);
        }else{
            service.insertCategory(category);
        }
        return Search(mapping,form,request,response);        
    }
    public ActionForward Delete(ActionMapping mapping,
                   ActionForm form, HttpServletRequest request,
                   HttpServletResponse response) throws Exception
    {
        CmsCategoryService service = new CmsCategoryService();
        CmsCategory category = (CmsCategory)form;
        service.deleteCategory(category.getCategoryId());
        return Search(mapping,form,request,response);
    }
}
