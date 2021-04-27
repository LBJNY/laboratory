package com.zj.laboratory.controller;


import com.zj.laboratory.oss.OssService;
import com.zj.laboratory.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private OssService ossService;

    /**
     * 文件上传
     *
     * @param file
     * @param dir
     * @return
     */
    @PostMapping(value = "/uploadFile")
    public Result<String> uploadFile(MultipartFile file, String dir) {
        String upload = ossService.upload(file, dir);
        return new Result<>("上传成功", upload);
    }

}
