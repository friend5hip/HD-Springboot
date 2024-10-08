<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DELETE</title>
</head>
<body>
	멤버 삭제 완료<br>
	${member.id}<br>
	${member.name}<br>
	${member.email}<br>
	${member.createDate}
</body>
</html>