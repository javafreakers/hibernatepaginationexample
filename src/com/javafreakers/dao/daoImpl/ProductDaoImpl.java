package com.javafreakers.dao.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.javafreakers.dao.ProductDao;
import com.javafreakers.model.Product;
@Component
public class ProductDaoImpl implements ProductDao{
	@Autowired
	SessionFactory sessionFactory;
	@Override
	@Transactional
	public List<Product> getProduct(int result, int offsetreal) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Product.class);
		criteria.setFirstResult(offsetreal);
		criteria.setMaxResults(result);
		List<Product>  products = (List<Product>)criteria.list();
		return products;
	}
	@Override
	@Transactional
	public void save(Product product) {
		sessionFactory.getCurrentSession().save(product);
	}
	@Override
	@Transactional
	public int getSize() {
		return sessionFactory.getCurrentSession().createCriteria(Product.class).list().size();
	}

}
