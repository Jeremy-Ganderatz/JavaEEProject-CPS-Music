<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>View sheet</title>
		<link rel="stylesheet" type="text/css" href="styles.css" />
	</head>
	<body>
        <h1>View sheet - ${connectedUser.login}</h1>
	    <br/>
	   		
        SheetName: ${sheetBrowser.currentSheet.sheetName} <br/>	
        InstrumentType: ${sheetBrowser.currentSheet.instrumentType} <br/>
        OriginalArtistName: ${sheetBrowser.currentSheet.originalArtistName} <br/>
        <img src="data:image/jpg;base64,${sheetBrowser.currentSheet.base64Image}" width="240" height="300">
        
        <br/>
        
        <form action="viewSheetsDetails" method="post">
            <input name="btnPrevious" type="submit" value="Previous" />
            &nbsp; &nbsp;
            <input name="btnAdd" type="submit" value="Add to shopping basket" />
            &nbsp; &nbsp;
            <input name="btnNext" type="submit" value="Next" />
            
            <input type="button" onclick=window.location.href='http://localhost:8080/CPS-Music/uploadSheet' value=UploadSheet />
        </form>  
        
        <br/>
        
         ${sheetBrowser.shoppingCartSize} sheet<c:if test="${sheetBrowser.shoppingCartSize gt 1}">s</c:if> in the shopping cart.<br/>
        <a href="summary">View the shopping basket</a>
	</body>
</html>