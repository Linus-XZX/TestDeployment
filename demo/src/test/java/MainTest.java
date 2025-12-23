import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lxzx.MainApp;
import com.lxzx.service.MainService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = MainApp.class)
@Slf4j
class MainTest {

    @Autowired
    private MainService mainService;

    @Test
    void test() {
        try {
            mainService.testService("demo/src/main/resources/202506.zip");
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        try (ZipFile zipFile = new ZipFile("src/main/resources/202506.zip")) {
            for (Enumeration<? extends ZipEntry> entries = zipFile.entries() ; entries.hasMoreElements() ;) {
                ZipEntry entry = entries.nextElement();
                log.info(entry.getName());
            }
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
        }
        // try (Extractor extractor = CompressUtil.createExtractor(CharsetUtil.defaultCharset(), new File("src/main/resources/Downloads.zip"))) {
        //     extractor.extract(new File("src/main/resources"));
        // }
        // assertEquals(true, FileUtil.exist(System.getProperty("user.dir") + "src/main/resources/LGJI.json"));
    }
}
