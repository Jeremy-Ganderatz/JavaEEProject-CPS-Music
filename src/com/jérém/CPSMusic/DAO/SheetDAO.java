package com.jérém.CPSMusic.DAO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;

import com.jérém.CPSMusic.objects.Sheet;

public class SheetDAO extends DAOContext{
	
	public static int getSheetCount() {
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ){

			String strSql = "SELECT count(idSheet) FROM T_Sheet";
			try ( Statement statement  = connection.createStatement() ) {
				try ( ResultSet resultSet = statement.executeQuery( strSql ) ) {
					resultSet.next();
					return resultSet.getInt( 1 );
				}
			}
			
		} catch ( Exception exception ) {
			
			throw new RuntimeException( exception );
			
		}
	}
	
	public static Sheet getSheetById( int idSheet ) throws SQLException, IOException{
		Sheet sheet = null;
        
        String sql = "SELECT * FROM T_Sheet WHERE idSheet = ?";
         
        try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idSheet);
            ResultSet result = statement.executeQuery();
             
            if (result.next()) {
            	sheet = new Sheet();
            	String sheetName = result.getString("sheetName");
                String instrumentType = result.getString("instrumentType");
                String originalArtistName = result.getString("originalArtistName");
                Blob blob = result.getBlob("dataSheet");
                //TODO gerer le cas ou il n'y a pas de photo ou pas a voir
                 
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                 
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);                  
                }
                 
                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                 
                 
                inputStream.close();
                outputStream.close();
                 
                sheet.setSheetName(sheetName);
                sheet.setInstrumentType(instrumentType);
                sheet.setOriginalArtistName(originalArtistName);
                sheet.setBase64Image(base64Image);
            }          
             
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
            throw ex;
        }      
         
        return sheet;
    }
	
	
	public static void updateSheet( Sheet sheet ) {
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ){

			String strSql = "UPDATE Sheet SET sheetName=?, instrumentType=?, originalArtistName=? WHERE idSheet=?";
			try ( PreparedStatement statement  = connection.prepareStatement( strSql ) ) {
				statement.setString( 1, sheet.getSheetName() );
				statement.setString( 2, sheet.getInstrumentType() );
				statement.setString( 3, sheet.getOriginalArtistName() );
				statement.setInt( 4, sheet.getIdSheet() );
				statement.executeUpdate();
			}
			
		} catch ( Exception exception ) {
			
			throw new RuntimeException( exception );
			
		}
	}
	
	
	public static void addSheet( String sheetName, String instrumentType, String originalArtistName, InputStream dataSheet, int idUser ) {
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ) {
			String strSql = "INSERT INTO T_Sheet (sheetName, instrumentType, originalArtistName, dataSheet, idUser) values (?,?,?,?,?)";
			System.out.println(strSql);
			try ( PreparedStatement statement  = connection.prepareStatement( strSql ) ) {
				statement.setString( 1, sheetName );
				statement.setString( 2, instrumentType );
				statement.setNString(3, originalArtistName);
				statement.setBlob(4, dataSheet);
				statement.setInt(5, idUser);
				statement.executeUpdate();
			}
		} catch ( Exception exception ) {
			throw new RuntimeException( exception );
		}
	}

}
