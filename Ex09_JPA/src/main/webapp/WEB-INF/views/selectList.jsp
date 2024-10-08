<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member List</title>
</head>
<body>
	총 멤버 수: ${totalElements}<br>
	총 페이지 수: ${totalPages}<br>
	사이즈: ${size}<br>
	페이지 번호: ${pageNumber}<br>
	보여지는 content 수: ${numberOfElements}<br>
	<hr>
	<c:forEach var="member" items="${members}">
		${member.id} | ${member.name} | ${member.email}
		<hr>
	</c:forEach>
</body>
</html>