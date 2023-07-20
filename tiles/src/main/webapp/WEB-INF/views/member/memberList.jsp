<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="bitedu.bipa.tiles.vo.User"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book List</title>
<link href="../resources/css/basic_style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>

<body>
${param.flag=='true'?"<script>alert('탈퇴성공');</script>":""}
<table>
	<tr><th colspan="5" id="title">회원리스트</th></tr>
	<tr><td id='seq'>순번</td><td>회원번호</td><td>회원이름</td><td>빌린책</td><td></td></tr>
<c:forEach var="copy" items="${pd.getList()}">
	<tr>
		<td>${copy.getBookSeq()}</td>
 		<td><a href='view_detail.do?bookSeq=${copy.bookSeq}'>${copy.title}</a></td>
		<td>${copy.author}</td>
		<td><fmt:formatDate value="${copy.publishDate}" pattern="yyyy-MM-dd"/> </td>
		<td><a href="remove.do?bookSeq=${copy.bookSeq}">삭제</a></td>
	</tr>
</c:forEach>
	<tr>
		<td colspan="5">${pd.getNavBar()}</td>
	</tr>

	<tr><td colspan="5"><a href="view_regist.do"><button>도서등록</button></a></td></tr>
</table>
<script type="text/javascript" src="../resources/js/book.js"></script> 
</body>
</html>