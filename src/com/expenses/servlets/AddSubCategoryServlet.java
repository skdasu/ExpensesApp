package com.expenses.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.expenses.model.SubCategoryItem;
import com.expenses.service.CategoryService;

public class AddSubCategoryServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		
		CategoryService serviceObj = new CategoryService();
		SubCategoryItem inputSubCategory = new SubCategoryItem();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(JsonMethod.FIELD, Visibility.ANY);
		try{
			//{"categoryID":4,"subCategoryName":"subCategoryName"}
			System.out.println("JSON Input value::: "+request.getParameter("newSubCategory")+"\n-----------------------------\n\n\n\n");
			inputSubCategory = mapper.readValue(request.getParameter("newSubCategory"), inputSubCategory.getClass());
			System.out.println("::::::::::::::: "+inputSubCategory.toString());
		}catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String result = serviceObj.addSubCategoryListing(inputSubCategory);
		resp.getWriter().write(result);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
