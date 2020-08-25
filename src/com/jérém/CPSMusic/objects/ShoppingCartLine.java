package com.jérém.CPSMusic.objects;

public class ShoppingCartLine {

	private Sheet sheet;
	private int quantity;
	
	
	public ShoppingCartLine( Sheet sheet, int quantity ) {
		setSheet( sheet );
		setQuantity( quantity );
	}
	
	
	public Sheet getSheet() {
		return sheet;
	}
	
	public void setSheet(Sheet sheet) {
		if ( sheet == null ) throw new NullPointerException();
		this.sheet = sheet;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	public void increaseQuantity() {
		this.quantity++;
	}
}