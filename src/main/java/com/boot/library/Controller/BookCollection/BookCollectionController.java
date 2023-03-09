package com.boot.library.Controller.BookCollection;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.library.Util.ResponseUtil;
import com.boot.library.domain.*;
import com.boot.library.service.BookOfCollectionService;
import com.boot.library.service.BookService;
import com.boot.library.service.BookcollectionService;
import com.boot.library.service.UserOfCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collection")
public class BookCollectionController {

    @Autowired
    BookcollectionService bookcollectionService;

    @Autowired
    UserOfCollectionService userOfCollectionService;



    @GetMapping("/getAllCollection")
    List<Bookcollection> getAll() {
        LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userOfCollectionService.getAllCollectionOfUser(user.getUser().getUId());
    }

    @PostMapping("/addCollection")
    ResponseEntity<String> addCollection(String cName)
    {
        LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String uId=user.getUser().getUId();
        if (bookcollectionService.addCollection(cName,uId)) {
            return new ResponseUtil<String>().addMessage("add collection successfully",HttpStatus.OK,null);
        }
        return new ResponseUtil<String>().addMessage("add collection fail",HttpStatus.BAD_REQUEST,null);
    }

    @GetMapping("/deleteCollection")
    ResponseEntity<String> deleteCollection(Integer cId)
    {
        LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (bookcollectionService.deleteCollection(cId,user.getUser())) {
            return new ResponseUtil<String>().addMessage("delete collection successfully",HttpStatus.OK,null);
        }
        return new ResponseUtil<String>().addMessage("delete collection fail",HttpStatus.BAD_REQUEST,null);
    }

    @PostMapping("/updateCollectionName")
    ResponseEntity<String> updateCollection(@RequestBody Bookcollection bookcollection)
    {
        LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String uId=user.getUser().getUId();
        Bookcollection bookcollection2 = bookcollectionService.getById(bookcollection.getCId());
        bookcollection2.setCName(bookcollection.getCName());
        if((bookcollection2.getUId().equals(uId) || user.getUser().getRoleId().equals(2)) )
        {
            if (bookcollectionService.updateCollectionName(bookcollection2)) {
                return new ResponseUtil<String>().addMessage("update collection successfully",HttpStatus.OK,null);
            }
        }
        return new ResponseUtil<String>().addMessage("update collection fail",HttpStatus.BAD_REQUEST,null);
    }


    @GetMapping("/getAllCollectionByDesc")
    List<Bookcollection> getAllCollectionByDesc()
    {
        Wrapper<Bookcollection> wrapper=new QueryWrapper<Bookcollection>().orderByDesc("n_of_collections");
        return  bookcollectionService.list(wrapper);
    }




}
