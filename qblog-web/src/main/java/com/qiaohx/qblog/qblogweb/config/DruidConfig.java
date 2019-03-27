package com.qiaohx.qblog.qblogweb.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DruidConfig {
    @Bean
    public ServletRegistrationBean druidServlet() { // 主要实现WEB监控的配置处理
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*"); // 进行druid监控的配置处理操作
        servletRegistrationBean.addInitParameter("allow",""); // 可以访问druid的白名单
        servletRegistrationBean.addInitParameter("deny", ""); // 黑名单
        servletRegistrationBean.addInitParameter("loginUsername", "jiayong"); // 用户名
        servletRegistrationBean.addInitParameter("loginPassword", "12345678"); // 密码
        servletRegistrationBean.addInitParameter("resetEnable", "false"); // 是否可以重置数据源
        return servletRegistrationBean ;
    }
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean() ;

        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*"); // 所有请求进行监控处理
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.css,/druid/*");
        filterRegistrationBean.addInitParameter("slowSqlMillis", "1");
        filterRegistrationBean.addInitParameter("logSlowSql", "true");

        return filterRegistrationBean ;
    }

    @Bean
    public StatFilter statFilterBean() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        StatFilter s = new StatFilter();
        s.setSlowSqlMillis(1);
        s.setLogSlowSql(true);
        return s;
    }
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }
}
