<%@ page import="com.cms.utils.PortletUtil"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<form action="${sessionScope.form.backUrl}" method="POST" id="item-form">
    <input type="hidden" value="<%= request.getContextPath()%>" id="contextPath"/>
    <input type="hidden" value="${requestScope.item.status}" name="status"/>
    <input type="hidden" value="${requestScope.form.typeId}" name="typeId"/>    
    <input type="hidden" name="language" value="${requestScope.form.language}"/>
    <input type="hidden" name="type" value="${requestScope.form.type}"/>
    <input type="hidden" name="perspective" value="${requestScope.form.perspective}"/>
    <input type="hidden" name="category" value="${requestScope.form.category}"/>
    <input type="hidden" name="searchText" value="${requestScope.form.searchText}"/>
    <input type="hidden" name="fromDate" value="${requestScope.form.fromDate}"/>
    <input type="hidden" name="toDate" value="${requestScope.form.toDate}"/>
</form>
  
