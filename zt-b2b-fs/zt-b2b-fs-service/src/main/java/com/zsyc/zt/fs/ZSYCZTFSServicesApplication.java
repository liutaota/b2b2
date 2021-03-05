package com.zsyc.zt.fs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by lcs on 2020/7/25.
 */
@SpringBootApplication(scanBasePackages = "com.zsyc")
@EnableScheduling
@EnableAsync
public class ZSYCZTFSServicesApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZSYCZTFSServicesApplication.class, args);
	}
}
