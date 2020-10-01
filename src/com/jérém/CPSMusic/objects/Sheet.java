package com.jérém.CPSMusic.objects;

import java.sql.Blob;

public class Sheet {
	
	private int idSheet;
	private String sheetName;
	private String instrumentType;
	private String originalArtistName;
	private Blob dataSheet;
	private int idUser;
	private String base64Image;
	private int price;
	
	
	public Sheet() {
		this( 0, "unknown", "unknown", "unknow", null, 0 );
	}
	
	
	public Sheet( int idSheet, String sheetName, String instrumentType, String originalArtistName, Blob dataSheet, int idUser ) {
		this.setIdSheet( idSheet );
		this.setSheetName( sheetName );
		this.setInstrumentType( instrumentType );
		this.setOriginalArtistName( originalArtistName );
		this.setDataSheet( dataSheet );
		this.setIdUser( idUser );
		this.setPrice( price );
	}



	public int getIdSheet() {
		return idSheet;
	}


	public void setIdSheet(int idSheet) {
		this.idSheet = idSheet;
	}


	public String getSheetName() {
		return sheetName;
	}


	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}


	public String getInstrumentType() {
		return instrumentType;
	}


	public void setInstrumentType(String instrumentType) {
		this.instrumentType = instrumentType;
	}


	public String getOriginalArtistName() {
		return originalArtistName;
	}


	public void setOriginalArtistName(String originalArtistName) {
		this.originalArtistName = originalArtistName;
	}


	public Blob getDataSheet() {
		return dataSheet;
	}


	public void setDataSheet(Blob dataSheet) {
		this.dataSheet = dataSheet;
	}


	public String getBase64Image() {
		return base64Image;
	}


	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}


	public int getIdUser() {
		return idUser;
	}


	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}

}