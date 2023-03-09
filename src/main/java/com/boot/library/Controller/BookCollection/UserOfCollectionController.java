package com.boot.library.Controller.BookCollection;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.library.Util.ResponseUtil;
import com.boot.library.domain.Bookcollection;
import com.boot.library.domain.LoginUser;
import com.boot.library.domain.UserOfCollection;
import com.boot.library.service.BookcollectionService;
import com.boot.library.service.UserOfCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collection")
public class UserOfCollectionController {

    @Autowired
    UserOfCollectionService userOfCollectionService;

    @Autowired
    BookcollectionService bookcollectionService;


    @PostMapping("/addCollectionOfUser")
    ResponseEntity<String> addCollectionOfUser(Integer cId)
    {
        LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String uId = user.getUser().getUId();
        UserOfCollection userOfCollection = new UserOfCollection();
        userOfCollection.setUId(uId);
        userOfCollection.setCId(cId);
        return userOfCollectionService.save(userOfCollection) && bookcollectionService.addnOfCollection(cId) ?
                new ResponseUtil<String>().addMessage("addCollectionOfUser successfully", HttpStatus.OK,null) :
                new ResponseUtil<String>().addMessage("addCollectionOfUser fail", HttpStatus.BAD_REQUEST,null);
    }


    @GetMapping("/deleteCollectionOfUser")
    ResponseEntity<String> deleteCollectionOfUser(Integer cId)
    {
        LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String uId = user.getUser().getUId();
        Wrapper<UserOfCollection> wrapper=new QueryWrapper<UserOfCollection>().eq("c_id",cId).eq("u_id",uId);
        return userOfCollectionService.remove(wrapper) && bookcollectionService.deletenOfCollection(cId)?
                new ResponseUtil<String>().addMessage("deleteCollectionOfUser successfully", HttpStatus.OK,null) :
                new ResponseUtil<String>().addMessage("deleteCollectionOfUser fail", HttpStatus.BAD_REQUEST,null);
    }


}
