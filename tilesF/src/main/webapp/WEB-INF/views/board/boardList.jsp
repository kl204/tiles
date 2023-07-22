<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
	<title>방명록</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link href="../resources/css/home.css" rel="stylesheet" type="text/css">
</head>
<body>
${param.flag=='true'?"<script>alert('삭제성공');</script>":""}
<table>
	<thead>
		<tr><td colspan="6"><h1>방명록</h1></td></tr>
		<tr>
			<td colspan="6">
				<select id="searchType">
					<option name="searchOption" value="selectAll">전체</option>
					<option name="searchOption" value="selectTitle">제목명</option>
				</select>
				<input type="text" id="searchText"/>
				<button type="button" id="search">조회</button>
				</td>
		</tr>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회횟수</th>
			<th>작성시간</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
<c:forEach var="text" items="${data}">
		<tr>
			<td>${text.textNum}</td>
			<td><a href="./content?textNum=${text.textNum}">${text.title}</a></td>
			<td>${text.writer}</td>
			<td>${text.viewNum}</td>
			<td>${text.createdAt}</td>
			<td><a href="delete?textNum=${text.textNum}">삭제</a></td>
		</tr>
</c:forEach>
		<tr>
			<td colspan="6">
			<button type="button" id="regist-button">작성</button>

			</td>
		</tr>
	</tbody>
</table>
<script type="text/javascript">
$("#regist-button").click(function() {
	window.location.href = "regist_view";
});

$("#logout").click(function() {
	let logout = true;
	window.location.href = "logout?flag=true";
});

$("#search").click(function() {
	
	let text = document.getElementById("searchText").value;
	let type = document.getElementById("searchType");
	
	let searchType = type.options[type.selectedIndex].value;
	
 	window.location.href = "search?text=" + text +"&type=" + searchType; 
});

	
</script>

</body>
</html>
