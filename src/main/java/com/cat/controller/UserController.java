package com.cat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.cat.common.ArmyResult;
import com.cat.model.User;
import com.cat.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @RequestMapping("/getUser")
    public String getUser() {
        User userDTO = new User();
        userDTO.setUsername("1");
        userDTO.setPassword("2222");
        User user = userService.getUser(userDTO);
        return JSON.toJSONString(user);
    }
    
    @RequestMapping("/getUser2")
    public ArmyResult getUserCs() {
        return ArmyResult.ok("JUSTSY.E.DEVICE.MSG.UPDATE.FAIL");
    }
}
