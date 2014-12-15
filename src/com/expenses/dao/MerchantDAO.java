package com.expenses.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.expenses.model.Merchant;

public class MerchantDAO {
	
	public String getMerchants(){
		Connection dbConnection = DBConnectionManager.getInstance().getDBConnection();
		String resultValue = "";
		ArrayList<Merchant> merchantListing = new ArrayList<Merchant>();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(JsonMethod.FIELD, Visibility.ANY);
		try {
			PreparedStatement pstmt = dbConnection.prepareStatement("select * from merchants ORDER BY merchantName ASC;");
			//pstmt.setInt(1, actorID);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs!=null){
				while(rs.next()){
					Merchant m = new Merchant();
					m.setMerchantID(rs.getString("merchantID"));
					m.setMerchantName(rs.getString("merchantName"));
					merchantListing.add(m);
					//resultValue +="<option value='"+rs.getString("merchantID")+"'>"+rs.getString("merchantName")+"</option>";
					//resultValue +="<tr><td>"+rs.getString("merchantID")+"</td><td>"+rs.getString("merchantName")+ "</td></tr>";
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{
			//System.out.println("From Merchant Service::: "+mapper.writeValueAsString(merchantListing));
			resultValue = mapper.writeValueAsString(merchantListing);
		}catch(JsonGenerationException e){
			e.printStackTrace();
		}catch(JsonMappingException e ){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		return resultValue;
	}
	
	public String addMerchants(String name){
		Connection dbConnection = DBConnectionManager.getInstance().getDBConnection();
		//String resultValue = "";
		int rs = 0;
		try {
			String sql = "INSERT INTO merchants (merchantName) VALUES (?)";
			PreparedStatement pstmt = dbConnection.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeUpdate();
			//ResultSet rs = pstmt.executeQuery();
			System.out.println("result -- DAO "+rs);
			
			/*if(rs!=null){
				while(rs.next()){
					resultValue += ", "+ rs.getString("merchantName");
				}
			}*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return String.valueOf(rs);
	}

}
