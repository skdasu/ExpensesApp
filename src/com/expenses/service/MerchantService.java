package com.expenses.service;

import com.expenses.dao.MerchantDAO;

public class MerchantService {
	
	public String getMerchantsListing(String a){
		//System.out.println("name -- getMerchantListing Serive called ");
		MerchantDAO daoObject = new MerchantDAO();
		return daoObject.getMerchants();
	}
	
	public String addMerchantsListing(String name){
		System.out.println("name -- service "+name);
		MerchantDAO daoObject = new MerchantDAO();
		return daoObject.addMerchants(name);
	}

}
