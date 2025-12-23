package com.lxzx.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import com.lxzx.entity.MainEntity;

@Slf4j
@Service
public class MainService extends BaseService<MainEntity, Long> {
    public String testService(String inFile) {
        try (ZipFile zipFile = new ZipFile(inFile)) {
            for (Enumeration<? extends ZipEntry> entries = zipFile.entries() ; entries.hasMoreElements() ;) {
                ZipEntry entry = entries.nextElement();
                log.info(entry.getName());
            }
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
            for (StackTraceElement elem : e.getStackTrace()) {
                log.error(elem.toString());
            }
        }
        return "";
    }
}
