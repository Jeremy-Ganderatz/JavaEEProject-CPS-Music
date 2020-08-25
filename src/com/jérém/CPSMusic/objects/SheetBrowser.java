package com.jérém.CPSMusic.objects;

import java.util.ArrayList;
import java.util.List;

import com.jérém.CPSMusic.DAO.SheetDAO;

public class SheetBrowser {
	
	private int currentPosition = 1;
	private Sheet currentSheet;
	private int sheetsCount = SheetDAO.getSheetCount();
	
	private List<ShoppingCartLine> shoppingCart = new ArrayList<>();
	
	
	public SheetBrowser() {
		currentSheet = SheetDAO.getSheetById( currentPosition );
	}
	
	public Sheet getCurrentSheet() {
		return currentSheet;
	}

	public List<ShoppingCartLine> getShoppingCart() {
		return shoppingCart;
	}
	
	
	public int getShoppingCartSize() {
		int fullQuantity = 0;
		for (ShoppingCartLine shoppingCartLine : shoppingCart) {
			fullQuantity += shoppingCartLine.getQuantity();
		}
		return fullQuantity;
	}
	
	
	public void goPrevious() {
		if ( --currentPosition < 1 ) {
			currentPosition = sheetsCount;
		}
		currentSheet = SheetDAO.getSheetById( currentPosition );
	}
	
	public void goNext() {
		if ( ++currentPosition > sheetsCount ) {
			currentPosition = 1;
		}
		currentSheet = SheetDAO.getSheetById( currentPosition );
	}
	
	public void addCurrentSheet() {
		for (ShoppingCartLine shoppingCartLine : shoppingCart) {
			if ( shoppingCartLine.getSheet().getIdSheet() == currentSheet.getIdSheet() ) {
				shoppingCartLine.increaseQuantity();
				return;
			}
		}
	shoppingCart.add( new ShoppingCartLine( currentSheet, 1 ) );
	}

}
