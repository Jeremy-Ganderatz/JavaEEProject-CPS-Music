<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload to Database</title>
</head>
<body>
   
        <h1>Renseigner votre partition</h1>
        <form method="post" action="uploadSheet" enctype="multipart/form-data">
            <table>
            	<tr>
                    <td>Nom : </td>
                    <td><input type="text" name="sheetName" size="50"/></td>
                </tr>
                <tr>
                    <td>Type d'instrument : </td>
                    <td><input type="text" name="instrumentType" size="50"/></td>
                </tr>
                
                <tr>
                    <td>Artiste : </td>
                    <td><input type="text" name="originalArtistName" size="50"/></td>
                </tr>
                <tr>
                    <td>Prix : </td>
                    <td><input type="number" name="price" size="4"/></td>
                </tr>
                <tr>
                    <td>Photo : </td>
                    <td><input type="file" name="photo" size="50"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Enregistrer">
                    </td>
                </tr>
            </table>
        </form>
    
</body>
</html>