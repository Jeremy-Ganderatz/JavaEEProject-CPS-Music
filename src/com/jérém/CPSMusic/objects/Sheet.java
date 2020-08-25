package com.jérém.CPSMusic.objects;

import java.sql.Blob;

public class Sheet {
	
	private int idSheet;
	private String sheetName;
	private String instrumentType;
	private String originalArtistName;
	private Blob dataSheet;
	
	
	public Sheet() {
		this( 0, "unknown", "unknown", "unknow", null );
	}
	
	
	public Sheet( int idSheet, String sheetName, String instrumentType, String originalArtistName, Blob dataSheet ) {
		this.setIdSheet( idSheet );
		this.setSheetName( sheetName );
		this.setInstrumentType( instrumentType );
		this.setOriginalArtistName( originalArtistName );
		this.setDataSheet( dataSheet );
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
	
	
}