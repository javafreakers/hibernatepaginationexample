package com.javafreakers.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import com.javafreakers.model.Product;
@Component
public interface ProductDao {
	List<Product> getProduct(int result, int offsetreal) ;;
	void save(Product product) ;
	int getSize();

}
