<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Shopping cart summary</title>
		<link rel="stylesheet" type="text/css" href="styles.css" />
	</head>
	<body>
        <h1>Shopping cart summary - ${connectedUser.login}</h1>
	    <br/>
	   
		<table style="width: 60%; border: 1px solid black; margin: auto;"> 
			<thead>
				<tr>
					<th>Identifier</th>
					<th>Name</th>
					<th>Instrument</th>
					<th>OriginalArtist</th>
					<th>Quantity</th>
				</tr>
			</thead>
			
			<tbody>
			
				<c:forEach items="#{sheetBrowser.shoppingCart}" var"line">
					<tr>
						<td>${line.sheet.idSheet}</td>
						<td>${line.sheet.sheetName}</td>
						<td>${line.sheet.instrumentType}</td>
						<td>${line.sheet.originalArtistName}</td>
						<td>${line.quantity}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>        

		<br/><br/>
		
		<a href="viewSheet">Return to catalog</a>
		
	</body>
</html>