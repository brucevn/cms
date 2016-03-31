<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.cms.utils.PortletUtil,com.cms.models.*"%>

<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datetime-1.0"
           prefix="datetime"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>main</title>
    <script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.js"></script>
      <script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery-ui.custom.js"></script>
      <script type="text/javascript" src="<%= request.getContextPath()%>/js/utils.js"></script>
      <script type="text/javascript" src="<%= request.getContextPath()%>/js/common.js"></script>
      <script type="text/javascript" src="<%= request.getContextPath()%>/js/datetimepicker.js"></script>
      <script type="text/javascript" src="<%= request.getContextPath()%>/ckeditor/ckeditor.js"></script>
      <script type="text/javascript" src="<%= request.getContextPath()%>/ckeditor/adapters/jquery.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath()%>/Item/js/item.js"></script>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/css/jquery-ui.css"/>
  <link rel="stylesheet" href="<%= request.getContextPath()%>/css/datetimepicker.css"/>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/css/cms.css"/>
  </head>
  <body>
  <div id="main-content">
    <form action="item.do" method="POST" id="item-form">  
      <input type="hidden" value="Search" id="method" name="<%= PortletUtil.portletParameter(request,"method")%>"/>
      <input type="hidden" value="0" id="itemId" name="<%= PortletUtil.portletParameter(request,"itemId")%>"/>
      <table id="item-search" cellpadding="0" cellspacing="0" width="100%">
        <tr>
            <td class="search-label">Ngôn ngữ</td>
            <td>
                <select onchange="Search();" class="search-selectbox" name="<%= PortletUtil.portletParameter(request,"language")%>">                        
                    <logic:equal value="en" name="form" property="language">                
                        <option value="vi">Tiếng việt</option>
                        <option value="en" selected="selected">Tiếng anh</option>
                    </logic:equal>
                    <logic:notEqual value="en" name="form" property="language">                
                        <option value="vi" selected="selected">Tiếng việt</option>
                        <option value="en">Tiếng anh</option>
                    </logic:notEqual>
                </select>
            </td>
            <td class="search-label">
                Loại
            </td>
            <td>
                <select class="search-selectbox" name="<%= PortletUtil.portletParameter(request,"type")%>">
                    <option value="">--- Chọn ---</option>
                    <logic:notEmpty name="types">
                        <logic:iterate id="type" name="types">
                            <bean:define id="typeId" name="type" property="typeId"/>
                            <logic:equal value="${requestScope.form.type}" name="typeId">
                                <option value="${typeId}" selected="selected"><bean:write name="type" property="typeName"/></option>
                            </logic:equal>
                            <logic:notEqual value="${requestScope.form.type}" name="typeId">
                                <option value="${typeId}"><bean:write name="type" property="typeName"/></option>
                            </logic:notEqual>                    
                        </logic:iterate>
                    </logic:notEmpty>
                </select>
            </td>
        </tr>
        <tr>
            <td class="search-label">Ngữ cảnh</td>
            <td>
                <select class="search-selectbox" name="<%= PortletUtil.portletParameter(request,"perspective")%>">
                    <option value="">--- Chọn ---</option>
                    <logic:notEmpty name="perspectives">
                        <logic:iterate id="perspective" name="perspectives">
                            <bean:define id="perspectiveId" name="perspective" property="perspectiveId"/>
                            <logic:equal value="${requestScope.form.perspective}" name="perspectiveId">
                                <option value="${perspectiveId}" selected="selected"><bean:write name="perspective" property="perspectiveName"/></option>
                            </logic:equal>
                            <logic:notEqual value="${requestScope.form.perspective}" name="perspectiveId">
                                <option value="${perspectiveId}"><bean:write name="perspective" property="perspectiveName"/></option>
                            </logic:notEqual>                    
                        </logic:iterate>
                    </logic:notEmpty>
                </select>
            </td>
            <td class="search-label">Danh mục</td>
            <td>
                <select class="search-selectbox" name="<%= PortletUtil.portletParameter(request,"category")%>">
                    <option value="">--- Chọn ---</option>
                    <logic:notEmpty name="categories">
                        <logic:iterate id="category" name="categories">
                            <bean:define id="categoryId" name="category" property="categoryId"/>
                            <logic:equal value="${requestScope.form.category}" name="categoryId">
                                <option value="${categoryId}" selected="selected"><bean:write name="category" property="categoryName"/></option>
                            </logic:equal>
                            <logic:notEqual value="${requestScope.form.category}" name="categoryId">
                                <option value="${categoryId}"><bean:write name="category" property="categoryName"/></option>
                            </logic:notEqual>                    
                        </logic:iterate>
                    </logic:notEmpty>
                </select>
            </td>
        </tr>
        <tr>
            <td>Từ khóa</td>
            <td>
                <input type="text" value="${requestScope.form.searchText}" name="<%=PortletUtil.portletParameter(request,"searchText")%>"/>
            </td>
            <td>Ngày xuất bản</td>
            <td>
            Từ ngày&nbsp;<input class="cms-simpledate" id="fromDate" type="text" value="${requestScope.form.fromDate}" name="<%=PortletUtil.portletParameter(request,"fromDate")%>"/>
            &nbsp;Đến ngày&nbsp;<input class="cms-simpledate" id="toDate" type="text" value="${requestScope.form.toDate}" name="<%=PortletUtil.portletParameter(request,"toDate")%>"/>
            </td>
        </tr>
        <tr>
            <td align="center"><input type="button" value="Tìm kiếm" onclick="Search();"/></td>
        </tr>
      </table>
    </form>
    <div class="add-new-holder"><a href="javascript:addNewItems();">Thêm mới</a></div>
    <logic:notEmpty name="items">    
      <table class="backend-table" cellpadding="0" cellspacing="0" width="100%">
        <tr>
            <th>STT</th>
            <th>Tiêu đề</th>
            <th>Người tạo</th>
            <th>Ngày xuất bản</th>
            <th>Người cập nhật</th>            
            <th>Ngày cập nhật</th>
            <th>Trạng thái</th>
            <th>Thao tác</th>
        </tr>       
        <logic:iterate name="items" id="item" indexId="stt" type="CmsItem" >
            <tr align="center">
                <td><c:out value="${stt+1}"/></td>
                <td align="left"><bean:write name="item" property="title"/></td>
                <td><bean:write name="item" property="owner"/></td>
                <td>
                    <logic:present name="item" property="publishDate">
                        <datetime:format pattern="dd/MM/yyyy HH:mm" date="${item.publishDate}"/>
                    </logic:present>
                </td>            
                <td><bean:write name="item" property="updater"/></td>            
                <td>
                    <datetime:format pattern="dd/MM/yyyy HH:mm" date="${item.updatedDate}"/>                    
                </td>            
                <td><bean:write name="item" property="statusValue"/></td>
                <td class="actio-holder">
                    <a href="javascript:editItem(${item.itemId})">
                        <img src="<%=request.getContextPath()%>/images/edit.gif"/>
                    </a>
                    <a href="javascript:deleteItem(${item.itemId})">
                        <img src="<%=request.getContextPath()%>/images/del.gif"/>
                    </a>
                </td>
            </tr>
        </logic:iterate>
      </table>
      </logic:notEmpty>
      <logic:empty name="items">    
            Không tìm thấy kết quả nào!
      </logic:empty>
    </div>
    <bean:write name="navigator" filter="false"/>
  </body
</html>