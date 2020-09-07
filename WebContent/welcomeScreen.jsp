<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Date" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Welcome Screen</title>
		<link rel="stylesheet" type="text/css" href="styles.css" />
	</head>
	<body>
        <h1>Welcome</h1>
	
	    <form method="post" action="login">
	        
	        <input type=button onclick=window.location.href='http://localhost:8080/CPS-Music/login' value=Connection />
	        
	        <input type=button onclick=window.location.href='http://localhost:8080/CPS-Music/registrer' value=Registrer />
	        
            <div class="errorMessage">${errorMessage}</div>
            
	    </form>
	    	
	</body>
</html>