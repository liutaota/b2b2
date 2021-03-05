package com.zsyc.zt.b2b.api.controller.admin;


import cn.hutool.http.HttpUtil;
import com.zsyc.zt.fs.service.MinioFileService;
import io.minio.errors.*;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import cn.hutool.core.io.resource.InputStreamResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/app")
@Slf4j
public class AdminAppController {

    private String appUploadPath = "http://fastdfs.gdztyy.com/group1/upload";

    @Autowired
    private MinioFileService minioFileService;

    @ApiOperation(value = "上传app版本文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "应用文件", required = true, dataType = "MultipartFile")
    })
    @PostMapping("uploadApplicationFile1")
    public String upload(@RequestBody MultipartFile file) {
        String result = "";
        try {
            InputStreamResource isr = new InputStreamResource(file.getInputStream(),
                    file.getOriginalFilename());

            Map<String, Object> params = new HashMap<>();
            params.put("file", isr);
            params.put("output", "json2");
            String resp = HttpUtil.post(appUploadPath, params);

            result = resp;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * 上件
     *
     * @param file
     * @return
     */
    @RequestMapping("uploadFile")
    @ResponseBody
    public Object uploadMinio(MultipartFile file) throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, InsufficientDataException, ErrorResponseException {

        Map<String, String> map = new HashMap<>();
        String code = null;
        try {
            code = this.minioFileService.write(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (StringUtils.isEmpty(code)) {
            return map;
        }
        map.put("code", code);
        map.put("id", code);
        return map;
    }

}
