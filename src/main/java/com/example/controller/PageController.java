package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 刘上忠
 * @data studying
 */
@Controller
public class PageController {
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(){
        return "register";
    }
}
