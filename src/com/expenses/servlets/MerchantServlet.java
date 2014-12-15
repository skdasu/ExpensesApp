package com.expenses.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.expenses.service.MerchantService;

/**
 * Servlet implementation class JDBCServlet
 */
public class MerchantServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MerchantService serviceObj = new MerchantService(); 
		String str = request.getParameter("dataItem");
		String merchantsListing = serviceObj.getMerchantsListing(str);
		response.getWriter().write(merchantsListing);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
