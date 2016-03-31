<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.cms.utils.PortletUtil"%>
<%@ page import="com.cms.utils.CmsConstants"%>
<%@ page import="com.cms.models.CmsTypeProperty"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset==UTF8"/>
    <title>properties</title>
  </head>
  <body>
    <%    
        String contextRoot = request.getContextPath();   
        int stt=0;        
    %> 
    <script src="<%= contextRoot%>/js/jquery.js"></script>
    <script src="<%= contextRoot%>/js/utils.js"></script>
   <script src="<%= contextRoot%>/Type/js/Type.js"></script>
   <bean:define id="size" name="NumOfProperties" type="java.lang.Integer"/>
    <table cellpadding="0" cellspacing="0" border="1" class="type-properties" width="100%">
    <tr><th>STT</th><th>Tên hiển thị</th><th>Trường bắt buộc</th><th>Thao tác</th></tr>
    <logic:notEmpty name="typeProperties">
        <logic:iterate id="typeProperty" name="typeProperties" type="CmsTypeProperty">                        
        <tr>
            <td><%= ++stt%></td>
            <td>        
                <bean:define id="property" name="typeProperty" property="cmsProperty" type="com.cms.models.CmsProperty"/>
                <c:choose>
                    <c:when test="<%= property.getPropertyId() == CmsConstants.TITLE_ID%>">
                        <bean:write name="type" property="titleLabel"/>
                    </c:when>
                    <c:when test="<%= property.getPropertyId() == CmsConstants.CONTENT_ID%>">
                        <bean:write name="type" property="contentLabel"/>
                    </c:when>
                    <c:when test="<%= property.getPropertyId() == CmsConstants.PERSPECTIVE_ID%>">
                        Perspective
                    </c:when>
                    <c:when test="<%= property.getPropertyId() == CmsConstants.CATEGORY_ID%>">
                        Category
                    </c:when>
                    <c:when test="<%= property.getPropertyId() == CmsConstants.PUBLISHDATE_ID%>">
                        <bean:message key="PUBLISH_DATE"/>
                    </c:when>
                    <c:otherwise>
                        <bean:write name="property" property="propertyLabel"/>
                    </c:otherwise>
                </c:choose>            
            </td>
            <td>
                <c:choose>
                    <c:when test="<%=property.getPropertyId() == CmsConstants.TITLE_ID%>">
                        Có
                    </c:when>
                    <c:otherwise>
                    <a href="JavaScript:require(<%=typeProperty.getId()%>);">
                            <logic:equal value="1" name="typeProperty" property="isRequired">
                                Có
                            </logic:equal>
                            <logic:equal value="0" name="typeProperty" property="isRequired">
                                Không
                            </logic:equal>
                        </a>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:if test="<%= stt>2%>">
                    <a href="JavaScript:up(<%=typeProperty.getId()%>)">Lên</a>
                </c:if>
                <c:if test="<%= stt<size&&stt>1%>">
                    <a href="JavaScript:down(<%=typeProperty.getId()%>)">Xuống</a>
                </c:if>
                <c:if test="<%=!PortletUtil.isDefaultProperty(property.getPropertyId())%>">
                    <a href="JavaScript:DeleteProperty(<%=typeProperty.getId()%>)">xóa</a>
                </c:if>
            </td>
        </tr>
        </logic:iterate>
    </logic:notEmpty>
    </table>
    <form action="type.do" method="POST" id="type-properties-form">
        <input type="hidden" id="method" value="insertProperty" name="<%= PortletUtil.portletParameter(request,"method")%>"/>        
        <input type="hidden" id="typeId" name="<%= PortletUtil.portletParameter(request,"typeId")%>" value="<bean:write name="form" property="typeId"/>"/>        
        <input type="hidden" id="typePropertyId" name="<%= PortletUtil.portletParameter(request,"typePropertyId")%>" value=""/>        
        <input type="hidden" id="searchText" name="<%= PortletUtil.portletParameter(request,"searchText")%>" value="<bean:write name="form" property="searchText"/>"/>
        <input type="hidden" id="language" name="<%= PortletUtil.portletParameter(request,"language")%>" value="<bean:write name="form" property="language"/>"/>
        <span class="row-title">
            Thuộc tính
        </span>
        <select name="<%= PortletUtil.portletParameter(request,"propertyId")%>">
            <option value="0">--- Chọn ---</option>
            <logic:notEmpty name="typeProperties">
                <logic:iterate id="property" name="properties">
                    <option value="<bean:write name="property" property="propertyId"/>">                        
                        <bean:write name="property" property="propertyLabel"/>
                    </option>
                </logic:iterate>
            </logic:notEmpty>
        </select>
        <span class="row-title">Bắt buộc</span>
        <input type="checkbox" name="<%= PortletUtil.portletParameter(request,"isRequired")%>" value="1"/>
        <input type="submit" value="Thêm"/>
        <input type="button" value="Quay lại" onclick="back();"/>
    </form>
    </body>
</html>