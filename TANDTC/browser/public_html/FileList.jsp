<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.cms.utils.PortletUtil"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String contextPath = request.getContextPath();
%>
<div id="first-position" style="display:none"></div>
<logic:notEmpty name="files">
        <logic:iterate name="files" id="file" type="com.cms.models.CmsFile">
            <logic:equal name="file" property="isImage" value="1">
                <a class="item file_entry" href="javascript:void(0);" onClick="javascript:selectFile(<bean:write name="file" property="fileId"/>,'<bean:write name="file" property="fileName"/>');" role="listiem presentation" title="<bean:write name="file" property="fileName"/>">
                    <img src="<%= contextPath%>/browser.do?method=image&id=<bean:write name="file" property="fileId"/>" border="0" width="96px" height="96px"/>
                    <span><bean:write name="file" property="shortName"/></span>
                    <span><bean:write name="file" property="fileSize"/>&nbsp;KB</span>
                </a>
            </logic:equal>
            <logic:equal value="0" name="file" property="isImage">
                <a class="item file_entry" href="javascript:void(0);" onClick="javascript:selectFile(<bean:write name="file" property="fileId"/>,'<bean:write name="file" property="fileName"/>');" role="listiem presentation" title="<bean:write name="file" property="fileName"/>">
                    <img src="<%= contextPath%>/sysadmin/images/ext/<bean:write name="file" property="fileExt"/>_ext.png" border="0" width="96px" height="96px"/>
                    <span><bean:write name="file" property="shortName"/></span>
                    <span><bean:write name="file" property="fileSize"/>&nbsp;KB</span>
                </a>
            </logic:equal>
        </logic:iterate>        
        <c:if test="${requestScope.maxPage>1}">            
            <div class="phantrang">
                <span>Trang&nbsp;</span>
                <c:choose>
                <c:when test="${requestScope.page==1}">
                    <span class="invisible"><<</span>&nbsp;&nbsp;
                    <c:out value="${requestScope.page}"/>/<c:out value="${requestScope.maxPage}"/>
                    <a class="pageLink" href="javascript:showFiles(${requestScope._form.dir},parseInt(${requestScope.page})+1,'${param.name}');">&nbsp;&nbsp;>></a>
                </c:when>
                 <c:when test="${requestScope.page>1&&requestScope.page<requestScope.maxPage}">
                        <a class="pageLink" href="javascript:showFiles(${requestScope._form.dir},parseInt(${requestScope.page})-1,'${param.name}');"><<</a>&nbsp;&nbsp;
                        <c:out value="${requestScope.page}"/>/<c:out value="${requestScope.maxPage}"/>
                        <a class="pageLink" href="javascript:showFiles(${requestScope._form.dir},parseInt(${requestScope.page})+1,'${param.name}');">&nbsp;&nbsp;>></a>
                   </c:when>
                   <c:otherwise>
                        <a class="pageLink" href="javascript:showFiles(${requestScope._form.dir},parseInt(${requestScope.page})-1,'${param.name}');"><<</a>&nbsp;&nbsp;
                        <c:out value="${requestScope.page}"/>/<c:out value="${requestScope.maxPage}"/>
                        <span class="invisible">&nbsp;&nbsp;>></span>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:if>
</logic:notEmpty>

