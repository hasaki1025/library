package com.boot.library.Controller.Category;

import com.alibaba.fastjson.JSONObject;
import com.alicp.jetcache.anno.Cached;
import com.alicp.jetcache.anno.CreateCache;
import com.boot.library.Util.ResponseUtil;
import com.boot.library.domain.BookOfCate;
import com.boot.library.domain.Category;
import com.boot.library.service.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cate")
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    @Autowired
    Jedis jedis;

    @GetMapping
    List<Category> getCate() throws JsonProcessingException {
        String cate = jedis.get("Cate");
        if(cate==null) {
            List<Category> collect = categoryService.list();
            String s = new ObjectMapper().writeValueAsString(collect);
            jedis.set("Cate",s);
            return collect;
        }
        else {
            return JSONObject.parseArray(cate, Category.class);
        }
    }




}
