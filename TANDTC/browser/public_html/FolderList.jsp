<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<logic:notEmpty name="folders">
    <ul class="jqueryFileTree" style="display: none;">
        <logic:iterate id="folder" name="folders">            
           <li class="directory collapsed" id="<bean:write name="folder" property="folderId"/>"><a href="#" rel="<bean:write name="folder" property="folderId"/>"><bean:write name="folder" property="folderName"/></a><div id="sub<bean:write name="folder" property="folderId"/>"></div></li>
        </logic:iterate>
     </ul>
</logic:notEmpty>


                    