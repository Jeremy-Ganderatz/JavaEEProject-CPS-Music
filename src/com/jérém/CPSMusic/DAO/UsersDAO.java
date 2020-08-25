package com.jérém.CPSMusic.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jérém.CPSMusic.objects.Users;

public class UsersDAO extends DAOContext {
	
	public static Users isValidLogin( String login, String password ) {
		
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ) {
			String strSql = "SELECT * FROM T_Users WHERE login=? AND password=?";
			System.out.println(strSql);
			try ( PreparedStatement statement  = connection.prepareStatement( strSql ) ) {
				statement.setString( 1, login );
				statement.setString( 2, password );
				try ( ResultSet resultSet = statement.executeQuery() ) {
					if ( resultSet.next() ) {
						increaseUserConnectionNumber(resultSet, login, password);
						System.out.println("there is a resultSet");
						return new Users(
								resultSet.getInt( "idUser" ),
								resultSet.getString( "login" ),
								resultSet.getString( "password" ),
								resultSet.getInt( "connectionNumber" )
						);
					} else {
						return null;
					}
				}
			}
		} catch ( Exception exception ) {
			throw new RuntimeException( exception );
		}
	}
	
	public static void createAccount( String login, String password ) {
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ) {
			String strSql = "INSERT INTO T_Users (login, password, connectionNumber) values (?,?,0)";
			System.out.println(strSql);
			try ( PreparedStatement statement  = connection.prepareStatement( strSql ) ) {
				statement.setString( 1, login );
				statement.setString( 2, password );
				statement.executeUpdate();
			}
		} catch ( Exception exception ) {
			throw new RuntimeException( exception );
		}
	}
	
	private static void increaseUserConnectionNumber (ResultSet resultSet, String login1, String password1) {
		
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ) {
			
			String strSql = "UPDATE T_Users SET connectionNumber = connectionNumber + 1 WHERE login =? AND password=?";
			System.out.println(strSql);
			try ( PreparedStatement statement  = connection.prepareStatement( strSql ) ) {
				statement.setString( 1, login1 );
				statement.setString( 2, password1 );
				statement.executeUpdate();
			}
		} catch ( Exception exception ) {
			throw new RuntimeException( exception );
		}
		
	}
	
}
