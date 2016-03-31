<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.cms.utils.PortletUtil,com.cms.models.*"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>ChooseType</title>
  </head>
  <body>
  <form action="item.do" method="POST" name="ChooseTypeForm" id="ChooseTypeForm">
      <input type="hidden" value="ShowForm" name="<%= PortletUtil.portletParameter(request,"method")%>"/>
      <logic:notEmpty name="types">
        <logic:iterate id="type" name="types" indexId="stt">
            <div>              
                <logic:equal value="0" name="stt">
                <input type="radio" checked="checked" name="<%= PortletUtil.portletParameter(request,"typeId")%>" value="${type.typeId}"/>
                </logic:equal>
                <logic:notEqual value="0" name="stt">
                    <input type="radio" name="<%= PortletUtil.portletParameter(request,"typeId")%>" value="${type.typeId}"/>
                </logic:notEqual>
                <span><bean:write name="type" property="typeName"/></span>
            </div>
        </logic:iterate>        
      </logic:notEmpty>
      <input type="hidden" name="<%= PortletUtil.portletParameter(request,"language")%>" value="${requestScope.form.language}"/>
      <input type="hidden" name="<%= PortletUtil.portletParameter(request,"type")%>" value="${requestScope.form.type}"/>
      <input type="hidden" name="<%= PortletUtil.portletParameter(request,"perspective")%>" value="${requestScope.form.perspective}"/>
      <input type="hidden" name="<%= PortletUtil.portletParameter(request,"category")%>" value="${requestScope.form.category}"/>
      <input type="hidden" name="<%= PortletUtil.portletParameter(request,"searchText")%>" value="${requestScope.form.searchText}"/>
      <input type="hidden" name="<%= PortletUtil.portletParameter(request,"fromDate")%>" value="${requestScope.form.fromDate}"/>
      <input type="hidden" name="<%= PortletUtil.portletParameter(request,"toDate")%>" value="${requestScope.form.toDate}"/>
      <input type="submit" value="Tạo mới"/>
    </form>
  </body>
</html>