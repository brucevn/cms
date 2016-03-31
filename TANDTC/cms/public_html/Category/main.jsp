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
    int upLevelId = java.lang.Integer.parseInt(request.getAttribute("uplevel").toString());
  %>    
  <script src="<%= contextRoot%>/js/jquery.js"></script>
  <script src="<%= contextRoot%>/Category/js/Category.js"></script>
  <div id="categorySearch" class="cms_search_form">
    <form action="category.do" method="POST" name="category-search" id="category-search-form">
        <input type="hidden" id="method" value="Search" name="<%= PortletUtil.portletParameter(request,"method")%>"/>        
        <input type="hidden" id="categoryId" name="<%= PortletUtil.portletParameter(request,"categoryId")%>"/>
        <input type="hidden" id="parentId" name="<%= PortletUtil.portletParameter(request,"parentId")%>" value="<bean:write name="form" property="parentId"/>"/>
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
        <input type="button" value="Tìm kiếm" onClick="search(0);"/>
    </form>
    <div><a href="JavaScript:createNewCategory();">Thêm mục mới</a></div>
    <table cellpadding="0" cellspacing="0" width="100%">
        <tr><th>STT</th><th>Tên</th><th>Thao tác</th></tr>        
        <logic:greaterThan value="0" name="form" property="parentId">
            <tr><td></td><td colspan="2"><a href="JavaScript:search(<%= upLevelId%>);">Up</a></td></tr>
        </logic:greaterThan>
        <logic:notEmpty name="categories">                         
                <logic:iterate id="category" name="categories">
                 <tr>
                    <td><%=stt++%></td>
                    <td><a href="JavaScript:showSubCategories(<bean:write name="category" property="categoryId"/>);"><bean:write name="category" property="categoryName"/></a></td>
                    <td>
                        <a href="javascript:updateCategory(<bean:write name="category" property="categoryId"/>);">
                            Update
                        </a>
                        <a href="javascript:deleteCategory(<bean:write name="category" property="categoryId"/>);">
                            Delete
                        </a>
                    </td>
                  </tr>
                </logic:iterate>                        
        </logic:notEmpty>           
         <logic:empty name="categories">
            <tr><td colspan="3">Chưa có mục nào được tạo</td></tr>
        </logic:empty>
    </table>
    <bean:write name="navigator" filter="false"/>
  </div>
  </body>
</html>