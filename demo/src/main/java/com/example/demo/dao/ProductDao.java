package com.example.demo.dao;

import com.example.demo.model.ProductModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 产品表
 */
@Mapper
public interface ProductDao {

	ProductModel get(String id);
	
	List<ProductModel> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ProductModel product);
	
	int update(ProductModel product);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
