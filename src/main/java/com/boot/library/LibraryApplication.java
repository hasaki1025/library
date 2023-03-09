package com.boot.library;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableCreateCacheAnnotation//开启缓存的注解
@EnableMethodCache(basePackages = "com.boot.library.service")//开启Jetcache方法缓存
@MapperScan(basePackages = "com.boot.library.mapper")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class LibraryApplication {



    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

}
