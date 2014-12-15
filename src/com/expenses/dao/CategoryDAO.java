package com.expenses.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.expenses.model.CategoryListing;
import com.expenses.model.SubCategoryItem;

public class CategoryDAO {
	
	public String getCategories(){
		Connection dbConnection = DBConnectionManager.getInstance().getDBConnection();
		String resultValue = "";
		ArrayList<CategoryListing> categories = new ArrayList<CategoryListing>();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(JsonMethod.FIELD, Visibility.ANY);
		
		try {
			PreparedStatement pstmt = dbConnection.prepareStatement("select * from category ORDER BY categoryName ASC;");
			
			ResultSet rs = pstmt.executeQuery();
			if(rs!=null){
				while(rs.next()){
					//resultValue+="<option value='"+rs.getString("categoryID")+"'>"+rs.getString("categoryName")+"</option>";
					CategoryListing c = new CategoryListing();
					c.setCategoryID(rs.getString("categoryID"));
					c.setCategoryName(rs.getString("categoryName"));
					categories.add(c);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try{
			resultValue = mapper.writeValueAsString(categories);
			//System.out.println("From Service::: "+mapper.writeValueAsString(categories));
			//System.out.println("From Service::: "+resultValue);
		}
		catch (JsonGenerationException e) {
			 
			e.printStackTrace();
	 
		} catch (JsonMappingException e) {
	 
			e.printStackTrace();
	 
		} catch (IOException e) {
	 
			e.printStackTrace();
	 
		}
		return resultValue;
	}
	
	public String getCategoriesAndSubCategories(){
		Connection dbConnection = DBConnectionManager.getInstance().getDBConnection();
		String resultValue = "";
		//ArrayList<SubCategoryItem> al = new ArrayList<SubCategoryItem>();
		HashMap<String, ArrayList<SubCategoryItem>> myMap = new HashMap<String, ArrayList<SubCategoryItem>>();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(JsonMethod.FIELD, Visibility.ANY);
		try {
			//PreparedStatement pstmt = dbConnection.prepareStatement("select * from category ORDER BY categoryName ASC;");
			String sqlStmt = "SELECT c.categoryid AS categoryID, sc.subcategoryid AS subcategoryID, c.categoryname AS categoryName,"
					+ "sc.subcategoryname AS subcategoryName FROM subcategory AS sc RIGHT OUTER JOIN category AS c ON sc.categoryid = c.categoryid "
					+ "ORDER BY c.categoryid ASC, sc.subcategoryid ASC ";
			PreparedStatement pstmt = dbConnection.prepareStatement(sqlStmt);
			ResultSet rs = pstmt.executeQuery();
			
			
			if(rs!=null){
				while(rs.next()){
					/*if(myMap.containsKey(rs.getString("categoryName"))){
						ArrayList<SubCategoryItem> al = myMap.get(rs.getString("categoryName"));
						SubCategoryItem sci = new SubCategoryItem();
						sci.setCategoryID(rs.getString("categoryID"));
						sci.setCategoryname(rs.getString("categoryName"));
						sci.setSubCategoryID(rs.getString("subcategoryID"));
						sci.setSubCategoryName(rs.getString("subcategoryName"));
						al.add(sci);
						myMap.put(rs.getString("categoryName"), al);
					}else{
						ArrayList<SubCategoryItem> al = new ArrayList<SubCategoryItem>();
						SubCategoryItem sci = new SubCategoryItem();
						sci.setCategoryID(rs.getString("categoryID"));
						sci.setCategoryname(rs.getString("categoryName"));
						sci.setSubCategoryID(rs.getString("subcategoryID"));
						sci.setSubCategoryName(rs.getString("subcategoryName"));
						al.add(sci);
						myMap.put(rs.getString("categoryName"), al);
					}*/
					resultValue += "<tr><td>"+rs.getString("categoryName")+"</td><td>"+rs.getString("subcategoryName")+ "</td></tr>";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try{
			//resultValue = mapper.writeValueAsString(myMap);
			System.out.println("From Service::: "+mapper.writeValueAsString(myMap));
			System.out.println("From Service::: "+resultValue);
		}
		catch (JsonGenerationException e) {
			 
			e.printStackTrace();
	 
		} catch (JsonMappingException e) {
	 
			e.printStackTrace();
	 
		} catch (IOException e) {
	 
			e.printStackTrace();
	 
		}
		return resultValue;
	}
	
	public String getSubCategories(){
		Connection dbConnection = DBConnectionManager.getInstance().getDBConnection();
		String resultValue = "";
		ArrayList<SubCategoryItem> subCategories = new ArrayList<SubCategoryItem>();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(JsonMethod.FIELD, Visibility.ANY);
		try {
			PreparedStatement pstmt = dbConnection.prepareStatement("select * from subcategory ORDER BY subcategoryName ASC;");
			ResultSet rs = pstmt.executeQuery();
			if(rs!=null){
				while(rs.next()){
					//resultValue +="<option value='"+rs.getString("subcategoryID")+"'>"+rs.getString("subcategoryName")+"</option>";
					SubCategoryItem sc = new SubCategoryItem();
					sc.setCategoryID(rs.getString("categoryID"));
					sc.setSubCategoryID(rs.getString("subcategoryID"));
					sc.setSubCategoryName(rs.getString("subcategoryName"));
					subCategories.add(sc);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try{
			resultValue = mapper.writeValueAsString(subCategories);
			//System.out.println("From SubCategory Service::: "+mapper.writeValueAsString(subCategories));
			System.out.println("From SubCat DAO::: "+resultValue);
		}
		catch (JsonGenerationException e) {
			 
			e.printStackTrace();
	 
		} catch (JsonMappingException e) {
	 
			e.printStackTrace();
	 
		} catch (IOException e) {
	 
			e.printStackTrace();
	 
		}
		return resultValue;
	}
	
	public String addCategories(String name){
		Connection dbConnection = DBConnectionManager.getInstance().getDBConnection();
		int rs = 0;
		System.out.println("in addCategoryListing DAO call "+name);
		if(name.trim().length()>1){
			try {
				String sql = "INSERT INTO category (categoryName) VALUES (?)";
				PreparedStatement pstmt = dbConnection.prepareStatement(sql);
				pstmt.setString(1, name);
				rs = pstmt.executeUpdate();
				System.out.println("result  addCategories  -- DAO "+String.valueOf(rs));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return String.valueOf(rs);
	}
	
	public String addSubCategories(String name, int categoryID){
		Connection dbConnection = DBConnectionManager.getInstance().getDBConnection();
		int rs = 0;
		System.out.println("SCN:: "+name + "  ID::: "+categoryID);
		try {
			String sql = "INSERT INTO subcategory (categoryID, subcategoryName) VALUES (?,?)";
			PreparedStatement pstmt = dbConnection.prepareStatement(sql);
			pstmt.setInt(1, categoryID);
			pstmt.setString(2, name);
			rs = pstmt.executeUpdate();
			System.out.println("result  addSubCategories  -- DAO "+rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return String.valueOf(rs);
	}

}
