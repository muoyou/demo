package com.example.demo.controller;

import com.example.demo.common.PageUtils;
import com.example.demo.common.Query;
import com.example.demo.common.R;
import com.example.demo.model.ProductModel;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 产品表
 */

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    String Product() {
        return "/product/product";
    }

    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<ProductModel> productList = productService.list(query);
        int total = productService.count(query);
        PageUtils pageUtils = new PageUtils(productList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    String add() {
        return "/product/add";
    }

    @GetMapping("/edit/{id}")
    String edit(@PathVariable("id") String id, Model model) {
        ProductModel product = productService.get(id);
        model.addAttribute("product", product);
        return "/product/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    public R save(ProductModel product) {
        if (productService.save(product) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    public R update(ProductModel product) {
        productService.update(product);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    public R remove(String id) {
        if (productService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("ids[]") String[] ids) {
        productService.batchRemove(ids);
        return R.ok();
    }

}
