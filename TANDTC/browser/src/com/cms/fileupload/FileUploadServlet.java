package com.cms.fileupload;
 
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.cms.models.CmsFile;
import com.cms.services.CmsFileService;


public class FileUploadServlet extends HttpServlet {
 
    private static final long serialVersionUID = 1L;
 
    /***************************************************
     * URL: /upload
     * doPost(): upload the files and other parameters
     ****************************************************/
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{ 
        List<CmsFile> files=MultipartRequestHandler.uploadByApacheFileUpload(request,response); 
        // 2. Set response type to json
        response.setContentType("application/json");
 
        // 3. Convert List<FileMeta> into JSON format
        ObjectMapper mapper = new ObjectMapper();
 
        // 4. Send resutl to client
        mapper.writeValue(response.getOutputStream(), files);
    }
    /***************************************************
     * URL: /upload?f=value
     * doGet(): get file of index "f" from List<CmsFile> as an attachment
     ****************************************************/
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
 
         int fileId = Integer.parseInt(request.getParameter("fileId").toString());
 
         CmsFileService service = new CmsFileService();
         CmsFile getFile = service.getFile(fileId); 
         try {        
                 
                response.setContentType(getFile.getFileType());                  
                response.setHeader("Content-disposition", "attachment; filename=\""+getFile.getFileName()+"\"");               
                OutputStream output = response.getOutputStream();
                output.write(getFile.getContent());
                output.close();
         }catch (IOException e) {
                e.printStackTrace();
         }
 
    }
}