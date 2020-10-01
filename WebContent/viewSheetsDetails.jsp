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
		<header>
			<h1>La double croche</h1>
			<h2>-- Connecté en tant que : ${connectedUser.login} --</h2>
		</header>
		
		<form action="viewSheetsDetails" method="post">	
			<nav>
				<ul>
				<!-- Début du menu déroulant -->
				<li><a href="#">Trier par Instrument</a>
				<ul>
				<li><input name="PIANO" type="submit" value="Piano" /></li>
				<li><input name="VIOLON" type="submit" value="Violon" /></li>
				<li><input name="CLARINETTE" type="submit" value="Clarinette" /></li>
				<li><input name="CHANT" type="submit" value="Chant" /></li>
				<li><input name="GUITARE" type="submit" value="Guitare" /></li>
				<li><input name="TOUS" type="submit" value="Tous" /></li>
				</ul>
				</li>
				
				<li><a href="#">Trier par Prix</a>
				<ul>
				<li><input name="ZEROACINQ" type="submit" value="0 à 5euros" /></li>
				<li><input name="CINQADIX" type="submit" value="5 à 10euros" /></li>
				<li><input name="DIXAVINGT" type="submit" value="10 à 20euros" /></li>
				<li><input name="VINGTETPLUS" type="submit" value="20euros et plus" /></li>
				<li><input name="TOUS" type="submit" value="Tous les prix" /></li>
				</ul>
				</li>
				
				<li><a href='http://localhost:8080/CPS-Music/uploadSheet'> Vendre une partition </a>
				
				<li><a href="#">Comment fonctionne le site</a>
				<li><a href="#">Nous contacter</a>
				</ul>
			</nav>
		</form>
		
		<div id="centerBlock">
		
        Nom: ${sheetBrowser.currentSheet.sheetName} <br/>	
        Type d'instrument: ${sheetBrowser.currentSheet.instrumentType} <br/>
        Artiste: ${sheetBrowser.currentSheet.originalArtistName} <br/>
        Prix: ${sheetBrowser.currentSheet.price} euros<br/>
        <img src="data:image/jpg;base64,${sheetBrowser.currentSheet.base64Image}" width="240" height="300"><br/>
        
        <form action="viewSheetsDetails" method="post">
            <input name="btnPrevious" type="submit" value=" <-" />
            &nbsp; &nbsp;
            <input name="btnAdd" type="submit" value="Ajouter au panier" />
            &nbsp; &nbsp;
            <input name="btnNext" type="submit" value=" ->" />
        </form>  
        
        <br/>
        
         ${sheetBrowser.shoppingCartSize} partition<c:if test="${sheetBrowser.shoppingCartSize gt 1}">s</c:if> dans le panier.<br/>
        <a href="summary">Voir le panier</a>
        </div>
        
        
        
	</body>
</html>