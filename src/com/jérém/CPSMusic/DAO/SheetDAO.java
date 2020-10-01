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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import com.jérém.CPSMusic.enumeration.WichSort;
import com.jérém.CPSMusic.objects.Sheet;

public class SheetDAO extends DAOContext{
	
//	public static int getSheetCount(String instrument) {
//		String strSql = "";
//		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ){
//			if (instrument == "Tous") {
//				strSql = "SELECT count(idSheet) FROM T_Sheet";
//			} else {
//				strSql = "SELECT count(idSheet) FROM T_Sheet WHERE instrumentType = ?";
//			}
//			try ( Statement statement  = connection.createStatement() ) {
//				try ( ResultSet resultSet = statement.executeQuery( strSql ) ) {
//					resultSet.next();
//					return resultSet.getInt( 1 );
//				}
//			}
//			
//		} catch ( Exception exception ) {
//			
//			throw new RuntimeException( exception );
//			
//		}
//	}
	
	
	public static List<Integer> getSheetListSorted(String sortParameter) {
		String strSql;
		List<Integer> idsList= new ArrayList<Integer>();
		WichSort state = instrumentOrPriceOrEverything(sortParameter);
		if (state == WichSort.EVERYTHING) {
			strSql = "SELECT idSheet FROM T_Sheet";
			idsList = getSheetIdsUnSorted(strSql);
		} else if (state == WichSort.INSTRUMENT) {
			strSql = getTheQueryIfInstrument(sortParameter);
			idsList = getSheetIdsSortedByInstrument(strSql, sortParameter);
		} else {
			strSql = getTheQueryIfPrice(sortParameter);
			idsList = getSheetIdsSortedByPrice(strSql);
		}
		
		return idsList;
	}
	
	
	private static List<Integer> getSheetIdsUnSorted(String strSql) {
		List<Integer> idsList= new ArrayList<Integer>();
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ){
			PreparedStatement statement = connection.prepareStatement(strSql);
				try ( ResultSet resultSet = statement.executeQuery() ) {
					while (resultSet.next()) {
						idsList.add(resultSet.getInt(1));
					}
					return idsList;
				}
			
		} catch ( Exception exception ) {
			
			throw new RuntimeException( exception );
			
		}
	}
	
	private static List<Integer> getSheetIdsSortedByInstrument(String strSql, String sortParameter) {
		List<Integer> idsList= new ArrayList<Integer>();
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ){
			PreparedStatement statement = connection.prepareStatement(strSql);
				statement.setString(1, sortParameter);
				try ( ResultSet resultSet = statement.executeQuery() ) {
					while (resultSet.next()) {
						idsList.add(resultSet.getInt(1));
					}
					return idsList;
				}
			
		} catch ( Exception exception ) {
			
			throw new RuntimeException( exception );
			
		}
	}
	
	private static List<Integer> getSheetIdsSortedByPrice(String strSql) {
		List<Integer> idsList= new ArrayList<Integer>();
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ){
			PreparedStatement statement = connection.prepareStatement(strSql);
				try ( ResultSet resultSet = statement.executeQuery() ) {
					while (resultSet.next()) {
						idsList.add(resultSet.getInt(1));
					}
					return idsList;
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
                int price = result.getInt("price");
                 
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
                sheet.setPrice(price);
            }          
             
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
            throw ex;
        }      
         
        return sheet;
    }
	
	

	
	
//	public static void updateSheet( Sheet sheet ) {
//		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ){
//
//			String strSql = "UPDATE Sheet SET sheetName=?, instrumentType=?, originalArtistName=? WHERE idSheet=?";
//			try ( PreparedStatement statement  = connection.prepareStatement( strSql ) ) {
//				statement.setString( 1, sheet.getSheetName() );
//				statement.setString( 2, sheet.getInstrumentType() );
//				statement.setString( 3, sheet.getOriginalArtistName() );
//				statement.setInt( 4, sheet.getIdSheet() );
//				statement.executeUpdate();
//			}
//			
//		} catch ( Exception exception ) {
//			
//			throw new RuntimeException( exception );
//			
//		}
//	}
	
	
	private static WichSort instrumentOrPriceOrEverything (String sortParameter) {
		WichSort state;
		String[] compareInstrument = new String[]{"PIANO","VIOLON","CLARINETTE","GUITARE"};
		if (sortParameter == "TOUS") {
			state = WichSort.EVERYTHING;
		} else if (Arrays.asList(compareInstrument).contains(sortParameter)) {
			state = WichSort.INSTRUMENT;
		} else {
			state = WichSort.PRICE;
		}
		return state;
	}
	
	private static String getTheQueryIfInstrument( String sortParameter) {
		String strSql;
		if (sortParameter != "PIANO") {
			strSql = "SELECT idSheet FROM T_Sheet WHERE instrumentType = ?";
		} else if (sortParameter == "VIOLON") {
			strSql = "SELECT idSheet FROM T_Sheet WHERE instrumentType = ?";
		}  else if (sortParameter == "CLARINETTE") {
			strSql = "SELECT idSheet FROM T_Sheet WHERE instrumentType = ?";
		}  else {
			strSql = "SELECT idSheet FROM T_Sheet WHERE instrumentType = ?";
		}  
		return strSql;
	}
	
	private static String getTheQueryIfPrice( String sortParameter) {
		String strSql;
		if (sortParameter == "ZEROACINQ") {
			strSql = "SELECT idSheet FROM T_Sheet WHERE price BETWEEN 0 AND 5";
		} else if (sortParameter == "CINQADIX") {
			strSql = "SELECT idSheet FROM T_Sheet WHERE price BETWEEN 5 AND 10";
		}  else if (sortParameter == "DIXAVINGT") {
			strSql = "SELECT idSheet FROM T_Sheet WHERE price BETWEEN 10 AND 20";
		}  else {
			strSql = "SELECT idSheet FROM T_Sheet WHERE price BETWEEN 20 AND 1000";
		}  
		return strSql;
	}
	
	
	public static void addSheet( String sheetName, String instrumentType, String originalArtistName, InputStream dataSheet, int idUser, int price ) {
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ) {
			String strSql = "INSERT INTO T_Sheet (sheetName, instrumentType, originalArtistName, dataSheet, idUser, price) values (?,?,?,?,?,?)";
			System.out.println(strSql);
			try ( PreparedStatement statement  = connection.prepareStatement( strSql ) ) {
				statement.setString( 1, sheetName );
				statement.setString( 2, instrumentType.toUpperCase() );
				statement.setNString(3, originalArtistName);
				statement.setBlob(4, dataSheet);
				statement.setInt(5, idUser);
				statement.setInt(6, price);
				statement.executeUpdate();
			}
		} catch ( Exception exception ) {
			throw new RuntimeException( exception );
		}
	}

}
