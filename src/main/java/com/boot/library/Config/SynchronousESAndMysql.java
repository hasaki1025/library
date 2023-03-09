package com.boot.library.Config;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.library.domain.Book;
import com.boot.library.mapper.BookMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SynchronousESAndMysql implements CommandLineRunner {

    @Autowired
    BookMapper bookMapper;

    @Autowired
    RestHighLevelClient client;


    @Value("${Elasticsearch.BookIndexName}")
    String indexName;

    @Override
    public void run(String... args) throws Exception {
        QueryBuilder query= QueryBuilders.matchAllQuery();
        DeleteByQueryRequest request=new DeleteByQueryRequest(indexName).setQuery(query);
        BulkByScrollResponse response = client.deleteByQuery(request, RequestOptions.DEFAULT);

        BulkRequest addrequest = new BulkRequest();
        Wrapper<Book> wrapper=new QueryWrapper<>();
        List<Book> book = bookMapper.selectList(wrapper);
        for (Book book1 : book) {
            addrequest.add(new IndexRequest().index(indexName).id(String.valueOf(book1.getBId())).source(new ObjectMapper().writeValueAsString(book1), XContentType.JSON));
        }

        client.bulk(addrequest, RequestOptions.DEFAULT);
    }
}

