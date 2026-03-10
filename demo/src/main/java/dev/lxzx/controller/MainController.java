package dev.lxzx.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dev.lxzx.entity.MainEntity;
import dev.lxzx.entity.ResponseItem;
import dev.lxzx.service.MainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;

@Slf4j
@Component
@RestController
public class MainController extends BaseController {

    @Autowired
    MainService mainService;

    @PostMapping("/uploadAndDecompress")
    public ResponseItem<String> uploadAndDecompress(@RequestParam(value = "files") MultipartFile[] files) throws IOException {
        return ResponseItem.success(mainService.uploadAndDecompress(files));
    }

    @GetMapping("/get")
    public ResponseItem<MainEntity> getItem(@RequestParam(value = "id", required = true) Long id) {
        return ResponseItem.success(mainService.queryById(id));
    }

    @GetMapping("/getAllTests")
    public ResponseItem<String> getAllTests(@RequestParam(value = "group", required = true) Long groupId) {
        return ResponseItem.success(mainService.testMerge(groupId));
    }
    

    @PostMapping("/update")
    public ResponseItem<MainEntity> updateItem(@RequestBody MainEntity item) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return ResponseItem.success(mainService.update(item));
    }

    @PutMapping("/create")
    public ResponseItem<MainEntity> createItem(@RequestBody MainEntity item) {
        return ResponseItem.success(mainService.create(item));
    }
}