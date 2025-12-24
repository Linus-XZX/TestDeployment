package com.lxzx.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import com.lxzx.entity.MainEntity;

@Slf4j
@Service
public class MainService extends BaseService<MainEntity, Long> {
    public String testService(String inFile) {
        // UTF8 is usually more prevalent but many compressors on Windows seems to use current code page, which is GB2312 in our case :sadge:
        final Charset fallback = Charset.forName("GB2312");
        try (ZipFile zipFile = new ZipFile(inFile)) {
            for (Enumeration<? extends ZipEntry> entries = zipFile.entries() ; entries.hasMoreElements() ;) {
                ZipEntry entry = entries.nextElement();
                log.info(entry.getName());
            }
        } catch (ZipException e) {
            try (ZipFile zipFile = new ZipFile(inFile, fallback)) {
                for (Enumeration<? extends ZipEntry> entries = zipFile.entries() ; entries.hasMoreElements() ;) {
                    ZipEntry entry = entries.nextElement();
                    log.info(entry.getName());
                }
            } catch (IOException ie) {
                log.error(ie.getLocalizedMessage());
            }
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
        }
        return "";
    }
}
