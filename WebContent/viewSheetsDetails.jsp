<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	   
        Identifier: ${sheetBrowser.currentSheet.idSheet} <br/>
        SheetName: ${sheetBrowser.currentSheet.sheetName} <br/>	
        InstrumentType: ${sheetBrowser.currentSheet.instrumentType} <br/>
        OriginalArtistName: ${sheetBrowser.currentSheet.originalArtistName} <br/>
        DataSheet: ${sheetBrowser.currentSheet.dataSheet} <br/>
        <br/>
        
        <form action="viewSheetsDetails" method="post">
            <input name="btnPrevious" type="submit" value="Previous" />
            &nbsp; &nbsp;
            <input name="btnAdd" type="submit" value="Add to shopping cart" />
            &nbsp; &nbsp;
            <input name="btnNext" type="submit" value="Next" />
        </form>  <br/>
        
         ${sheetBrowser.shoppingCartSize} sheet<c:if test="${sheetBrowser.shoppingCartSize gt 1}">s</c:if> in the shopping cart.<br/>
        <a href="summary">View the shopping cart</a>
	</body>
</html>