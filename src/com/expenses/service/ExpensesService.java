package com.expenses.service;

import com.expenses.dao.ExpensesDAO;
import com.expenses.model.Expense;

public class ExpensesService {

	public String addExpense(Expense ex) {
		//System.out.println("in addCategoryListing service call " + );
		ExpensesDAO daoObject = new ExpensesDAO();
		return daoObject.addExpenses(ex);
	}
	
	public String getExpenses(){
		ExpensesDAO daoObject = new ExpensesDAO();
		return daoObject.getExpenses();
	}

}
