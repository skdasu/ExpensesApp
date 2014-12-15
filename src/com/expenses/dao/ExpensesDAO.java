package com.expenses.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.expenses.model.Expense;

public class ExpensesDAO {
	
	public String addExpenses(Expense ex){
		Connection dbConnection = DBConnectionManager.getInstance().getDBConnection();
		int rs = 0;
		try {
			//String sql = "INSERT INTO expenses ('dateOfPurchase','merchant','itemName','price','quantity','units','rate','category','organic','subcategory','place') VALUES (?,?,?,?,?,?,?,?,?,?,?);";
			String sql = "INSERT INTO expenses (dateOfPurchase, merchant, itemName, price, quantity, units, rate, category, organic, subcategory, place) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement pstmt = dbConnection.prepareStatement(sql);
			
			int i = 1;
			pstmt.setDate(i++, new java.sql.Date(ex.getDateOfPurchaseinDateFormat().getTime())); //'date'
			//pstmt.setDate(i++, (Date) );
			pstmt.setInt(i++, ex.getMerchantID());//'merchant'
			pstmt.setString(i++, ex.getItemName());//'itemName'
			pstmt.setFloat(i++, ex.getPrice());//'price'
			pstmt.setFloat(i++, ex.getQuantity());//'quantity'
			pstmt.setString(i++, ex.getUnits());//'units'
			pstmt.setFloat(i++, ex.getRate());//'rate'
			pstmt.setInt(i++, ex.getCategoryID());//'category'
			pstmt.setBoolean(i++, ex.isOrganic());//'organic'
			pstmt.setInt(i++, ex.getSubCategoryID());//'subcategory'
			pstmt.setString(i++, ex.getPlace());//'place'
			
			//pstmt.setString(1, name);
			rs = pstmt.executeUpdate();
			System.out.println("result  addExpenses  -- DAO "+String.valueOf(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return String.valueOf(rs);
	}
	
	public String getExpenses(){
		Connection dbConnection = DBConnectionManager.getInstance().getDBConnection();
		String resultValue = "";
		ArrayList<Expense> expenses = new ArrayList<Expense>();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(JsonMethod.FIELD, Visibility.ANY);
		try {
			/*String sql = "SELECT e.idExpenses AS expensesID,"
					+ "Date(e.dateOfPurchase) AS dateOfPurchase,"
					+ "e.itemName AS itemName,"
					+ "e.price AS price,"
					+ "e.place AS place,"
					+ "e.quantity AS quantity,"
					+ "e.units AS units,"
					+ "e.rate AS rate,"
					+ "e.organic AS isOrganic,"
					+ "m.merchantName AS merchantName,"
					+ "c.categoryName AS categoryName,"
					+ "sc.subcategoryName AS subCategoryName"
					+ "FROM expenses e "
					+ "INNER JOIN merchants m ON e.merchant = m.merchantID "
					+ "INNER JOIN category c ON e.category = c.categoryID "
					+ "INNER JOIN subcategory sc ON e.subcategory = sc.subcategoryID "
					+ "ORDER BY e.dateOfPurchase";*/
			String sql = "SELECT e.idExpenses AS expensesID, Date(e.dateOfPurchase) AS dateOfPurchase, e.itemName AS itemName, "
					+ "e.price AS price, e.place AS place, e.quantity AS quantity, e.units AS units,  e.rate AS rate, e.organic AS isOrganic, "
					+ "m.merchantName AS merchantName, c.categoryName AS categoryName, sc.subcategoryName AS subCategoryName "
					+ "FROM expenses e "
					+ "INNER JOIN merchants m ON e.merchant = m.merchantID "
					+ "INNER JOIN category c ON e.category = c.categoryID "
					+ "INNER JOIN subcategory sc ON e.subcategory = sc.subcategoryID "
					+ "ORDER BY e.dateOfPurchase";
			PreparedStatement pstmt = dbConnection.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs!=null){
				while(rs.next()){
					//resultValue+="<option value='"+rs.getString("categoryID")+"'>"+rs.getString("categoryName")+"</option>";
					/*resultValue+="<tr><td>"+rs.getString("dateOfPurchase")+"</td><td>"+rs.getString("merchantName")+"</td><td>"+rs.getString("categoryName")+"</td>"
							+ "<td>"+rs.getString("subCategoryName")+"</td><td>"+rs.getString("place")+"</td><td>"+rs.getString("itemName")+"</td>"
							+ "<td>"+rs.getString("price")+"</td><td>"+rs.getString("quantity")+"</td><td>"+rs.getString("units")+"</td><td>"+rs.getString("rate")+"</td></tr>";*/
					Expense e = new Expense();
					e.setExpensesID(rs.getString("expensesID"));
					e.setDateOfPurchase(rs.getString("dateOfPurchase"));
					e.setMerchantName(rs.getString("merchantName"));
					e.setCategoryName(rs.getString("categoryName"));
					e.setSubcategoryName(rs.getString("subCategoryName"));
					e.setPlace(rs.getString("place"));
					e.setOrganic(rs.getString("isOrganic"));
					e.setItemName(rs.getString("itemName"));
					e.setPrice(rs.getString("price")); 
					e.setQuantity(rs.getString("quantity"));
					e.setUnits(rs.getString("units"));
					e.setRate(rs.getString("rate"));
					expenses.add(e);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try{
			resultValue = mapper.writeValueAsString(expenses);
			//System.out.println("From Service::: "+mapper.writeValueAsString(expenses));
			//System.out.println("From DAO::: "+resultValue);
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

}
