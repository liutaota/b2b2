package com.zsyc.zt.fs.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lcs on 2020/7/25.
 */
@Configuration
public class OssConfig {
    @Autowired
    private AliyunOssProperties aliyunOssProperties;

    @Autowired
    private MinioOssProperties minioOssProperties;

    @Bean
    public OSS initOSSClient() {
        return new OSSClientBuilder().build(
                this.aliyunOssProperties.getEndpoint(),
                this.aliyunOssProperties.getAccessKeyId(),
                this.aliyunOssProperties.getAccessKeySecret());
    }


    @Bean
    public MinioClient minioOssClient() {
        return new MinioClient.Builder().
                endpoint(minioOssProperties.getEndpoint()).
                credentials(minioOssProperties.getAccessKeyId(), minioOssProperties.getAccessKeySecret()).
                build();
    }
}
