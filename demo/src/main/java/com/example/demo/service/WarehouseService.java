package com.example.demo.service;

import com.example.demo.model.WarehouseModel;

import java.util.List;
import java.util.Map;

/**
 * 仓库
 */
public interface WarehouseService {

    List<WarehouseModel> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(WarehouseModel warehouse);

    int updateAmount(Map<String, Object> map);

    int remove(String id);

    WarehouseModel get(String id);
}
