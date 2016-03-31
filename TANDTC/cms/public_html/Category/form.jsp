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
  <script src="<%= contextRoot%>/Category/js/Category.js"></script>
  <form action="category.do" method="POST" name="category-form" id="category-form">
        <input type="hidden" id="method" value="UpdateData" name="<%= PortletUtil.portletParameter(request,"method")%>"/>        
        <input type="hidden" id="categoryId" name="<%= PortletUtil.portletParameter(request,"categoryId")%>" value="<bean:write name="category" property="categoryId"/>"/>
        <input type="hidden" id="parentId" name="<%= PortletUtil.portletParameter(request,"parentId")%>" value="<bean:write name="category" property="parentId"/>"/>
        <input type="hidden" id="searchText" name="<%= PortletUtil.portletParameter(request,"searchText")%>" value="<bean:write name="searchText"/>"/>
        <input type="hidden" id="language" name="<%= PortletUtil.portletParameter(request,"language")%>" value="<bean:write name="category" property="language"/>"/>
        <div class="form-row">
        <span class="form-title">Tên </span>
        <input type="text" id="category-name" name="<%= PortletUtil.portletParameter(request,"categoryName")%>" value="<bean:write name="category" property="categoryName"/>"/>
        </div>
        <div class="form-row">
        <span class="form-title">Trạng thái</span>
        <select name="<%= PortletUtil.portletParameter(request,"status")%>">
            <logic:equal value="active" name="category" property="status">
                <option value="active" selected="selected">Hoạt động</option>
                <option value="inactive">Không hoạt động</option>
            </logic:equal>
            <logic:equal value="inactive" name="category" property="status">
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