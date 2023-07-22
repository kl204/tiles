<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="bitedu.bipa.tiles.vo.BookCopy"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    

<c:set var = "contextpath" value = "<%=request.getContextPath()%>"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book List</title>
<style type="text/css">
		table, td, th {
            border : 1px solid black;
            border-collapse: collapse;
            margin: 20px auto;
        }
        td {
            width: 150px;
            height: 50px;
            padding: 5px;
            font-size: 20px;
            /* text-align: center; */
        }

        input , select {
            font-size: 20px;
        }
        .data_ui {
            /* width: 250px; */
            height: 50px;
        }

        button {
            font-size: 15px;
            margin: 5px;
        }
        
        
        #sending {
        	text-align: center;
        }
        
        input.poster :disabled {
        	background: gray;
        }
        
        #form {
        	font-size: 30px;
        }
        
        #title {
        	height: 80px;
        	font-size: 50px;
        }
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>

<body>
${param.flag=='true'?"<script>alert('삭제성공');</script>":""}
<table>
	<tr><th colspan="5" id="title">도서관리</th></tr>
	<tr><td id='seq'>순번</td><td>타이틀</td><td>저자</td><td>출판일</td><td></td></tr>
<c:forEach var="copy" items="${data}">
	<tr>
		<td>${copy.getBookSeq()}</td>
 		<td><a href='view_detail.do?bookSeq=${copy.bookSeq}'>${copy.title}</a></td>
		<td>${copy.author}</td>
		<td><fmt:formatDate value="${copy.publishDate}" pattern="yyyy-MM-dd"/> </td>
		<td><a href="remove.do?bookSeq=${copy.bookSeq}">삭제</a></td>
	</tr>
</c:forEach>

	<tr><td colspan="5"><a href="view_regist.do"><button>도서등록</button></a></td></tr>
</table>
<script type="text/javascript" src="../resources/js/book.js"></script> 
</body>
</html>