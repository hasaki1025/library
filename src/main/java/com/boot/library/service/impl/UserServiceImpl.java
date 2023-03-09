package com.boot.library.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CreateCache;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.library.Util.JWTUtil;
import com.boot.library.Util.MailUtil;
import com.boot.library.Util.ResponseUtil;
import com.boot.library.domain.*;
import com.boot.library.mapper.BookcollectionMapper;
import com.boot.library.service.BookService;
import com.boot.library.service.BookcollectionService;
import com.boot.library.service.UserOfCollectionService;
import com.boot.library.service.UserService;
import com.boot.library.mapper.UserMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
* @author pcdn
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-08-15 20:10:28
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    UserMapper userMapper;

    @Autowired
    MailUtil mailUtil;

    @Autowired
    UserService userService;

    @Value("${JWT.defaultTTL}")
    Long defaultTTL;
    @Autowired
    BookcollectionService bookcollectionService;

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ResponseUtil<String> responseUtil;

    @Autowired
    BookService bookService;
    @Autowired
    UserOfCollectionService userOfCollectionService;


    @CreateCache(area="TokenCache",name="TokenCache",expire = 2,timeUnit = TimeUnit.HOURS)//expire（过期时间）默认单位为秒
    private Cache<String,String> tokenCache;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean Register(User user) {

        QueryWrapper<User> emailQuery = new QueryWrapper<User>()
                .eq("email", user.getEmail())
                .or().eq("nick_name",user.getNickName());
        if (userService.getOne(emailQuery)==null) { //判断是否中注册过
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userMapper.addUser(user)==1 && bookcollectionService.initUserCollection(user.getUId());
        }
        else
        {
            return false;
        }

    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.selectByEmailUser(email);
    }

    @Override
    public ResponseEntity<String> login(User user,Long TTL) {


        try{
            //传入认证对象
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
            Authentication authenticate =null;
            try {
                //开始认证
                authenticate=authenticationManager.authenticate(authenticationToken);
                //判断是否认证成功
                if(authenticate==null)
                {
                    throw new Exception("login fail");
                }
            }catch (Exception e)
            {
                e.printStackTrace();
                return responseUtil.addMessage("User password or email incorrect",HttpStatus.BAD_REQUEST,null);
            }
            //获取用户对象,这里authenticate的Principal是我们在UserDetailsService中返回的对象
            LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
            //随机得到一个JWTID
            String TokenId=jwtUtil.getUUID();
            //在loginUser中放置jwtId
            loginUser.setTokenId(TokenId);
            TTL =TTL==null? TTL :defaultTTL ;
            //创建JWT
            String jwt = jwtUtil.createJWT(String.valueOf(loginUser.getUser().getUId()),TTL,TokenId);
            //将Token放置在缓存中
            tokenCache.put("Token"+loginUser.getUser().getUId(),new ObjectMapper().writeValueAsString(loginUser));
            //返回响应，其中包含jwt
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Message","Login successful");

            return new ResponseEntity<>(jwt,httpHeaders, HttpStatus.OK);
        }catch (Exception e)
        {
            return responseUtil.addMessage("login fail",HttpStatus.BAD_REQUEST,null);
        }



    }

    @Override
    public boolean logout(Authentication authentication) {
        try {
            //获取到SecurityContextHolder中的LoginUser信息
            LoginUser user = (LoginUser) authentication.getPrincipal();
            //删除Redis中的User信息
            String uId=user.getUser().getUId();
            tokenCache.remove("Token"+uId);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UBC getOneUBCByuId(String uId) {
        UBC ubc = new UBC();
        User user = userService.getById(uId);
        ubc.setUser(user);
        List<Book> someOneUpload = bookService.getSomeOneUpload(user);
        ubc.setBookList(someOneUpload);
        List<Bookcollection> allCollectionOfUser = userOfCollectionService.getAllCollectionOfUser(uId);
        ubc.setBookcollectionList(allCollectionOfUser);
        return ubc;
    }


}




