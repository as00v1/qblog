package com.qiaohx.qblog.qblogweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.qiaohx.qblog")
@MapperScan("com.qiaohx.qblog.service.*.dao")
@EnableSwagger2// 开启swagger
@EnableTransactionManagement// 开启事务
public class QblogWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(QblogWebApplication.class, args);
    }

}
