package dev.lxzx.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import dev.lxzx.entity.MainEntity;
import dev.lxzx.service.MainService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;


@Slf4j
@Component
@Controller
public class MainController extends BaseController {

    @Autowired
    MainService mainService;

    @PostMapping("/testFile")
    public String testFile(Model model, @RequestParam(value = "files", required = false) MultipartFile[] files) throws IOException {
        model.addAttribute("response", mainService.testFile(files));
        return "index";
    }

    @GetMapping("/get")
    public String getItem(Model model, @RequestParam(value = "id", required = true) Long id) {
        model.addAttribute("item", mainService.queryById(id));
        return "entity";
    }

    @GetMapping("/getAllTests")
    public String getAllTests(Model model, @RequestParam(value = "group", required = true) Long groupId) {
        model.addAttribute("response", mainService.testMerge(groupId));
        return "index";
    }
    

    @PostMapping("/update")
    public String updateItem(Model model, @RequestBody MainEntity item) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        mainService.update(item);
        return "index";
    }

    @PutMapping("/create")
    public String createItem(Model model, @RequestBody MainEntity item) {
        mainService.create(item);
        return "index";
    }
}