package com.boot.library.Config;

import com.boot.library.Filter.LoginFilter;
import com.boot.library.Handler.LibraryLogoutHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)//开启权限设置
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Autowired
    private LoginFilter loginFilter;

    @Autowired
    LibraryLogoutHandler logoutHandler;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity

                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()


                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeRequests()
                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // 允许对于网站静态资源的无授权访问
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/*.eot",
                        "/*.svg",
                        "/*.ttf",
                        "/*.ico",
                        "/*.jpg",
                        "/*.woff",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/**/*.jpg",
                        "/**/*.eot",
                        "/**/*.svg",
                        "/**/*.ttf",
                        "/**/*.woff"



                ).permitAll()
                // anonymous方法对于获取token的rest api要允许匿名访问，也只能匿名访问
                .antMatchers(HttpMethod.POST,"/download")
                .permitAll()
                .antMatchers(HttpMethod.GET,"/getbook")
                .permitAll()
                .antMatchers(HttpMethod.POST,"/login")
                .anonymous()
                .antMatchers(HttpMethod.POST,"/register")
                .anonymous()
                .antMatchers(HttpMethod.GET,"/mail/sendMail")
                .permitAll()
                .antMatchers(HttpMethod.GET,"/comment/get").permitAll()
                .antMatchers(HttpMethod.GET,"/collection/getBook").permitAll()
                .antMatchers(HttpMethod.GET,"/cate").permitAll()
                .antMatchers(HttpMethod.GET,"/search").permitAll()
                .antMatchers(HttpMethod.GET,"/collection/getAllCollectionByDesc").permitAll()
                .antMatchers(HttpMethod.POST,"/admin/register").permitAll()
                .antMatchers(HttpMethod.GET,"/user").permitAll()
                .antMatchers(HttpMethod.GET,"/getBookByAuthor").permitAll()
                .antMatchers(HttpMethod.GET,"/getBookByCate").permitAll()
                .antMatchers(HttpMethod.GET,"/changePassword").permitAll()


                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();

        // 禁用缓存
        httpSecurity.headers().cacheControl();

        //将登录认证过滤器添加在登录过滤器前
        httpSecurity.addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class);

        //配置登出处理器
        httpSecurity.logout()
                .logoutSuccessHandler(logoutHandler)
                .logoutUrl("/logout")
                .permitAll();
    }

}