package com.boot.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.library.Util.FileUtil;
import com.boot.library.domain.BUC;
import com.boot.library.domain.Book;
import com.boot.library.domain.BookOfCate;
import com.boot.library.domain.User;
import com.boot.library.service.*;
import com.boot.library.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author pcdn
* @description 针对表【book】的数据库操作Service实现
* @createDate 2022-08-15 20:10:28
*/
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book>
    implements BookService{

    @Autowired
    ElasticsearchService elasticsearchService;

    @Value("${bookfile.localPath}")
    String fileprefix;

    @Value("${bookfile.deletedPath}")
    String deletedPath;

    @Autowired
    BookService bookService;

    @Autowired
    BookOfCateService bookOfCateService;
    @Autowired
    UserService userService;

    @Autowired
    BookMapper mapper;

    @Autowired
    BookOfCollectionService bookOfCollectionService;

    @Override
    public List<Book> listBook(String keyword) {
        return elasticsearchService.listBook(keyword);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void addBook(MultipartFile uploadfile, Book book) throws IOException {
        String FilebookName=uploadfile.getOriginalFilename();
        String bUri=fileprefix + FilebookName;
        book.setUri(bUri);
        book.setHasBeenDeleted(0);
        Book isExists = mapper.selectBookIsExists(book);

        if(isExists!=null)
        {
            book.setBId(isExists.getBId());
            if (isExists.getHasBeenDeleted()==1) {
                mapper.updateOneBook(book);
            }
            else
            {
                throw new RuntimeException("图书已存在");
            }
        }
        else
        {
            if (!bookService.save(book)) {
                throw new RuntimeException("数据库添加失败");
            }
        }

        elasticsearchService.addBookOfES(book);
        File file = new File(bUri);
        FileOutputStream os = new FileOutputStream(file);
        os.write(uploadfile.getBytes());

    }


    @Override
    @Transactional(rollbackFor=Exception.class)
    public void deleteBookById(Book book) throws IOException {


        if (!bookService.removeById(book.getBId())) {
            throw new RuntimeException("database delete defeat");
        }
        String oldPath = book.getUri();
        File file = new File(oldPath);
        String newPath = deletedPath+file.getName();
        if (!FileUtil.copyFile(oldPath,newPath) || !file.delete()) {
            throw new RuntimeException("delete book defeat");
        }

        elasticsearchService.deleteBookOfES(book.getBId());

    }

    @Override
    public BUC getOneBUC(Integer bId) {
        BUC buc = new BUC();
        Book book = bookService.getById(bId);
        buc.setBook(book);
        buc.setUser(userService.getById(book.getUId()));
        buc.setCategoryList(bookOfCateService.getAllCateOfBook(bId));
        buc.setRecommendBookCollection(bookOfCollectionService.listByIncludeOneBook(bId));
        return  buc;
    }

    @Override
    public List<Book> getSomeOneUpload(User user) {
        Wrapper<Book> wrapper=new QueryWrapper<Book>().eq("u_id",user.getUId());
        return mapper.selectList(wrapper);
    }

    @Override
    public List<Book> getBookByCate(String cateId) {
        Wrapper<BookOfCate> wrapper=new QueryWrapper<BookOfCate>().eq("cate_id",cateId);
        List<BookOfCate> list = bookOfCateService.list(wrapper);
        List<Integer> collect = list.stream().map(BookOfCate::getBId).collect(Collectors.toList());
        if (collect.size()==0)
        {
            return new ArrayList<>();
        }
        List<Book> books = bookService.listByIds(collect);
        return books.stream().sorted((b1, b2) -> b2.getCountCollected() - b1.getCountCollected()).collect(Collectors.toList());
    }

    @Override
    public boolean addCountCollected(Integer bId,Integer n) {
        return mapper.updateCountCollectedByBId(bId, n) == 1;
    }

    @Override
    public List<Book> getBookByAuthor(String author) {
        Wrapper<Book> wrapper=new QueryWrapper<Book>().eq("author",author);
        List<Book> list = bookService.list(wrapper);
        return list.stream().sorted((b1, b2) -> b2.getCountCollected() - b1.getCountCollected()).collect(Collectors.toList());
    }


}




