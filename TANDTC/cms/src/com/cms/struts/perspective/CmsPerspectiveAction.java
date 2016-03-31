package com.cms.struts.perspective;

import com.cms.models.CmsPerspective;
import com.cms.services.CmsPerspectiveService;

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

public class CmsPerspectiveAction extends DispatchAction{
    public CmsPerspectiveAction() {
        super();
    }
    public ActionForward Search(ActionMapping mapping,
                   ActionForm form, HttpServletRequest request,
                   HttpServletResponse response) throws Exception
   {       
       CmsPerspectiveService service = new CmsPerspectiveService();
       CmsPerspectiveForm perspectiveForm = (CmsPerspectiveForm)form;      
       PrintWriter out = response.getWriter();      
       int offset = CmsConstants.PAGE_SIZE*(perspectiveForm.getPage()-1);
       int limit = CmsConstants.PAGE_SIZE*perspectiveForm.getPage();
       request.setAttribute("form",perspectiveForm);
       if(perspectiveForm.getLanguage()!=null&&perspectiveForm.getLanguage().length()==0)
            perspectiveForm.setLanguage("vi");
       if(perspectiveForm.getSearchText()!=null&&perspectiveForm.getSearchText().length()==0)
            perspectiveForm.setSearchText(null);         
       request.setAttribute("perspectives",service.findPerspectives(perspectiveForm.getLanguage(),perspectiveForm.getSearchText(),perspectiveForm.getParentId(),offset, limit));       
       String nav = Page.pageNavigator(perspectiveForm.getPage(),CmsConstants.PAGE_SIZE,service.getPerspectivesNumber(perspectiveForm.getLanguage(),perspectiveForm.getSearchText(),perspectiveForm.getParentId()));
       request.setAttribute("navigator", nav);  
       if(perspectiveForm.getParentId()>0){       
            request.setAttribute("uplevel",service.getPerspectiveById(perspectiveForm.getParentId()).getParentId());
       } else 
            request.setAttribute("uplevel","0");
       List<CmsPerspective> arr = service.findPerspectives(perspectiveForm.getLanguage(),perspectiveForm.getSearchText(),perspectiveForm.getParentId(),offset, limit);
       
       return mapping.findForward("home");           
   }
    public ActionForward ShowForm(ActionMapping mapping,
                   ActionForm form, HttpServletRequest request,
                   HttpServletResponse response) throws Exception
    {
        CmsPerspectiveService service = new CmsPerspectiveService();
        CmsPerspectiveForm perspectiveForm = (CmsPerspectiveForm)form;
        if(perspectiveForm.getPerspectiveId()>0){                        
            CmsPerspective perspective = service.getPerspectiveById(perspectiveForm.getPerspectiveId());
            request.setAttribute("perspective",perspective);
        }else{
            CmsPerspective perspective = new CmsPerspective();
            perspective.setParentId(perspectiveForm.getParentId());
            perspective.setLanguage(perspectiveForm.getLanguage());
            request.setAttribute("perspective",perspective);            
        }
        request.setAttribute("searchText",perspectiveForm.getSearchText());
        return mapping.findForward("form");
    }
    public ActionForward UpdateData(ActionMapping mapping,
                   ActionForm form, HttpServletRequest request,
                   HttpServletResponse response) throws Exception
    {
        CmsPerspectiveService service = new CmsPerspectiveService();
        CmsPerspective perspective = (CmsPerspective)form;
        if(perspective.getPerspectiveId()>0){            
           service.updatePerspective(perspective);
        }else{
            service.insertPerspective(perspective);
        }
        return Search(mapping,form,request,response);        
    }
    public ActionForward Delete(ActionMapping mapping,
                   ActionForm form, HttpServletRequest request,
                   HttpServletResponse response) throws Exception
    {
        CmsPerspectiveService service = new CmsPerspectiveService();
        CmsPerspective perspective = (CmsPerspective)form;
        service.deletePerspective(perspective.getPerspectiveId());
        return Search(mapping,form,request,response);
    }
}
