<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="loginId"
       value="${pageContext.request.getSession(false)==null ? '' : pageContext.request.session.getAttribute('username')}"/>
<c:set var="loginOutLink" value="${loginId=='' ? '/auth/login' : '/auth/logout'}"/>
<c:set var="loginOut" value="${loginId=='' ? 'LogIn' : 'ID='+=loginId}"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판 만들기</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/menu.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
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
    <table>
        <tr>
            <th class="boardId">번호</th>
            <th class="title">제목</th>
            <th class="writer">작성자</th>
            <th class="createdAt">등록일</th>
            <th class="viewCount">조회수</th>
        </tr>
        <c:forEach var="board" items="${boardList}">
            <tr>
                <td class="boardId">${board.boardId}</td>
                <td class="title">${board.title}</td>
                <td class="writer">${board.writer}</td>
                <td class="createdAt">${board.createdAt}</td>
                <td class="viewCount">${board.viewCount}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<br>
<div>
    <c:if test="${pageHandler.showPrev}">
        <a href="<c:url value="/boards?page=${i - 1}&pageSize=${pageHandler.pageSize}"/>">&lt;</a>
    </c:if>
    <c:forEach var="i" begin="${pageHandler.startPage}" end="${pageHandler.endPage}">
        <a href="<c:url value="/boards?page=${i}&pageSize=${pageHandler.pageSize}"/>">${i}</a>
    </c:forEach>
    <c:if test="${pageHandler.showNext}">
        <a href="<c:url value="/boards?page=${pageHandler.endPage + 1}&pageSize=${pageHandler.pageSize}"/>">&gt;</a>
    </c:if>
</div>
</body>
</html>