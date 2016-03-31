<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.cms.utils.PortletUtil"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>main</title>
  </head>
  <body>
  <script src="<%= request.getContextPath()%>/js/jquery.js"></script>
  <script src="<%= request.getContextPath()%>/WorkFollow/js/WorkFollow.js"></script>
  <link rel="stylesheet" href="<%= request.getContextPath()%>/css/cms.css"/>
  <form action="workfollow.do" name="workfollow" id="workfollow" method="POST">
    <label class="cms-label">Quy trình</label>
    <input type="text" name="<%=PortletUtil.portletParameter(request,"txtName")%>" value="${requestScope.form.txtName}"/>
    <input type="hidden" name="<%=PortletUtil.portletParameter(request,"method")%>" id="method" value="Search"/>
    <input type="hidden" name="<%=PortletUtil.portletParameter(request,"id")%>" id="id" value="0"/>
    <input type="submit" value="Tìm kiếm"/>
  </form>
  <div class="add-panel"><a href="javascript:addWorkFollow()">Thêm mới</a></div>
  <table id="tblWorkFollow" class="cms-table" cellpadding="0" cellspacing="0" width="100%" border="1">
    <tr>
        <th>STT</th>  
        <th>Quy trình</th>
        <th>Thao tác</th>
    </tr>
    <logic:notEmpty name="list">
        <logic:iterate id="workFollow" name="list" indexId="stt">
            <tr>
                <td>${stt+1}</td>
                <td><bean:write name="workFollow" property="name"/></td>
                <td>
                    <a href="javascript:updateWorkFollow(${workFollow.id});">
                            <img src="<%=request.getContextPath()%>/images/edit.gif"/>
                    </a>
                    <a href="javascript:deleteWorkFollow(${workFollow.id});">
                        <img src="<%=request.getContextPath()%>/images/del.gif"/>
                    </a>
                </td>
          </logic:iterate>
      </logic:notEmpty>
    </table>
  </body>
</html>