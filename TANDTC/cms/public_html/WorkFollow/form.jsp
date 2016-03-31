<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.cms.utils.PortletUtil"%>
<%@ page import="com.cms.utils.CmsConstants"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>form</title>
  </head>
  <body>
  <script src="<%= request.getContextPath()%>/js/jquery.js"></script>
  <script src="<%= request.getContextPath()%>/WorkFollow/js/WorkFollow.js"></script>
  <link rel="stylesheet" href="<%= request.getContextPath()%>/css/cms.css"/>
    <form name="workfollow" id="workfollow" action="workfollow.do" method="POST">
        <input type="hidden" id="method" name="<%=PortletUtil.portletParameter(request,"method")%>" value="update"/>
        <input type="hidden" id="id" name="<%=PortletUtil.portletParameter(request,"id")%>" value="${requestScope.workfollow.id}"/>
        <input type="hidden" id="txtName" name="<%=PortletUtil.portletParameter(request,"txtName")%>" value="${requestScope.form.txtName}"/>
        <input type="hidden" name="<%=PortletUtil.portletParameter(request,CmsConstants.TOKEN_ID)%>" id="tokenId" value="${requestScope.token.id}"/>
        <input type="hidden" name="<%=PortletUtil.portletParameter(request,CmsConstants.TOKEN_VALUE)%>" id="tokenId" value="${requestScope.token.token}"/>
        <table width="100%" cellpadding="0" cellspacing="0">
            <tr>
                <td>Quy trình</td>
                <td><input type="text" name="<%=PortletUtil.portletParameter(request,"name")%>" value="${requestScope.workfollow.name}"/></td>
            </tr>
            <tr>
                <td>Mô tả</td>
                <td><textarea cols="10" rows="10" name="<%=PortletUtil.portletParameter(request,"description")%>">${requestScope.workfollow.description}</textarea></td>
            </tr>
        </table>
        <input type="submit" value="Cập nhật"/>
        <input type="button" value="Bỏ qua" onclick="cancel()"/>
    </form>
  </body>
</html>