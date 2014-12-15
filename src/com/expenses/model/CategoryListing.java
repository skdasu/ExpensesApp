package com.expenses.model;

public class CategoryListing {
	
	private int categoryID;
	private String categoryName;
	
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(String categoryID) {
		this.categoryID = Integer.parseInt(categoryID);
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
