package com.jérém.CPSMusic.objects;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jérém.CPSMusic.DAO.SheetDAO;
import com.jérém.CPSMusic.enumeration.SortedSheet;

public class SheetBrowser {
	
	private List<Integer> idsList;
	private int currentPosition;
	private Sheet currentSheet;
	private int index;
	private List<ShoppingCartLine> shoppingCart = new ArrayList<>();
	
	
	public SheetBrowser(SortedSheet object) throws SQLException, IOException {
		String sortParameter = object.name();
		idsList = SheetDAO.getSheetListSorted(sortParameter);
		index = 0;
		currentPosition = idsList.get(index);
		currentSheet = SheetDAO.getSheetById( currentPosition );
	}
	
	public Sheet getCurrentSheet() throws SQLException, IOException {
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
	
	public void goPrevious() throws SQLException, IOException {
		if ( --index < 0 ) {
			index = idsList.size()-1;
			currentPosition = idsList.get(index);
		}
		
		currentPosition = idsList.get(index);
		currentSheet = SheetDAO.getSheetById( currentPosition );
	}
	
	public void goNext() throws SQLException, IOException {
		if ( ++index > idsList.size()-1 ) {
			index = 0;
			currentPosition = idsList.get(index);
		}
		currentPosition = idsList.get(index);
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
