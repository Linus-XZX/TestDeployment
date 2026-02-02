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
        JSONObject testJson = new JSONObject();
        testJson.set("11", new JSONArray("[{\"11\": \"22\"}, {\"33\": \"44\"}]"));
        testJson.set("111", new JSONArray("[{\"111\": \"222\"}, {\"333\": \"444\"}]"));

        MainEntity testMain = new MainEntity();
        testMain.setTest(testJson.toString());
        testMain.setTestWrapped(testJson.toString());
        testMain.setGroupId(1L);
        mainService.create(testMain);

        MainEntity testMain2 = new MainEntity();
        testMain2.setTest(testJson.toString());
        testMain2.setTestWrapped(testJson.toString());
        testMain2.setGroupId(1L);
        mainService.create(testMain2);

        // Intentional no-op as there's nothing to test rn
        assertEquals(1, 2 - 1);
    }
}
