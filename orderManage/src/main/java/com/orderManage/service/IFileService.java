package com.orderManage.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Create by LZ
 */
public interface IFileService {

    String upload(MultipartFile file, String path);
}
