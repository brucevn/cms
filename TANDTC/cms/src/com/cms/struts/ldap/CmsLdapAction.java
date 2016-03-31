package com.cms.struts.ldap;

import com.cms.models.CmsUserGroup;
import com.cms.services.CmsLdapService;
import com.cms.utils.CmsConstants;
import com.cms.utils.Page;

import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CmsLdapAction extends org.apache.struts.action.Action{
    public CmsLdapAction() {
        super();
    }
    public ActionForward execute(ActionMapping mapping,
                    ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
    {       
        CmsLdapService service = new CmsLdapService();
        CmsLdapForm _form = (CmsLdapForm)form;      
        PrintWriter out = response.getWriter();              
        request.setAttribute("form",_form);     
        String nav="";
        String[] temp=null;
        List list = new ArrayList();
        if(_form.getType().equalsIgnoreCase("user")){
            nav = Page.pageNavigator(_form.getPage(),CmsConstants.PAGE_SIZE,service.countPortalUsers(_form.getText()));   
            temp=service.getPortalUsers(_form.getStart(),_form.getEnd(),_form.getText());
        }else{
            nav = Page.pageNavigator(_form.getPage(),CmsConstants.PAGE_SIZE,service.countPortalGroups(_form.getText()));   
            temp=service.getPortalGroups(_form.getStart(),_form.getEnd(),_form.getText());
        }
        if(temp!=null){
            for(int i=0;i<temp.length;i++){
                String[] arrStr=temp[i].split("/");
                CmsUserGroup obj = new CmsUserGroup();
                obj.setId(arrStr[0]);
                obj.setName(arrStr[1]);
                obj.setExtension(arrStr[2]);
                list.add(obj);
            }
        }
        request.setAttribute("list",list);
        request.setAttribute("navigator", nav);                 
        return mapping.findForward("home");        
    }
}
