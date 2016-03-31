<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.cms.utils.PortletUtil"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF8"/>
    <title>main</title>    
  </head>
  <body>    
  <bean:define id="stt" name="form" property="page" type="java.lang.Integer"/>
  <%
    stt=com.cms.utils.CmsConstants.PAGE_SIZE*(stt-1)+1;
    String contextRoot = request.getContextPath();    
  %>    
  <script src="<%= contextRoot%>/js/jquery.js"></script>
  <script src="<%= contextRoot%>/Type/js/Type.js"></script>
  <div id="type-search" class="cms_search_form">
    <form action="type.do" method="POST" name="type-search" id="type-search-form">
        <input type="hidden" id="method" value="Search" name="<%= PortletUtil.portletParameter(request,"method")%>"/>        
        <input type="hidden" id="typeId" name="<%= PortletUtil.portletParameter(request,"typeId")%>"/>        
        <input type="hidden" id="page" name="<%= PortletUtil.portletParameter(request,"page")%>" value="<bean:write name="form" property="page"/>"/>
        <span class="search-label">Ngôn ngữ</span>         
        <select class="search-selectbox" name="<%= PortletUtil.portletParameter(request,"language")%>">                        
            <logic:equal value="en" name="form" property="language">                
                <option value="vi">Tiếng việt</option>
                <option value="en" selected="selected">Tiếng anh</option>
            </logic:equal>
            <logic:notEqual value="en" name="form" property="language">                
                <option value="vi" selected="selected">Tiếng việt</option>
                <option value="en">Tiếng anh</option>
            </logic:notEqual>
        </select>
        <span class="search-label">Từ khóa</span>
        <input class="search-textbox" id="searchText" type="text" value="<bean:write name="form" property="searchText"/>" name="<%= PortletUtil.portletParameter(request,"searchText")%>"/>
        <input type="button" value="Tìm kiếm" onClick="search();"/>
    </form>
    <div><a href="JavaScript:createNewType();">Thêm mục mới</a></div>
    <table cellpadding="0" cellspacing="0" width="100%">
        <tr><th>STT</th><th>Tên</th><th>Thao tác</th></tr>                
        <logic:notEmpty name="types">                         
                <logic:iterate id="type" name="types">
                 <tr>
                    <td><%=stt++%></td>
                    <td><a href="javascript:showProperties(<bean:write name="type" property="typeId"/>)"><bean:write name="type" property="typeName"/></a></td>
                    <td>
                        <a href="javascript:updateType(<bean:write name="type" property="typeId"/>);">
                            <img src="<%=contextRoot%>/images/edit.gif"/>
                        </a>
                        <a href="javascript:deleteType(<bean:write name="type" property="typeId"/>);">
                            <img src="<%=contextRoot%>/images/del.gif"/>
                        </a>
                        <a href="javascript:addRole(<bean:write name="type" property="typeId"/>);">
                            <img src="<%=contextRoot%>/images/plus.gif"/>
                        </a>
                    </td>
                  </tr>
                </logic:iterate>                        
        </logic:notEmpty>           
         <logic:empty name="types">
            <tr><td colspan="3">Chưa có mục nào được tạo</td></tr>
        </logic:empty>
    </table>
    <bean:write name="navigator" filter="false"/>
  </div>
  </body>
</html>