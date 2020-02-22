package com.example.demo.controller;

import com.example.demo.common.PageUtils;
import com.example.demo.common.Query;
import com.example.demo.common.R;
import com.example.demo.model.WarehouseModel;
import com.example.demo.service.WarehouseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 仓库
 */
@Controller
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;

    @GetMapping()
    String Product() {
        return "/warehouse/warehouse";
    }

    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<WarehouseModel> productList = warehouseService.list(query);
        int total = warehouseService.count(query);
        PageUtils pageUtils = new PageUtils(productList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    String add() {
        return "/warehouse/add";
    }

    @GetMapping("/checkProduct")
    String checkProduct() {
        return "/warehouse/product";
    }

    /**
     * 入库
     */
    @ResponseBody
    @PostMapping("/save")
    public R save(WarehouseModel warehouse) {
        if (StringUtils.isNotBlank(warehouse.getProductId())) {
            //查看是否已有相同产品入库
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("productId", warehouse.getProductId());
            List<WarehouseModel> warehouseList = warehouseService.list(map);
            if (warehouseList.size() > 0) {//修改
                int amount = warehouseList.get(0).getAmount() + warehouse.getAmount();
                Map<String, Object> updateMap = new HashMap<String, Object>();
                updateMap.put("productId", warehouse.getProductId());
                updateMap.put("amount", amount);
                if (warehouseService.updateAmount(updateMap) > 0) {
                    return R.ok();
                }
            } else {//新增
                if (warehouseService.save(warehouse) > 0) {
                    return R.ok();
                }
            }
        }

        return R.error();
    }

    /**
     * 出库
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    String edit(@PathVariable("id") String id, Model model) {
        WarehouseModel warehouseModel = warehouseService.get(id);
        model.addAttribute("warehouse", warehouseModel);
        return "/warehouse/edit";
    }
    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    public R update(WarehouseModel warehouse) {
        WarehouseModel model = warehouseService.get(warehouse.getId());
        if((warehouse.getAmount() - model.getAmount())>0){//判读是否大于库存数量
            return R.error("库存数量不足");
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("productId",model.getProductId());
        map.put("amount",model.getAmount() - warehouse.getAmount());
        warehouseService.updateAmount(map);
        return R.ok();
    }
}
