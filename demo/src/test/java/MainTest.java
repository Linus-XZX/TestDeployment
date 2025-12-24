import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lxzx.MainApp;
// import com.lxzx.service.MainService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = MainApp.class)
@Slf4j
class MainTest {

    // @Autowired
    // private MainService mainService;

    @Test
    void test() {
        // Intentional no-op as there's nothing to test rn
        assertEquals(1, 2 - 1);
    }
}
