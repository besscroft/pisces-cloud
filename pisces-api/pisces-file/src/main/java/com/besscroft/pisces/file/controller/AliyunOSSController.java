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
 * @Date 2022/7/28 15:27
 */
@Slf4j
@Tag(name = "阿里云 OSS 接口")
@RestController
@RequestMapping("/aliyun/oss")
@RequiredArgsConstructor
public class AliyunOSSController {

    private final StorageService aliyunStorageService;

    @PostMapping("/upload")
    @Operation(summary = "文件上传")
    public AjaxResult uploadFile(@RequestPart MultipartFile file) throws Exception {
        if (ObjectUtils.allNull(file)) {
            throw new RuntimeException("未上传文件！");
        }
        String filename = file.getOriginalFilename();
        String url = aliyunStorageService.putObject(null, filename, file.getInputStream(), "application/octet-stream");
        return AjaxResult.success("", url);
    }

    @PostMapping("/uploadCdn")
    @Operation(summary = "文件上传")
    public AjaxResult uploadCdn(@RequestPart MultipartFile file) throws Exception {
        if (ObjectUtils.allNull(file)) {
            throw new RuntimeException("未上传文件！");
        }
        String filename = file.getOriginalFilename();
        String url = aliyunStorageService.putObjectCdn(null, filename, file.getInputStream(), "application/octet-stream");
        return AjaxResult.success("", url);
    }

}
