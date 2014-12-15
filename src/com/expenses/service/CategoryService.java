package com.expenses.service;

import com.expenses.dao.CategoryDAO;
import com.expenses.model.SubCategoryItem;

public class CategoryService {
	
	public String getCategoryListing(){
		//System.out.println("in getCategoryListing service call "+requestCall);
		CategoryDAO daoObject = new CategoryDAO();
		String result = "";
		/*if(requestCall.equalsIgnoreCase("table")){
			//System.out.println("in getCategoryListing service call table");
			result = daoObject.getCategoriesAndSubCategories();
		}else{
			result = daoObject.getCategories(); 
		}*/
		//result = daoObject.getCategoriesAndSubCategories();
		result = daoObject.getCategories();
		return result;
	}
	
	public String addCategoryListing(String name){
		System.out.println("in addCategoryListing service call "+name);
		CategoryDAO daoObject = new CategoryDAO();
		return daoObject.addCategories(name);
	}
	
	public String getSubCategoryListing(){
		System.out.println("in get sub CategoryListing service call");
		CategoryDAO daoObject = new CategoryDAO();
		return daoObject.getSubCategories();
	}
	
	public String addSubCategoryListing(SubCategoryItem s){
		//System.out.println("in addSubCategoryListing service call "+s.getSubCategoryName());
		CategoryDAO daoObject = new CategoryDAO();
		return daoObject.addSubCategories(s.getSubCategoryName(), s.getCategoryID());
	}
}
