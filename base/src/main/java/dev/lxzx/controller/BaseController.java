package dev.lxzx.controller;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class BaseController {
    public String redirect(String url) {
        return "redirect:" + url;
    }
}
