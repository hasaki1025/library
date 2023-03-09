package com.boot.library.Util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CreateCache;
import com.boot.library.domain.LoginUser;
import com.boot.library.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Component
public class JWTUtil {


    private final String JWT_key = "wuhu";

    @Value("${JWT.defaultTTL}")
    private long defaultTTL;

    @Autowired
    JWTUtil jwtUtil;

    @CreateCache(area = "TokenCache", name = "TokenCache", expire = 2, timeUnit = TimeUnit.HOURS)//expire（过期时间）默认单位为秒
    private Cache<String, String> tokenCache;

    public String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    private JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = defaultTTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)              //唯一的ID
                .setSubject(subject)   // 主题  可以是JSON数据
                .setIssuer("wuhu")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);
    }

    public SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JWT_key);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }


    public String createJWT(String subject, Long TTL, String id) throws JsonProcessingException {
        JwtBuilder builder = jwtUtil.getJwtBuilder(subject, TTL, id);// 设置过期时间
        return builder.compact();
    }


    public Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)//传入私钥
                .parseClaimsJws(jwt)//传入JWT
                .getBody();
    }


    public Authentication getAuthFromToken(HttpServletRequest request) {
        String token = request.getHeader("Token");
        if (token == null || token.equals("")) {
            //没有token则向下执行之后该请求会被拦截并转至登录
            return null;
        }
        //解析Token
        try {
            Claims claims = jwtUtil.parseJWT(token);
            String tokenId = claims.getId();
            String userId = claims.getSubject();
            String loginUserAsJson = tokenCache.get("Token" + userId);
            if (loginUserAsJson == null) {
                throw new RuntimeException("找不到该token");
            }
            //将jwt中的json转为loginUser
            LoginUser user = JSON.parseObject(loginUserAsJson, LoginUser.class);
            if (!tokenId.equals(user.getTokenId())) {
                throw new RuntimeException("TokenId不匹配");
            }
            // 传入第三个参数将该请求设置为已认证
            UsernamePasswordAuthenticationToken authentication = new
                    UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            //将User对象放置在SecurityContextHolder中
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return authentication;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}