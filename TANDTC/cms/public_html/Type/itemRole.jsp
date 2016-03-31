<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.cms.utils.PortletUtil,com.cms.utils.CmsConstants"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>itemRole</title>
  </head>
  <body>  
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.custom.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/Type/js/itemRole.js"></script>
  <form action="type.do" method="POST" id="itemRole" name="itemRole">
    <label>User/Group</label>
    <input type="text" name="<%=PortletUtil.portletParameter(request,"userGroupName")%>" id="userGroupName"/>
    <input type="hidden" name="<%=PortletUtil.portletParameter(request,"method")%>" id="method" value="addItemRole"/>
    <input type="hidden" name="<%=PortletUtil.portletParameter(request,CmsConstants.TOKEN_ID)%>" id="tokenId" value="${requestScope.token.id}"/>
    <input type="hidden" name="<%=PortletUtil.portletParameter(request,CmsConstants.TOKEN_VALUE)%>" id="tokenId" value="${requestScope.token.token}"/>
    <input type="hidden" name="<%=PortletUtil.portletParameter(request,"userGroup")%>" id="userGroup"/>
    <input type="hidden" name="<%=PortletUtil.portletParameter(request,"isUser")%>" value="0" id="isUser"/>
    <input type="hidden" name="<%=PortletUtil.portletParameter(request,"typeId")%>" id="typeId" value="${requestScope.form.typeId}"/>
    <input type="hidden" name="<%=PortletUtil.portletParameter(request,"roleId")%>" id="roleId" value="${requestScope.form.roleId}"/>
    <a href="javascript:selectUserGrop('user');"><img src="<%=request.getContextPath()%>/images/tabuser.gif" title="Chọn người dùng"/></a>
    &nbsp;<a href="javascript:selectUserGrop('group');"><img src="<%=request.getContextPath()%>/images/tabgroups.gif" title="Chọn nhóm người dùng"/></a>
    &nbsp;Quyền
    <select name="<%=PortletUtil.portletParameter(request,"userRole")%>">
        <option value="editor">Biên tập viên</option>
        <option value="manager">Quản lý</option>
    </select>
    <input type="submit" value="Thêm"/>
    <input type="button" value="Quay lại" onclick="back();"/>
  </form>
  <table width="100%" cellpadding="0" cellspacing="0" border="1">
    <caption>Danh sách phân quyền</caption>
    <tr>
        <td>User/Group</td>
        <td>Quyền</td>
        <td>Thao tác</td>
    </tr>
    <logic:notEmpty name="users">
       <tr>
        <td colspan="3">
            Người dùng
        </td>
    </tr>
    <logic:iterate id="user" name="users">
        <tr>
            <td>
              <bean:write name="user" property="name"/>
            </td>
            <td>
                <bean:message key="${user.userRole}"/> 
            </td>
            <td>
                <a href="JavaScript:remove(${user.id})">
                    <img src="<%=request.getContextPath()%>/images/del.gif"/>
                </a>
            </td>
        </tr>
    </logic:iterate>
    </logic:notEmpty>
    <logic:notEmpty name="groups">
       <tr>
        <td colspan="3">
            Nhóm người dùng
        </td>
    </tr>
    <logic:iterate id="group" name="groups">
        <tr>
            <td>
              <bean:write name="group" property="name"/>
            </td>
            <td>
                <bean:message key="${group.userRole}"/>                
            </td>
            <td>
                <a href="JavaScript:remove(${group.id})">
                    <img src="<%=request.getContextPath()%>/images/del.gif"/>
                </a>
            </td>
        </tr>
    </logic:iterate>
    </logic:notEmpty>
  </table>  
  </body>
</html>