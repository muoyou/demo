package com.example.demo.controller;

import com.example.demo.common.R;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 登陆
 */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String welcome() {
        return "login";
    }

    /**
     * 登陆验证
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/loginVerification")
    @ResponseBody
    public R loginVerification(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
        try {
            if (StringUtils.isBlank(username) && StringUtils.isBlank(password)) {
                return R.error("账户或密码错误");
            }
            //验证用户密码
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("username", username);
            map.put("password", password);
            UserModel user = userService.getUserInfoByUP(map);
            if (user == null) {
                return R.error("账户或密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("登陆失败");
        }
        return R.ok();
    }

    /**
     * 跳转产品界面
     * @return
     */
   /* @GetMapping("/product")
    public String product() {
        return "product/product";
    }*/
}
