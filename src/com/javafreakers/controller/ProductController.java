package com.javafreakers.controller;

import java.util.ArrayList;
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
	//offset is page number 
	String offset = (String)request.getParameter("offSet");
	// result is number of record displayed on each page
	int result = 10;
	// size is the total number of record present in DB
	int size ;
	List<Integer> pageList = new ArrayList<Integer>();
	List<Product> products ;
	/*in the beginning we set page number zero
*/	if(offset!=null){
		int offsetreal = Integer.parseInt(offset);
		offsetreal = offsetreal*10;
		 products = productDao.getProduct(result,offsetreal);
		 
	}else{
		products = productDao.getProduct(result,0);
		size = productDao.getSize();
		/*if total record are divisible by 10 then we set page list 
		 * size one less than total size to avoid empty last page i.e if total record are 1220 then page list
		 *  size will be 121 because here we are taking page list size from 0-121 which is 122 pages*/
		if((size%result) == 0){
		session.setAttribute("size", (size/10)-1);
		}else{
			session.setAttribute("size", size/10);
		}
	}
	System.out.println(session.getAttribute("size").toString());
	/*when user click on any page number then this part will be executed. 
	 * else part will be executed on load i.e first time on page*/
	if(offset!=null){
		int listsize = Integer.parseInt(session.getAttribute("size").toString());
		if(Integer.parseInt(offset) <6){
			if(listsize>=10){
				for(int i= 1; i<=9;i++){
					pageList.add(i);
				}
			}else{
				for(int i= 1; i<=listsize;i++){
					pageList.add(i);
				}
			}

		}else{
			if(listsize >= 10 && Integer.parseInt(offset)-5 >0){
				List<Integer> temp = new ArrayList<Integer>(); 
				for(int i=Integer.parseInt(offset);i>Integer.parseInt(offset)-5;i--){
					temp.add(i);
				}
				for(int i=temp.size()-1;i>=0;i--){
					pageList.add(temp.get(i));
				}
			}
			if(listsize >= 10 && Integer.parseInt(offset)+5<listsize){
				for(int i=Integer.parseInt(offset)+1;i<Integer.parseInt(offset)+5;i++){
					pageList.add(i);
				}
			}else if(listsize >= 10){
				for(int i=Integer.parseInt(offset)+1;i<listsize;i++){
					pageList.add(i);
				}
			}
		}
	}else{
		int listsize = Integer.parseInt(session.getAttribute("size").toString());
		if(listsize>=10){
			for(int i= 1; i<=10;i++){
				pageList.add(i);
			}
		}else{
			for(int i= 1; i<=listsize;i++){
				pageList.add(i);
			}
		}
	}
	session.setAttribute("pageList", pageList);
	session.setAttribute("productList", products);
	return "productsList";
}

@RequestMapping("addProduct.html")
public void inserProduct(HttpServletRequest request){
	// this is used to insert product in DB
	for(int i=0;i<150;i++){
		Product product = new Product();
		product.setpNameStr("product"+i+1);
		product.setpExpirDate(new Date(System.currentTimeMillis()));
		product.setpExpirDate(new Date(System.currentTimeMillis()+24*60*60*30));
		productDao.save(product);
	}
}
	
}
