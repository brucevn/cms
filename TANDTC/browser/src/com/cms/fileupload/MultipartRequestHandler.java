package com.cms.fileupload;
 
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
 
import com.cms.models.CmsFile;
import com.cms.services.CmsFileService;

import java.io.PrintWriter;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

public class MultipartRequestHandler {
 
    public static List<CmsFile> uploadByApacheFileUpload(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
 
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        CmsFile temp = null;
        List<CmsFile> files = new LinkedList<CmsFile>();        
        if(isMultipart){ 
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
 
            try {
 
                List<FileItem> items = upload.parseRequest(request);
                int folderId = 0;
                String owner="";
                int isPublic = 1;
 
                for(FileItem item:items){ 
                    if (item.isFormField()) {                         
                        if(item.getFieldName().equals("id")){
                            folderId = Integer.parseInt(item.getString("UTF-8"));
                        }
                        if(item.getFieldName().equals("owner")){
                            owner = item.getString("UTF-8");
                        }
 
                    }else {                         
                        temp = new CmsFile();
                        temp.setFileName(item.getName());                        
                        temp.setContent(item.get());
                        temp.setFileType(item.getContentType());
                        temp.setFileSize((int)item.getSize()/1024);  
                        String fileName=item.getName();
                        temp.setFileExt(fileName.substring(fileName.lastIndexOf(".")+1));                        
                        temp.setOwner(owner);                        
                        temp.setFolderId(folderId);
                        temp.setIsPublic(1);
                        temp.isImage();                        
                        new CmsFileService().insertFile(temp);
                        files.add(temp);
                    }
                }
 
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }
        return files;
    }
 }