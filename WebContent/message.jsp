<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Message</title>
</head>
<body>
        <h3><%=request.getAttribute("Message")%></h3>
        <input type="button" onclick=window.location.href='http://localhost:8080/CPS-Music/viewSheetsDetails' value=Retour />
</body>
</html>