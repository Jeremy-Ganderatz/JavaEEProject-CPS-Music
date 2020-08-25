package com.jérém.CPSMusic.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
	
	public static Sheet getSheetById( int idSheet ) {
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ){
			System.out.println( "connection to the database" );
			String strSql = "SELECT * FROM T_Sheet WHERE idSheet=?";
			try ( PreparedStatement statement  = connection.prepareStatement( strSql ) ) {
				statement.setInt( 1, idSheet );
				try ( ResultSet resultSet = statement.executeQuery() ) {
					resultSet.next();
					return new Sheet(
							resultSet.getInt( "idSheet" ),
							resultSet.getString( "sheetName" ),
							resultSet.getString( "instrumentType" ),
							resultSet.getString( "originalArtistName" ),
							resultSet.getBlob("dataSheet")
					);
				}
			}
			
		} catch ( Exception exception ) {
			
			throw new RuntimeException( exception );
			
		}
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

}
