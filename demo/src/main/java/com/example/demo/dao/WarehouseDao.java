package com.example.demo.dao;

import com.example.demo.model.WarehouseModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 仓库
 */
@Mapper
public interface WarehouseDao {
    List<WarehouseModel> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(WarehouseModel warehouse);

    int updateAmount(Map<String, Object> map);

    int remove(String id);

    WarehouseModel get(String id);

}
