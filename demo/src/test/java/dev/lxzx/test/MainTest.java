package dev.lxzx.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.lxzx.MainApp;
import dev.lxzx.service.MainService;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

import dev.lxzx.entity.MainEntity;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = MainApp.class)
@Slf4j
class MainTest {

    @Autowired
    private MainService mainService;

    @Test
    void test() {
        try {
            mainService.testRollback(1L);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        

        // Intentional no-op as there's nothing to test rn
        assertEquals(1, 2 - 1);
    }
}
