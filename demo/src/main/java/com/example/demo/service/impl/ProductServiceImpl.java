package com.example.demo.service.impl;

import com.example.demo.dao.ProductDao;
import com.example.demo.model.ProductModel;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	
	@Override
	public ProductModel get(String id){
		return productDao.get(id);
	}
	
	@Override
	public List<ProductModel> list(Map<String, Object> map){
		return productDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return productDao.count(map);
	}
	
	@Override
	public int save(ProductModel product){
		product.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		product.setUpdateTime(new Date());
		product.setCreateTime(new Date());
		return productDao.save(product);
	}
	
	@Override
	public int update(ProductModel product){
		product.setUpdateTime(new Date());
		return productDao.update(product);
	}
	
	@Override
	public int remove(String id){
		return productDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return productDao.batchRemove(ids);
	}
	
}
