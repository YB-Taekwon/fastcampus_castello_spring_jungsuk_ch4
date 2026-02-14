<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page session="false" %>
<c:set var="loginId"
       value="${pageContext.request.getSession(false)==null ? '' : pageContext.request.session.getAttribute('username')}"/>
<c:set var="loginOutLink" value="${loginId=='' ? '/auth/login' : '/auth/logout'}"/>
<c:set var="loginOut" value="${loginId=='' ? 'LogIn' : 'ID='+=loginId}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판 만들기</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/menu.css'/>">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@7.2.0/css/all.min.css">
</head>
<body>
<div id="menu">
    <ul>
        <li id="logo">게시판 만들기</li>
        <li><a href="<c:url value='/'/>">Home</a></li>
        <li><a href="<c:url value='/boards'/>">Board</a></li>
        <li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
        <li><a href="<c:url value='/auth/register'/>">JoinUs</a></li>
        <li><a href=""><i class="fa fa-search"></i></a></li>
    </ul>
</div>
<div style="text-align:center">
    <h1>This is HOME</h1>
    <h1>This is HOME</h1>
    <h1>This is HOME</h1>
</div>
</body>
</html>