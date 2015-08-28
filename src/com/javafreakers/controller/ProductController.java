package com.javafreakers.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javafreakers.dao.ProductDao;
import com.javafreakers.model.Product;

@Controller
public class ProductController {
@Autowired
ProductDao productDao;
@RequestMapping("productsHome.html")
public String productsHome(HttpServletRequest request,HttpSession session){
	String offset = (String)request.getParameter("offSet");
	int result = 10;
	int size ;
	List<Product> products ;
	if(offset!=null){
		int offsetreal = Integer.parseInt(offset);
		offsetreal = offsetreal*10;
		 products = productDao.getProduct(result,offsetreal);
	}else{
		products = productDao.getProduct(result,0);
		size = productDao.getSize();
		session.setAttribute("size", size%10);
	}
	
	
	session.setAttribute("size", 20);
	session.setAttribute("pList", products);
	return "productsList";
}

@RequestMapping("addProduct.html")
public void inserProduct(HttpServletRequest request){
	for(int i=0;i<150;i++){
		Product product = new Product();
		product.setpNameStr("product"+i+1);
		product.setpExpirDate(new Date(System.currentTimeMillis()));
		product.setpExpirDate(new Date(System.currentTimeMillis()+24*60*60*30));
		productDao.save(product);
	}
}
	
}
