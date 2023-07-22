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
<title>Member Detail</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>
<link href="../resources/css/basic_style.css" rel="stylesheet" type="text/css" />
</head>
<body>
${param.flag=='true'?"<script>alert('탈퇴성공');</script>":""}
<table>
	<c:set var="copy" value="${data}"/>
	<tr><th colspan="5" id="title">회원 상세 정보</th></tr>

	<tr>
		<td>순번</td>
 		<td>${copy.memberSeq}</td>

	</tr>
	<tr>
		<td>회원번호</td>
 		<td>${copy.memberNum}</td>

	</tr>
	<tr>
		<td>회원이름</td>
 		<td>${copy.memberName}</td>

	</tr>
	<tr>
		<td>회원성별</td>
 		<td>${copy.memberGender}</td>

	</tr>
	<tr>
		<td>회원가입일</td>
 		<td>${copy.memberRegistDate}</td>

	</tr>
	<tr>
		<td>회원 등급</td>
 		<td>${copy.memberGrade}</td>

	</tr>

	<tr><td colspan="5"><a href="view_regist.do"><button>회원 탈퇴</button></a></td></tr>
</table>
<script type="text/javascript" src="../resources/js/book.js"></script> 
</body>
</html>