<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 작성</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link href="../resources/css/home.css" rel="stylesheet" type="text/css">

</head>
<body>

<table>
	<thead>
		<tr>
			<th colspan ="4"><h1>방명록 조회</h1></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>글번호 : ${list.textNum }</td>
			<td>작성자 : ${list.writer }</td>
			<td>글제목 : ${list.title }</td>
			<td>작성일 : ${list.createdAt}</td>
		</tr>
		<tr>
			<td style="width:80%" colspan ="4"><c:out value="${list.content}" /></td>
		</tr>	
		<tr>
			<td colspan ="4"><button type="button" id="returnList">방명록 돌아가기</button><button>댓글달기</button></td>
		</tr>
	</tbody>
	
</table>
<script type="text/javascript">
$(document).ready(function(){
	$("#returnList").click(function() {
		window.location.href = "./list.do";		
	});
	
});
</script>

</body>
</html>