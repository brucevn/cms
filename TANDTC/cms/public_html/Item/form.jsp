<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.cms.utils.PortletUtil,com.cms.models.*"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.cms.utils.CmsConstants"%>
<%@ page import="com.cms.utils.PortletUtil"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>form</title>
  </head>
  <body>
  <%
    String l_user="trungbx";
  %>
  <script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.js"></script>
  <script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery-ui.custom.js"></script>
  <script type="text/javascript" src="<%= request.getContextPath()%>/js/utils.js"></script>
  <script type="text/javascript" src="<%= request.getContextPath()%>/js/common.js"></script>
  <script type="text/javascript" src="<%= request.getContextPath()%>/js/datetimepicker.js"></script>
  <script type="text/javascript" src="<%= request.getContextPath()%>/ckeditor/ckeditor.js"></script>
  <script type="text/javascript" src="<%= request.getContextPath()%>/Item/js/item.js"></script>  
  <script type="text/javascript" src="<%= request.getContextPath()%>/ckeditor/adapters/jquery.js"></script>
  <script src="<%= request.getContextPath()%>/js/validate/jquery.validate.js"></script>
    <script src="<%= request.getContextPath()%>/js/validate/localization/messages_vi.js"></script>
    <script >
    $(document).ready(function() {
        $('#item-form').validate({
            ignore: ".ignore"
        });
    });
    </script>
  <link rel="stylesheet" href="<%= request.getContextPath()%>/css/jquery-ui.css"/>
  <link rel="stylesheet" href="<%= request.getContextPath()%>/css/datetimepicker.css"/>
  <link rel="stylesheet" href="<%= request.getContextPath()%>/css/cms.css"/>
  <form action="item.do" method="POST" name="item-form" id="item-form">
    <bean:define id="item" name="item" type="CmsItem"/>
    <input type="hidden" name="method" id="method" value="update"/>
    <input type="hidden" name="itemId" id="itemId" value="${requestScope.item.itemId}"/>
    <table width="100%" cellpadding="0" cellspacing="0" id="item-table" class="cms-backend-form">
        <logic:iterate id="propertyValue" name="propertyValues" type="CmsPropertyValue">           
            <tr>
                <td>
                    <bean:write name="propertyValue" property="propertyLabel"/>
                    <c:if test="${propertyValue.required}">
                        <span class="required">*</span>
                    </c:if>
                </td>
                <td>
                    <c:choose>
                        <c:when test="<%= propertyValue.getPropertyId()==CmsConstants.CATEGORY_ID%>">
                            <select name="<%=propertyValue.getName()%>" data-rule-required="${propertyValue.required}">                                
                                <option value="">---- Chọn ----</option>
                                <logic:iterate id="category" name="categories" type="CmsCategory">
                                    <c:if test="<%= item.getCategoryId()==category.getCategoryId()%>">
                                        <option value="<%= category.getCategoryId()%>" selected="selected"><%=category.getCategoryName()%></option>
                                    </c:if>
                                    <c:if test="<%= !(item.getCategoryId()==category.getCategoryId())%>">
                                        <option value="<%= category.getCategoryId()%>"><%=category.getCategoryName()%></option>
                                    </c:if>
                                </logic:iterate>
                            </select>
                        </c:when>
                        <c:when test="<%= propertyValue.getPropertyId()==CmsConstants.PERSPECTIVE_ID%>">
                            <input id="perspectiveIds" type="<%= propertyValue.getPropertyType()%>" value="<%= propertyValue.getStrValue()%>" name="<%=propertyValue.getName()%>"/>
                            <table width="100%" cellpadding="0" cellspacing="0">
                                <colgroup>
                                    <col width="45%"/>
                                    <col width="10%"/>
                                    <col width="45%"/>
                                </colgroup>
                                <tr>
                                    <td>
                                        <select id="perspectiveSource" name="perspectiveSource" multiple="multiple" size="10" onDblClick="javascript:copyToList(document.getElementById('perspectiveSource'),document.getElementById('itemPerspectives'),'right',document.getElementById('perspectiveIds'));">
                                            <logic:iterate id="perspective" name="perspectivesSource" type="CmsPerspective">
                                                <option value="<%= perspective.getPerspectiveId()%>"><bean:write name="perspective" property="perspectiveName"/></option>
                                            </logic:iterate>
                                        </select>
                                    </td>
                                    <td>
                                        <A HREF="javascript:copyToList(document.getElementById('perspectiveSource'),document.getElementById('itemPerspectives'),'right',document.getElementById('perspectiveIds'));">
