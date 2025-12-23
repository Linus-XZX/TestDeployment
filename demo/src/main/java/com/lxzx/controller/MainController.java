package com.lxzx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.lxzx.service.MainService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Controller
public class MainController extends BaseController {

    @Autowired
    MainService mainService;

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("response", mainService.testService("demo/src/main/resources/202506.zip"));
        return "index";
    }
}