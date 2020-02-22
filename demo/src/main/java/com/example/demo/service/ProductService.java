package com.example.demo.service;

import com.example.demo.model.ProductModel;

import java.util.List;
import java.util.Map;

/**
 * 产品表
 * 
 */
public interface ProductService {
	
	ProductModel get(String id);
	
	List<ProductModel> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ProductModel product);
	
	int update(ProductModel product);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