<IMG SRC="<%=request.getContextPath()%>/images/csmv.gif" ALIGN="bottom" ALT="Move" border=0 height="13" width="13"></A><BR>
<IMG SRC="<%=request.getContextPath()%>/images/pobtrans.gif" height="9" width="60" ALT=""><BR><A HREF="javascript:copyAll(document.getElementById('perspectiveSource'),document.getElementById('itemPerspectives'),'right',document.getElementById('perspectiveIds')); "><IMG SRC="<%=request.getContextPath()%>/images/csmvall.gif" ALIGN="bottom" ALT="Move All" border=0 height="13" width="13"></A><BR>
<IMG SRC="<%=request.getContextPath()%>/images/pobtrans.gif" height="9" width="60" ALT=""><BR><A HREF="javascript:copyToList(document.getElementById('itemPerspectives'),document.getElementById('perspectiveSource'),'left',document.getElementById('perspectiveIds')); "><IMG SRC="<%=request.getContextPath()%>/images/csrmv.gif" ALIGN="bottom" ALT="Remove" border=0 height="13" width="13"></A><BR>
<IMG SRC="<%=request.getContextPath()%>/images/pobtrans.gif" height="9" width="60" ALT=""><BR><A HREF="javascript:copyAll(document.getElementById('itemPerspectives'),document.getElementById('perspectiveSource'),'left',document.getElementById('perspectiveIds')); "><IMG SRC="<%=request.getContextPath()%>/images/csrmvall.gif" ALIGN="bottom" ALT="Remove All" border=0 height="13" width="13"></A>
                                    </td>
                                    <td>
                                        <select ${propertyValue.required?"required minlength='1' title='Cần chọn ít nhất một ngữ cảnh'":""} id="itemPerspectives" multiple="multiple" size="10" onDblClick="javascript:copyToList(document.getElementById('itemPerspectives'),document.getElementById('perspectiveSource'),'left',document.getElementById('perspectiveIds'));">
                                            <logic:iterate id="perspective" name="itemPerspectives" type="CmsPerspective">
                                                <option value="<%= perspective.getPerspectiveId()%>" selected="selected"><bean:write name="perspective" property="perspectiveName"/></option>
                                            </logic:iterate>
                                        </select>
                                    </td>                                    
                                </tr>
                            </table>
                        </c:when>
                        <c:when test="<%= propertyValue.getPropertyType().equalsIgnoreCase(CmsConstants.TEXT_AREA)%>">
                            <textarea data-rule-required="${propertyValue.required}" cols="20" rows="5" class="cms-textarea" name="<%=propertyValue.getName()%>">
                                <bean:write name="propertyValue" property="strValue"/>
                            </textarea>
                        </c:when>
                        <c:when test="<%= propertyValue.getPropertyType().equalsIgnoreCase(CmsConstants.TEXT_EDITOR)%>">
                            <textarea data-rule-required="${propertyValue.required}" cols="400" rows="20" class="cms-editor" name="<%=propertyValue.getName()%>">
                                <bean:write name="propertyValue" property="strValue"/>
                            </textarea>                            
                        </c:when>
                        <c:when test="<%=propertyValue.getPropertyType().equalsIgnoreCase(CmsConstants.SIMPLE_DATE)%>">                            
                            <input id="<%=propertyValue.getName()%>_temp" disabled="disabled" type="text" class="cms-simpledate" value="<%= propertyValue.getStrValue()%>" onchange="updateDate('<%=propertyValue.getName()%>_temp','<%=propertyValue.getName()%>_value')"/> 
                            <input data-rule-required="${propertyValue.required}" type="hidden" name="<%=propertyValue.getName()%>" id="<%=propertyValue.getName()%>_value" value="<%=propertyValue.getStrValue()%>"/>
                        </c:when>
                        <c:when test="<%=propertyValue.getPropertyType().equalsIgnoreCase(CmsConstants.FULL_DATE)%>">                            
                            <input id="<%=propertyValue.getName()%>_temp" disabled="disabled" type="text" class="cms-fulldate" value="<%= propertyValue.getStrValue()%>" onchange="updateDate('<%=propertyValue.getName()%>_temp','<%=propertyValue.getName()%>_value')"/> 
                            <input data-rule-required="${propertyValue.required}" type="hidden" name="<%=propertyValue.getName()%>" id="<%=propertyValue.getName()%>_value" value="<%=propertyValue.getStrValue()%>"/>
                        </c:when>
                        <c:when test="<%=propertyValue.getPropertyType().equalsIgnoreCase(CmsConstants.CHECK_BOX)%>">
                            <input data-rule-required="${propertyValue.required}" type="<%= propertyValue.getPropertyType()%>" value="<%= propertyValue.getIntValue()%>" name="<%=propertyValue.getName()%>"/>
                        </c:when>
                        <c:when test="<%=propertyValue.getPropertyType().equalsIgnoreCase(CmsConstants.SELECT_BOX)%>">
                            <select data-rule-required="${propertyValue.required}" id="<%=propertyValue.getName()%>" name="<%=propertyValue.getName()%>">
                                <option value="">---- Chọn ----</option>                                
                                <logic:iterate id="category" name="propertyValue" type="CmsCategory" property="mutiValues">
                                    <c:choose>
                                        <c:when test="<%= category.getCategoryId()==propertyValue.getIntValue()%>">
                                            <option value="<%= category.getCategoryId()%>" selected="selected"><bean:write name="category" property="categoryName"/></option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="<%= category.getCategoryId()%>"><bean:write name="category" property="categoryName"/></option>
                                        </c:otherwise>
                                    </c:choose>
                                </logic:iterate>
                            </select>
                        </c:when>
                        <c:when test="<%=propertyValue.getPropertyType().equalsIgnoreCase(CmsConstants.IMAGE)%>">                            
                            <c:if test="<%=propertyValue.getIntValue()>0%>">
                                <img id="<%= propertyValue.getName()%>_source" src="<%=CmsConstants.IMG_SOURCE_LINK+propertyValue.getIntValue()%>" class="cms-image"/>
                            </c:if>
                            <c:if test="<%=!(propertyValue.getIntValue()>0)%>">
                                <img id="<%= propertyValue.getName()%>_source" src="<%=request.getContextPath()%>/images/No_Image.jpg" class="cms-image"/>
                            </c:if>
                            &nbsp;<img title="Xóa" src="<%=request.getContextPath()%>/images/del.gif" align="middle" class="cms-util-icon" onclick="removeImage('<%= propertyValue.getName()%>_id','<%= propertyValue.getName()%>_source')"/>
                            &nbsp;<img title="Thêm" src="<%=request.getContextPath()%>/images/plus.gif" align="middle" class="cms-util-icon" onclick="openImageBrowser('<%= propertyValue.getName()%>_id','<%= propertyValue.getName()%>_source')"/>
                            <input data-rule-required="${propertyValue.required}" type="hidden" id="<%= propertyValue.getName()%>_id" value="<%= propertyValue.getIntValue()%>" name="<%=propertyValue.getName()%>"/>
                        </c:when>
                        <c:when test="<%=propertyValue.getPropertyType().equalsIgnoreCase(CmsConstants.FILE)%>">                            
                            <%
                                int size=0;
                            %>
                            <ul id="<%= propertyValue.getName()%>_source" class="file-attach-list">
                                <logic:iterate id="file" name="propertyValue" property="mutiValues" type="CmsFile">
                                    <li id="<%=propertyValue.getName()+size%>" class="attach_file">
                                        <img title="Xóa" onclick="removeAttachFile('<%= propertyValue.getName()%>_id','<%=propertyValue.getName()+size%>',<%=file.getFileId()%>)" src="<%=request.getContextPath()%>/images/del.gif" align="middle" class="cms-util-icon"/>
                                        <a href="<%=CmsConstants.FILE_DOWNLOAD_LINK+file.getFileId()%>">
                                            <bean:write name="file" property="fileName"/>
                                        </a>      
                                    </li>
                                </logic:iterate>                                                                
                            </ul>
                            <input type="hidden" id="<%= propertyValue.getName()%>_size" value="<%=size%>"/>
                            &nbsp;<img id="<%= propertyValue.getName()%>_source" src="<%=request.getContextPath()%>/images/plus.gif" align="middle" class="cms-util-icon" onclick="openFileBrowser('<%= propertyValue.getName()%>_id','<%= propertyValue.getName()%>_source')" title="Thêm"/>
                            <input data-rule-required="${propertyValue.required}" type="hidden" id="<%= propertyValue.getName()%>_id" value="<%= propertyValue.getStrValue()%>" name="<%=propertyValue.getName()%>"/>                            
                        </c:when>
                        <c:otherwise>
                            <input data-rule-required="${propertyValue.required}" type="<%= propertyValue.getPropertyType()%>" value="<%= propertyValue.getStrValue()%>" name="<%=propertyValue.getName()%>"/>
                        </c:otherwise>
                    </c:choose>
                </td>                
            </tr>
        </logic:iterate>
    </table>
    <div class="aciont-button">
        <input type="hidden" value="<%= request.getContextPath()%>" id="contextPath"/>
        <input type="hidden" value="${requestScope.item.status}" name="status"/>
        <input type="hidden" value="${requestScope.form.typeId}" name="typeId"/>
        <input type="hidden" value="<%= l_user%>" name="owner"/>
        <input type="hidden" value="<%= l_user%>" name="updater"/>
        <input type="hidden" name="language" value="${requestScope.form.language}"/>
        <input type="hidden" name="type" value="${requestScope.form.type}"/>
        <input type="hidden" name="perspective" value="${requestScope.form.perspective}"/>
        <input type="hidden" name="category" value="${requestScope.form.category}"/>
        <input type="hidden" name="searchText" value="${requestScope.form.searchText}"/>
        <input type="hidden" name="fromDate" value="${requestScope.form.fromDate}"/>
        <input type="hidden" name="toDate" value="${requestScope.form.toDate}"/>
        <input type="hidden" name="backUrl" value="<%=PortletUtil.getPageURL(request)%>"/>
        <input type="hidden" name="<%=PortletUtil.portletParameter(request,CmsConstants.TOKEN_ID)%>" id="tokenId" value="${requestScope.token.id}"/>
        <input type="hidden" name="<%=PortletUtil.portletParameter(request,CmsConstants.TOKEN_VALUE)%>" id="tokenId" value="${requestScope.token.token}"/>
        <input type="submit" value="Cập nhật"/>
    </div>
  </form>
  </body>
</html>