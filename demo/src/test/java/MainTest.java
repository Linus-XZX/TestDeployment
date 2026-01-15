import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lxzx.MainApp;
import com.lxzx.service.MainService;
import com.lxzx.entity.MainEntity;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = MainApp.class)
@Slf4j
class MainTest {

    @Autowired
    private MainService mainService;

    @Test
    void test() {
        MainEntity testEntity = new MainEntity();

        Boolean[] theList = new Boolean[]{false, false};

        testEntity.setTest(false);
        testEntity.setTestArray(theList);
        testEntity.setTestList(Arrays.asList(theList));
        
        Long id = mainService.create(testEntity).getId();

        MainEntity retrievedEntity = mainService.queryById(id);

        assertEquals(false, retrievedEntity.getTestArray()[1]);
        assertEquals(false, retrievedEntity.getTestList().get(1));

        // Intentional no-op as there's nothing to test rn
        assertEquals(1, 2 - 1);
    }
}
