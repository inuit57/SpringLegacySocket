<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/room/list.jsp</h1>
	

    <table border="1">
      <tr>
      	<td>방번호</td>
        <td>방이름</td>
        <td>현재인원</td>
        <td>최대인원</td>
        <td>방장명</td>
      </tr>
      <c:forEach var="room" items="${roomList }">
      <tr> 
      	<td>${room.roomId }</td>
      	<td>${room.roomName }</td>
      	<td>${room.currUserCnt }</td>
      	<td>${room.maxUserCnt }</td>
      	<td>${room.roomManager }</td>
      </tr>
      </c:forEach>
    </table>


</body>
</html>