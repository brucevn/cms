<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.cms.utils.PortletUtil"%>
<%@ page import="com.cms.utils.CmsConstants"%>
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
  <bean:define id="usePerspective" name="type" property="usePerspective" type="java.lang.Byte"/>
  <bean:define id="useCategory" name="type" property="useCategory" type="java.lang.Byte"/>
  <script src="<%= contextRoot%>/js/jquery.js"></script>
  <script src="<%= contextRoot%>/Type/js/Type.js"></script>
  <form action="type.do" method="POST" name="type-form" id="type-form">
        <input type="hidden" id="method" value="UpdateData" name="<%= PortletUtil.portletParameter(request,"method")%>"/>        
        <input type="hidden" id="typeId" name="<%= PortletUtil.portletParameter(request,"typeId")%>" value="<bean:write name="type" property="typeId"/>"/>        
        <input type="hidden" id="searchText" name="<%= PortletUtil.portletParameter(request,"searchText")%>" value="<bean:write name="searchText"/>"/>
        <input type="hidden" id="language" name="<%= PortletUtil.portletParameter(request,"language")%>" value="<bean:write name="type" property="language"/>"/>
        <input type="hidden" id="tokenId" name="<%= PortletUtil.portletParameter(request,CmsConstants.TOKEN_ID)%>" value="<bean:write name="token" property="id"/>"/>
        <input type="hidden" id="token" name="<%= PortletUtil.portletParameter(request,CmsConstants.TOKEN_VALUE)%>" value="<bean:write name="token" property="token"/>"/>
        <div class="form-row">
        <span class="form-title">Tên</span>
            <input property="text" id="type-name" name="<%= PortletUtil.portletParameter(request,"typeName")%>" value="<bean:write name="type" property="typeName"/>"/>
        </div>
        <div class="form-row">
            <span class="form-title">Miêu tả</span>
            <textarea cols="30" rows="5" id="type-description" name="<%= PortletUtil.portletParameter(request,"typeDescription")%>"><bean:write name="type" property="typeDescription"/></textarea>
        </div>
        <div class="form-row">
            <span class="form-title">Nhãn của trường tiêu đề</span>
            <input property="text" id="type-title-label" name="<%= PortletUtil.portletParameter(request,"titleLabel")%>" value="<bean:write name="type" property="titleLabel"/>"/>
        </div>
        <div class="form-row">
            <span class="form-title">Nhãn của trường nội dung</span>
            <input property="text" id="type-content-label" name="<%= PortletUtil.portletParameter(request,"contentLabel")%>" value="<bean:write name="type" property="contentLabel"/>"/>
        </div>
        <div class="form-row">
            <span class="form-title">Sử dụng phối cảnh (Perspective)</span>
            <input type="checkbox" value="1" id="use-perspective" name="<%= PortletUtil.portletParameter(request,"usePerspective")%>" <% out.println(usePerspective==1?"checked=\"checked\"":"");%>/>
        </div>
        <div class="form-row">
            <span class="form-title">Sử dụng danh mục (Category)</span>
            <input type="checkbox" value="1" id="use-category" name="<%= PortletUtil.portletParameter(request,"useCategory")%>" <% out.println(useCategory==1?"checked=\"checked\"":"");%>/>
        </div>
        <div class="form-row">
        <span class="form-title">Trạng thái</span>
        <select name="<%= PortletUtil.portletParameter(request,"status")%>">
            <logic:equal value="active" name="type" property="status">
                <option value="active" selected="selected">Hoạt động</option>
                <option value="inactive">Không hoạt động</option>
            </logic:equal>
            <logic:equal value="inactive" name="type" property="status">
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