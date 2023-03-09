package com.boot.library.Controller.Category;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.library.Util.ResponseUtil;
import com.boot.library.domain.BookOfCate;
import com.boot.library.service.BookOfCateService;
import org.elasticsearch.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

@RestController
@RequestMapping("/cate")
public class BookOfCategoryController {

    @Autowired
    BookOfCateService bookOfCateService;
    @PostMapping("/addCateToBook")
   public ResponseEntity<String> addCateToBook(@RequestBody List<BookOfCate> bookOfCate)
    {
        try{
            if (bookOfCateService.saveBatch(bookOfCate)) {
                return new ResponseUtil<String>().addMessage("add Book category successfully", HttpStatus.OK,null);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseUtil<String>().addMessage("add Book category fail", HttpStatus.BAD_REQUEST,null);
    }
    @PostMapping("/deleteCateOfBook")
    public ResponseEntity<String> deleteCateOfBook(@RequestBody  BookOfCate bookOfCate)
    {
        Wrapper<BookOfCate> wrapper=
                new QueryWrapper<BookOfCate>()
                        .eq("cate_id",bookOfCate.getCateId())
                        .eq("b_id",bookOfCate.getBId());
        if (bookOfCateService.remove(wrapper)) {
            return new ResponseUtil<String>().addMessage("delete Book category successfully", HttpStatus.OK,null);
        }
        return new ResponseUtil<String>().addMessage("delete Book category fail", HttpStatus.BAD_REQUEST,null);
    }



}
