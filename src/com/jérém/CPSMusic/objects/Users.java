package com.jérém.CPSMusic.objects;

public class Users {
	
	private int idUser;
	private String login;
	private String password;
	private int connectionNumber;
	
	
	public Users() {
		this( 0, "john", "doe", 0 );
	}
	
	public Users( int idUser, String login, String password, int connectionNumber ) {
		this.setIdUser( idUser );
		this.setLogin( login );
		this.setPassword( password );
		this.setConnectionNumber( connectionNumber );
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getConnectionNumber() {
		return connectionNumber;
	}

	public void setConnectionNumber(int connectionNumber) {
		this.connectionNumber = connectionNumber;
	}
}
