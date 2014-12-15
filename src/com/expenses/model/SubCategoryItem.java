package com.expenses.model;

public class SubCategoryItem {
	
	private int categoryID;
	//private String categoryname;
	private int subCategoryID;
	private String subCategoryName;
	
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(String categoryID) {
		this.categoryID = Integer.parseInt(categoryID);
	}
	/*public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}*/
	public int getSubCategoryID() {
		return subCategoryID;
	}
	public void setSubCategoryID(String subCategoryID) {
		this.subCategoryID = Integer.parseInt(subCategoryID);
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	@Override
	public String toString() {
		return "SubCategoryItem [categoryID=" + categoryID + ", subCategoryID="
				+ subCategoryID + ", subCategoryName=" + subCategoryName + "]";
	}
	
	/*@Override
	public String toString() {
		return "SubCategoryItem {categoryID=" + categoryID + ", categoryname="
				+ categoryname + ", subCategoryID=" + subCategoryID
				+ ", subCategoryName=" + subCategoryName + "}";
	}*/
	
	
	
	

}
