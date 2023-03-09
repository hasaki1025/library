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


}
