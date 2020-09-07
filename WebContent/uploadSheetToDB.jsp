<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Upload to Database</title>
</head>
<body>
   
        <h1>File Upload to Database Demo</h1>
        <form method="post" action="uploadSheet" enctype="multipart/form-data">
            <table>
            	<tr>
                    <td>Sheet Name: </td>
                    <td><input type="text" name="sheetName" size="50"/></td>
                </tr>
                <tr>
                    <td>Instrument Type: </td>
                    <td><input type="text" name="instrumentType" size="50"/></td>
                </tr>
                
                <tr>
                    <td>Original Artist Name : </td>
                    <td><input type="text" name="originalArtistName" size="50"/></td>
                </tr>
                <tr>
                    <td>Sheet pic : </td>
                    <td><input type="file" name="photo" size="50"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Save">
                    </td>
                </tr>
            </table>
        </form>
    
</body>
</html>