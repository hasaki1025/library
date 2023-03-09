package com.boot.library.Controller.Book;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.library.Util.FileUtil;
import com.boot.library.Util.MailUtil;
import com.boot.library.Util.ResponseUtil;
import com.boot.library.Util.UserUtil;
import com.boot.library.domain.BUC;
import com.boot.library.domain.Book;
import com.boot.library.domain.LoginUser;
import com.boot.library.domain.User;
import com.boot.library.mapper.BookMapper;
import com.boot.library.service.BookService;
import com.boot.library.service.ElasticsearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
public class BookController {


    @Autowired
    BookService bookService;

    @Autowired
    BookMapper bookMapper;

    @Autowired
    UserUtil userUtil;

    @Autowired
    ElasticsearchService elasticsearchService;

    @Autowired
    ResponseUtil<String> responseUtil;

    @Autowired
    MailUtil mailUtil;

    @Value("${bookfile.localPath}")
    String localPath;


    private final RestTemplate restTemplate=new RestTemplate();



    @GetMapping("/search")//关键字搜索图书
    List<Book> getListBook(String keyword)
    {
        return bookService.listBook(keyword);
    }

    @GetMapping("/getbook")//获取一本图书的详细信息
    BUC getBuc(Integer bId)
    {
        return bookService.getOneBUC(bId);
    }

    @PostMapping("/upload")//上传
    ResponseEntity<String> upload(MultipartFile uploadfile, @Valid Book book)
    {

        try {
            LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            book.setUId(user.getUser().getUId());
            book.setUNickName(user.getUser().getNickName());
            bookService.addBook(uploadfile,book);
            return responseUtil.addMessage("create successfully", HttpStatus.OK,null);
        } catch (IOException e) {
            e.printStackTrace();
            return responseUtil.addMessage("create book defeat",HttpStatus.BAD_REQUEST,null);
        }
    }

    @GetMapping("/download")//下载
    ResponseEntity<byte[]> download(String uri) {

        return null;
    }



    @PostMapping("/deleteBook")
    ResponseEntity<String> deleteBook(@RequestBody Book book)
    {
        ResponseUtil<String> response = new ResponseUtil<>();
        book=bookMapper.selectById(book.getBId());
        LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user.getUser().getUId().equals(book.getUId()) || user.getUser().getRoleId().equals(2))
        {
            try {
                bookService.deleteBookById(book);
                return responseUtil.addMessage("delete successfully",HttpStatus.OK,null);
            }catch(Exception e){
                e.printStackTrace();
                return responseUtil.addMessage("delete fail",HttpStatus.BAD_REQUEST,null);
            }
        }
        else
        {
            return responseUtil.addMessage("You can only delete books uploaded by yourself",HttpStatus.UNAUTHORIZED,null);
        }

    }


    @GetMapping("/MyUpload")
    List<Book> MyUpload()
    {
        User nowUser = userUtil.getNowUser();
        return bookService.getSomeOneUpload(nowUser);
    }


    @GetMapping("/getBookByAuthor")
    List<Book> getBookByAuthor(String author)
    {
        return bookService.getBookByAuthor(author);
    }

    @GetMapping("/getBookByCate")
    List<Book> getBookByCate(String cateId)
    {
        return bookService.getBookByCate(cateId);
    }




}
