package com.expenses.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class Expense {
	
	private int expensesID;
	private Date dateOfPurchase;
	private String merchantName;
	private int merchantID;
	private String categoryName;
	private int categoryID;
	private String subcategoryName;
	private int subCategoryID;
	private String place;
	private boolean isOrganic;
	private String itemName;
	private float price; 
	private float quantity;
	private String units;
	private float rate;
	
	public int getExpensesID() {
		return expensesID;
	}
	public void setExpensesID(String expensesID) {
		this.expensesID = Integer.parseInt(expensesID);
	}
	
	public String getDateOfPurchase() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
		return dateFormatter.format(this.dateOfPurchase.getTime());
	}
	public Date getDateOfPurchaseinDateFormat() {
		return this.dateOfPurchase;
	}
	public void setDateOfPurchase(String dateStr) {
		//System.out.println("::::::::: Expense Date ::::::::::::"+dateStr);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try{
			this.dateOfPurchase = formatter.parse(dateStr);
		}catch (ParseException e) {
			System.out.println("Date parse exception from expense model");
			//System.out.println(":::::::: "+);
			e.printStackTrace();
		}
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public int getMerchantID() {
		return merchantID;
	}
	public void setMerchantID(int merchantID) {
		this.merchantID = merchantID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public String getSubcategoryName() {
		return subcategoryName;
	}
	public void setSubcategoryName(String subcategoryName) {
		this.subcategoryName = subcategoryName;
	}
	public int getSubCategoryID() {
		return subCategoryID;
	}
	public void setSubCategoryID(int subCategoryID) {
		this.subCategoryID = subCategoryID;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public boolean isOrganic() {
		return isOrganic;
	}
	public void setOrganic(String isOrganic) {
		this.isOrganic = isOrganic.equalsIgnoreCase("0")?false:true;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = Float.parseFloat(price);
	}
	public float getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = Float.parseFloat(quantity);
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(String rate) {
		//rate = String.format("%.2f", rate);
		//System.out.println(":::::: Expense Model ::::::::"+rate);
		this.rate = Float.parseFloat(rate);
	}
	
	@Override
	public String toString() {
		return "Expense [expensesID=" + expensesID + ", dateOfPurchase="
				+ dateOfPurchase + ", merchantName=" + merchantName
				+ ", merchantID=" + merchantID + ", categoryName="
				+ categoryName + ", categoryID=" + categoryID
				+ ", subcategoryName=" + subcategoryName + ", subCategoryID="
				+ subCategoryID + ", place=" + place + ", isOrganic="
				+ isOrganic + ", itemName=" + itemName + ", price=" + price
				+ ", quantity=" + quantity + ", units=" + units + ", rate="
				+ rate + "]";
	}
	
	

}
