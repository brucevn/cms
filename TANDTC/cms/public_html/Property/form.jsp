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
  <bean:define id="type" name="property" property="propertyType" type="java.lang.String"/>
  <script src="<%= contextRoot%>/js/jquery.js"></script>
  <script src="<%= contextRoot%>/Property/js/Property.js"></script>
  <form action="property.do" method="POST" name="property-form" id="property-form">
        <input type="hidden" id="method" value="UpdateData" name="<%= PortletUtil.portletParameter(request,"method")%>"/>        
        <input type="hidden" id="propertyId" name="<%= PortletUtil.portletParameter(request,"propertyId")%>" value="<bean:write name="property" property="propertyId"/>"/>        
        <input type="hidden" id="searchText" name="<%= PortletUtil.portletParameter(request,"searchText")%>" value="<bean:write name="searchText"/>"/>
        <input type="hidden" id="language" name="<%= PortletUtil.portletParameter(request,"language")%>" value="<bean:write name="property" property="language"/>"/>        
        <div class="form-row">
            <span class="form-title">Tên hiển thị</span>
            <input type="text" id="property-label" name="<%= PortletUtil.portletParameter(request,"propertyLabel")%>" value="<bean:write name="property" property="propertyLabel"/>"/>
        </div>
        <div class="form-row">
        <span class="form-title">Loại</span>
        <select name="<%= PortletUtil.portletParameter(request,"propertyType")%>">
            <option value="">--- Chọn ---</option>
            <option value="text" <%out.println(type.equalsIgnoreCase("text")?"selected=\"selected\"":"");%>>Text</option>
            <option value="simpledate" <%out.println(type.equalsIgnoreCase("simpledate")?"selected=\"selected\"":"");%>>Simple date</option>
            <option value="fulldate" <%out.println(type.equalsIgnoreCase("fulldate")?"selected=\"selected\"":"");%>>Full date</option>
            <option value="file" <%out.println(type.equalsIgnoreCase("file")?"selected=\"selected\"":"");%>>file</option>
            <option value="image" <%out.println(type.equalsIgnoreCase("image")?"selected=\"selected\"":"");%>>image</option>
            <option value="textarea" <%out.println(type.equalsIgnoreCase("textarea")?"selected=\"selected\"":"");%>>TextArea</option>
            <option value="selectbox" <%out.println(type.equalsIgnoreCase("selectbox")?"selected=\"selected\"":"");%>>SelectBox</option>
            <option value="checkbox"  <%out.println(type.equalsIgnoreCase("checkbox")?"selected=\"selected\"":"");%>>CheckBox</option>
        </select>
        </div>        
         <div class="form-action">
            <input type="button" onclick="update();" value="Cập nhật"/>
            <input type="button" value="Trở về" onclick="back();"/>
        </div>
    </form>
  </body>
</html>