<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.cms.utils.PortletUtil"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset==UTF-8"/>
    <title>form</title>
  </head>
  <body>
    <%    
        String contextRoot = request.getContextPath();
    %>    
  <script src="<%= contextRoot%>/js/jquery.js"></script>
  <script src="<%= contextRoot%>/Perspective/js/Perspective.js"></script>
  <form action="perspective.do" method="POST" name="perspective-form" id="perspective-form">
        <input type="hidden" id="method" value="UpdateData" name="<%= PortletUtil.portletParameter(request,"method")%>"/>        
        <input type="hidden" id="perspectiveId" name="<%= PortletUtil.portletParameter(request,"perspectiveId")%>" value="<bean:write name="perspective" property="perspectiveId"/>"/>
        <input type="hidden" id="parentId" name="<%= PortletUtil.portletParameter(request,"parentId")%>" value="<bean:write name="perspective" property="parentId"/>"/>
        <input type="hidden" id="searchText" name="<%= PortletUtil.portletParameter(request,"searchText")%>" value="<bean:write name="searchText"/>"/>
        <input type="hidden" id="language" name="<%= PortletUtil.portletParameter(request,"language")%>" value="<bean:write name="perspective" property="language"/>"/>
        <div class="form-row">
        <span class="form-title">Tên</span>
        <input type="text" id="perspective-name" name="<%= PortletUtil.portletParameter(request,"perspectiveName")%>" value="<bean:write name="perspective" property="perspectiveName"/>"/>
        </div>
        <div class="form-row">
        <span class="form-title">Trạng thái</span>
        <select name="<%= PortletUtil.portletParameter(request,"status")%>">
            <logic:equal value="active" name="perspective" property="status">
                <option value="active" selected="selected">Hoạt động</option>
                <option value="inactive">Không hoạt động</option>
            </logic:equal>
            <logic:equal value="inactive" name="perspective" property="status">
                <option value="active">Hoạt động</option>
                <option value="inactive" selected="selected">Không hoạt động</option>
            </logic:equal>
        </select>
        </div>
         <div class="form-action">
            <input type="button" onclick="update();" value="Cập nhật"/>
            <input type="button" value="Trở về" onclick="back();"/>
        </div>
    </form>
  </body>
</html>