package com.expenses.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.expenses.model.Expense;
import com.expenses.service.ExpensesService;



public class AddExpenseServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ExpensesService serviceObj = new ExpensesService();
		Expense inputExpense = new Expense();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(JsonMethod.FIELD, Visibility.ANY);
		try{
			System.out.println("JSON Input value::: "+request.getParameter("dataItem")+"\n-----------------------------\n\n\n\n");
			inputExpense = mapper.readValue(request.getParameter("newExpenseItem"), inputExpense.getClass());
			System.out.println("::::::::::::::: "+inputExpense.toString());
		}catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String result = serviceObj.addExpense(inputExpense);
		response.getWriter().write(result);
		/*String newCategoryName = req.getParameter("dataItem");
		String result = serviceObj.addExpense(newCategoryName);
		resp.getWriter().write(result);*/
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
