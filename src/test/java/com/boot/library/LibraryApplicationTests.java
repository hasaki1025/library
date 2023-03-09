package com.boot.library;

import com.alibaba.fastjson.JSONObject;
import com.boot.library.Util.JWTUtil;
import com.boot.library.Util.RedisUtil;
import com.boot.library.domain.*;
import com.boot.library.mapper.*;
import com.boot.library.service.BookcollectionService;
import com.boot.library.service.CategoryService;
import com.boot.library.service.UserOfCollectionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@SpringBootTest
class LibraryApplicationTests {

    @Autowired
    JWTUtil jwtUtil;





    @Autowired
    UserMapper userMapper;




    @Test
    void testUserMapper() {
        Jedis jedis = new Jedis("43.138.191.71", 9370);
        jedis.auth("&8DQxjGakJ");
        System.out.println(jedis.ping());
    }

    @Autowired
    RoleMapper roleMapper;

    @Test
    void testRoleMapper() {
        System.out.println(roleMapper.selectRoleNameByRid(1));
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void testPWDencode()
    {
        System.out.println(passwordEncoder.encode("123"));
    }


    @Autowired
    BookMapper bookMapper;

    @Test
    void testSelectBookWithUser() throws JsonProcessingException {
        Book book = new Book();
        book.setBId(3);
        book.setUId("1559787748703899649");

    }


    @Test
    void getJsonBook() throws JsonProcessingException {
        Book book = new Book();
        book.setBId(6);
        book.setUId("1559787748703899649");
        System.out.println(new ObjectMapper().writeValueAsString(bookMapper.selectBookIsExists(book)));

    }

    @Autowired
    CommentMapper commentMapper;
    @Test
    void CommentGet() {
        commentMapper.selectCommentByLike(6).forEach(System.out::println);
    }

    @Autowired
    BookcollectionMapper bookcollectionMapper;

    @Test
    void testbookcollectionMapper() {
        Bookcollection bookcollection = new Bookcollection();
        bookcollection.setUId("1559787748703899649");
        bookcollection.setCName("MyBookCollection");
        System.out.println(bookcollectionMapper.insertOneCollection(bookcollection));
    }

    @Autowired
    BookOfCollectionMapper bookOfCollectionMapper;

    @Test
    void testBOC() {
        bookOfCollectionMapper.selectBookOfCollection(1).forEach(System.out::println);
    }
    @Autowired
    Jedis jedis;
    @Autowired
    CategoryService categoryService;


    @Autowired
    BookcollectionService bookcollectionService;

    @Autowired
    UserOfCollectionService userOfCollectionService;
    @Test
    void testInitUserCollection() {
        String uId="1565331917300056066";
        Bookcollection bookcollection = new Bookcollection();
        bookcollection.setUId(uId);
        bookcollection.setCName("MyBookCollection2");
        UserOfCollection userOfCollection = new UserOfCollection();

        boolean save1 = bookcollectionService.save(bookcollection);
        userOfCollection.setUId(uId);
        userOfCollection.setCId(bookcollection.getCId());
        boolean save2 = userOfCollectionService.save(userOfCollection);
        System.out.println(save1 && save2);
    }




    @Test
    void testPasswordEndode() {
        String password="123";
        String encode = passwordEncoder.encode(password);
        System.out.println(password);
        System.out.println(encode);
        System.out.println(passwordEncoder.matches(password,encode));

    }
}
