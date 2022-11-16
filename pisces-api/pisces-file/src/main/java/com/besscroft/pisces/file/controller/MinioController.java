package com.besscroft.pisces.file.controller;

import com.besscroft.pisces.file.service.StorageService;
import com.besscroft.pisces.framework.common.result.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/11/16 14:32
 */
@Slf4j
@Tag(name = "Minio OSS 接口")
@RestController
@RequestMapping("/minio/oss")
@RequiredArgsConstructor
public class MinioController {

    private final StorageService minioStorageService;

    @PostMapping("/upload")
    @Operation(summary = "文件上传")
    public AjaxResult uploadFile(@RequestPart MultipartFile file) throws Exception {
        if (ObjectUtils.allNull(file)) {
            throw new RuntimeException("未上传文件！");
        }
        String filename = file.getOriginalFilename();
        String url = minioStorageService.putObject(null, filename, file.getInputStream(), "application/octet-stream");
        return AjaxResult.success("上传成功！", url);
    }

    @PostMapping("/uploadCdn")
    @Operation(summary = "文件上传(CDN)")
    public AjaxResult uploadCdn(@RequestPart MultipartFile file) throws Exception {
        if (ObjectUtils.allNull(file)) {
            throw new RuntimeException("未上传文件！");
        }
        String filename = file.getOriginalFilename();
        String url = minioStorageService.putObjectCdn(null, filename, file.getInputStream(), "application/octet-stream");
        return AjaxResult.success("上传成功！", url);
    }

}
