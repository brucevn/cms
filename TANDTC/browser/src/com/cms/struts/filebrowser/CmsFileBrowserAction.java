package com.cms.struts.filebrowser;


import com.cms.models.CmsFile;
import com.cms.models.CmsFolder;
import com.cms.services.CmsFileService;

import java.io.OutputStream;

import java.io.PrintWriter;

import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class CmsFileBrowserAction extends DispatchAction{
    public CmsFileBrowserAction() {
        super();
    }
    public ActionForward showFiles(ActionMapping mapping,
                   ActionForm form, HttpServletRequest request,
                   HttpServletResponse response) throws Exception
    {
        CmsFileService service = new CmsFileService();
        CmsFileBrowserForm _form = (CmsFileBrowserForm)form;
        List idList = service.getDescendents(_form.getDir());
        Map<String, Object> map = new HashMap<String, Object>();
        if(_form.getType()!=null&&_form.getType().length()==0){
            _form.setType(null);
        }
        map.put("ids", service.getDescendents(_form.getDir()));
        map.put("name",_form.getName());
        map.put("type",_form.getType());
        int count = service.countFiles(map);
        int maxPage=count%20==0?count/20:count/20+1;
        if(_form.getPage()<0)_form.setPage(1);
        if(_form.getPage()>maxPage) _form.setPage(maxPage);
        int start=(_form.getPage()-1)*20;
        int amount=20;
        List files = service.getFiles(map,start,20); 
        request.setAttribute("files", files);
        request.setAttribute("page",_form.getPage());
        request.setAttribute("maxPage", maxPage);
        request.setAttribute("_form",_form);
        return mapping.findForward("files");
    }
    public ActionForward showFolders(ActionMapping mapping,
                   ActionForm form, HttpServletRequest request,
                   HttpServletResponse response) throws Exception
    {
        CmsFileService service = new CmsFileService();
        CmsFileBrowserForm _form = (CmsFileBrowserForm)form;
        List folders = service.getSubFolders(_form.getDir());
        request.setAttribute("folders",folders);
        return mapping.findForward("folders");
    }
    public ActionForward image(ActionMapping mapping,
                   ActionForm form, HttpServletRequest request,
                   HttpServletResponse response) throws Exception
    {
        try{
            CmsFileService service = new CmsFileService();
            CmsFileBrowserForm _form = (CmsFileBrowserForm)form;
            CmsFile file = service.getFile(_form.getId());
            response.setContentType(file.getFileType());
            OutputStream o = response.getOutputStream();
            o.write(file.getContent());
            o.flush();
            o.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public ActionForward createFolder(ActionMapping mapping,
                   ActionForm form, HttpServletRequest request,
                   HttpServletResponse response) throws Exception
    {
        PrintWriter out = response.getWriter();
        try{
            CmsFileService service = new CmsFileService();
            CmsFileBrowserForm _form = (CmsFileBrowserForm)form;
            CmsFolder folder= new CmsFolder();
            folder.setParentId(_form.getDir());
            folder.setOwner(_form.getOwner());
            folder.setFolderName(_form.getFolderName());
            service.insertFolder(folder);            
            out.println(folder.getFolderId());
        }catch(Exception e){
            e.printStackTrace();
            out.println(0);
        }
        return null;
    }
    
    public ActionForward deleteFolder(ActionMapping mapping,
                   ActionForm form, HttpServletRequest request,
                   HttpServletResponse response) throws Exception
    {
        PrintWriter out = response.getWriter();
        try{
            CmsFileService service = new CmsFileService();
            CmsFileBrowserForm _form = (CmsFileBrowserForm)form;
            CmsFolder folder = service.getFolder(_form.getDir());
            int parentId = folder.getParentId();
            int delete=service.deleteFolder(_form.getDir());
            if(delete>0){
                out.println(parentId);
            }
        }catch(Exception e){
            e.printStackTrace();
            out.println(-1);
        }
        return null;
    }
    
}
