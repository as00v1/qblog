package com.qiaohx.qblog.qblogweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.qiaohx.qblog")
@MapperScan("com.qiaohx.qblog.service.user.dao")
@EnableSwagger2
public class QblogWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(QblogWebApplication.class, args);
    }

}
