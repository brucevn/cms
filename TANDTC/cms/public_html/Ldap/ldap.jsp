<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.cms.utils.PortletUtil,com.cms.models.CmsUserGroup"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datetime-1.0"
           prefix="datetime"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>ldap</title>
    <script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath()%>/js/common.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath()%>/Ldap/js/ldap.js"></script>    
  </head>
  <body>
      <html:form action="/ldap.do" method="post">
            <input type="text" value="${requestScope.form.text}" name="<%=PortletUtil.portletParameter(request,"text")%>" id="text"/>
            <input type="hidden" value="${requestScope.form.type}" name="<%=PortletUtil.portletParameter(request,"type")%>" id="type"/>
            <input type="submit" value="Tìm kiếm"/>
            <input type="hidden" name="<%=PortletUtil.portletParameter(request,"page")%>" id="page" value="1"/>
            <table cellpadding="0" cellspacing="0" border="1" width="100%" style="border-collapse:collapse;">
                <tr>
                <th>Chọn</th>
                <th>
                    <logic:equal value="user" name="form" property="type">
                        Người dùng
                    </logic:equal>
                    <logic:equal value="group" name="form" property="type">
                        Nhóm người dùng
                    </logic:equal>
                </th>
                <th>
                    <logic:equal value="user" name="form" property="type">
                        Email
                    </logic:equal>
                    <logic:equal value="group" name="form" property="type">
                        Mô tả
                    </logic:equal>
                </th>
                </tr>
                <logic:iterate id="obj" name="list" type="CmsUserGroup">
                    <tr>
                        <td><input type="radio" name="select" onclick="selectObj('${obj.id}','${obj.name}');"/></td>
                        <td><bean:write name="obj" property="name"/></td>
                        <td><bean:write name="obj" property="extension"/></td>
                    </tr>
                </logic:iterate>
            </table>
        </html:form>
        <div<bean:write name="navigator" filter="false"/>
    </body>
</html>