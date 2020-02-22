package com.example.demo.service.impl;

import com.example.demo.dao.WarehouseDao;
import com.example.demo.model.WarehouseModel;
import com.example.demo.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    private WarehouseDao warehouseDao;

    @Override
    public List<WarehouseModel> list(Map<String, Object> map) {
        return warehouseDao.list(map);
    }

    public int count(Map<String, Object> map) {
        return warehouseDao.count(map);
    }

    @Override
    public int save(WarehouseModel warehouse) {
        warehouse.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        warehouse.setCreateTime(new Date());
        warehouse.setUpdateTime(new Date());
        return warehouseDao.save(warehouse);
    }

    @Override
    public int updateAmount(Map<String, Object> map) {
        map.put("updateTime", new Date());
        return warehouseDao.updateAmount(map);
    }

    @Override
    public int remove(String id) {
        return warehouseDao.remove(id);
    }

    @Override
    public WarehouseModel get(String id) {
        return warehouseDao.get(id);
    }

}
