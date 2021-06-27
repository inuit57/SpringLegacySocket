<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<form action="#">
	유저 등록 : <input type="text" name="userid"><br>
</form>


<!--  테스트로 해당 채팅방으로 보내주는 부분 -->
<a href="./chatting/1">1번 채팅방</a><br>
<a href="./chatting/2">1번 채팅방</a> <br>

<!-- 
	DB에서 현재 유저가 속한 채팅방 목록을 읽어서 
	해당 채팅방으로 갈 수 있는 링크들을 생성해줘야 한다. 
	
	덧붙여서 새로운 채팅이 들어왔을 경우 알람도 띄워주는 것을 생각해보고.
 -->
</body>
</html>
