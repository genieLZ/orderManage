<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--<jsp:forward page="/cmdts"></jsp:forward>--%>
<html>
<head>
    <title>商品列表</title>
    <%
        pageContext.setAttribute("APP_PATH",request.getContextPath());
    %>
    <%=request.getContextPath()%>
    <%--引入jquery--%>
    <script type="text/javascript" src="${APP_PATH}/js/jquery-3.2.1.min.js"></script>
    <%--引入样式--%>
    <link href="${APP_PATH}/static.bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="${APP_PATH}/static.bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
<h1>h1. Bootstrap heading</h1>
<h2>h2. Bootstrap heading</h2>
<h3>h3. Bootstrap heading</h3>
<h4>h4. Bootstrap heading</h4>
<h5>h5. Bootstrap heading</h5>
<h6>h6. Bootstrap heading</h6>
</body>
</html>
