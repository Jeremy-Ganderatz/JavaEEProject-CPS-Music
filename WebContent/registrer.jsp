<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Date" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Registrer screen</title>
		<link rel="stylesheet" type="text/css" href="styles.css" />
	</head>
	<body>
        <h1>Create your account</h1>
	
	    <form method="post" action="registrer">

	        Login: 
	        <input name="txtLogin" value="${login}" autofocus />
	        <br/>

            Password: 
            <input name="txtPassword" type="password" value="${password}" />
            <br/> <br/>
	        
	        <input type="submit" name="submit" value="Registrer" />
	        <br/><br/>
	        
            <div class="errorMessage">${errorMessage}</div>
            
	    </form>
	    	
	</body>
</html>