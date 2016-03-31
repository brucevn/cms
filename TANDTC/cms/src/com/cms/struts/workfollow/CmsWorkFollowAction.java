package com.cms.struts.workfollow;


import com.cms.models.CmsWorkFollow;
import com.cms.services.CmsWorkFollowService;
import com.cms.struts.CommonAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class CmsWorkFollowAction extends CommonAction {
    public ActionForward Search(ActionMapping mapping,
                    ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
    {       
        CmsWorkFollowService service = new CmsWorkFollowService();
        CmsWorkFollowForm wForm = (CmsWorkFollowForm)form;         
        List<CmsWorkFollow> list=service.getAllWorkFollows(wForm.getTxtName());
        request.setAttribute("list",list);
        request.setAttribute("form",wForm);
        return mapping.findForward("home");           
    }
    public ActionForward form(ActionMapping mapping,
                    ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
    {       
        CmsWorkFollowService service = new CmsWorkFollowService();
        CmsWorkFollowForm wForm = (CmsWorkFollowForm)form; 
        CmsWorkFollow workFollow = service.getWorkFollowById(wForm.getId());
        if(workFollow==null)workFollow=new CmsWorkFollow();
        saveToken(request);
        request.setAttribute("workfollow",workFollow);
        request.setAttribute("form",wForm);
        return mapping.findForward("form");           
    }
    public ActionForward update(ActionMapping mapping,
                    ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
    {       
        CmsWorkFollowService service = new CmsWorkFollowService();
        CmsWorkFollow workFollow = (CmsWorkFollow)form;
        if(isValidToken(request)){
            if(workFollow.getId()==0){
                service.insertWorkFollow(workFollow);
            }else{
                service.updateWorkFollow(workFollow);
            }
            resetToken(request);
        }
        return Search(mapping,form,request,response);
    }
    public ActionForward delete(ActionMapping mapping,
                    ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
    {       
        CmsWorkFollowService service = new CmsWorkFollowService();
        CmsWorkFollow workFollow = (CmsWorkFollow)form;
        service.deleteWorkFollow(workFollow);
        return Search(mapping,form,request,response);
    }
    public ActionForward showSteps(ActionMapping mapping,
                    ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
    {       
        CmsWorkFollowService service = new CmsWorkFollowService();
        CmsWorkFollowForm _form = (CmsWorkFollowForm)form;
        CmsWorkFollow workFollow = service.getWorkFollowById(_form.getId());
        request.setAttribute("workFollow",workFollow);
        return mapping.findForward("steps");
    }
}
