package dev.lxzx.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

import dev.lxzx.dao.MainDao;
import dev.lxzx.entity.MainEntity;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.extra.compress.CompressUtil;
import cn.hutool.extra.compress.extractor.Extractor;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;

@Slf4j
@Service
public class MainService extends BaseService<MainEntity, Long> {

    @Value("${system.filePath.uploads}")
    private String uploadPath;
    @Value("${system.filePath.base}")
    private String basePath;

    @Autowired
    private MainDao mainDao;

    // This stays in MainService since MultipartFile is part of Spring
    private String saveFile(MultipartFile file, String savePath, String[] checkFilesuffixName) throws IOException {
        String fileName = file.getOriginalFilename();
        if (CharSequenceUtil.isEmpty(fileName)) {
            throw new IOException("No name in upload");
        }
        fileName = fileName.replace(";","");
        fileName = fileName.replace(" ","");
        fileName = fileName.replace("\\)","_");
        fileName = fileName.replace("\\(","_");
        fileName = fileName.replace("）","_");
        fileName = fileName.replace("（","_");
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        fileName = fileName.substring(0,fileName.lastIndexOf("."));
        if (Objects.nonNull(checkFilesuffixName) && !Arrays.asList(checkFilesuffixName).contains(suffixName)){
            throw new IOException(Arrays.toString(checkFilesuffixName) + " extension expected");
        }
        recurseDir(savePath);
        String filePath = savePath + fileName + suffixName;
        file.transferTo(new File(filePath));
        log.info("save file success:" + filePath);
        return filePath;
    }

    public String testService(String inFile) {
        // No-op here as it's now covered in the file-capable service
        return "";
    }

    public String testFile(MultipartFile[] files) throws IOException {
        for (MultipartFile file : files) {
            boolean hasBaseFolder = false;
            String yearMonth = file.getOriginalFilename().replace(".zip", "");
            saveFile(file, basePath + uploadPath, new String[]{".zip"});
            String savePath = basePath + uploadPath + file.getOriginalFilename();
            ZipFile zipFile = null;
            try {
                zipFile = new ZipFile(new File(savePath));
            } catch (ZipException e) {
                // UTF8 is usually more prevalent but many compressors on Windows seems to use
                // current code page, which is GB2312 in our case :sadge:
                log.info("Falling back to GB2312");
                zipFile = new ZipFile(new File(savePath), Charset.forName("GB2312"));
            } catch (IOException e) {
                log.error(e.getLocalizedMessage());
            } finally {
                hasBaseFolder = (zipFile.entries().nextElement().getName().replace("/", ""))
                        .equals(yearMonth);
                for (Enumeration<? extends ZipEntry> entries = zipFile.entries(); entries.hasMoreElements();) {
                    log.info(entries.nextElement().getName());
                }
                zipFile.close();
            }

            // Extractor.extract() closes the stream automatically
            Extractor extractor = CompressUtil.createExtractor(Charset.defaultCharset(), new File(savePath));
            extractor.extract(new File(basePath + uploadPath + (hasBaseFolder ? "" : yearMonth)));
        }
        return "";
    }

    public String testMerge(Long groupId) {
        JSONArray ret = new JSONArray();
        mainDao.findAllByGroupId(groupId).forEach(item -> {
            JSONArray entries = JSONUtil.parseObj(item.getTest()).getJSONArray("111");
            for (Object entry : entries) {
                ret.put(entry);
            }
        });
        return ret.toString();
    }
}
