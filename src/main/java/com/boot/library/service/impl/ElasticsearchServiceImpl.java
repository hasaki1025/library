package com.boot.library.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.boot.library.domain.Book;
import com.boot.library.mapper.BookMapper;
import com.boot.library.service.ElasticsearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ElasticsearchServiceImpl implements ElasticsearchService {

    @Autowired
    RestHighLevelClient client;

    @Value("${Elasticsearch.BookIndexName}")
    String indexName;

    @Autowired
    BookMapper bookMapper;

    @Override
    public List<Book> listBook(String keyword) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.should(QueryBuilders.matchQuery("bName",keyword)).
                should(QueryBuilders.matchQuery("author",keyword)).
                should(QueryBuilders.matchQuery("description",keyword)).
                should(QueryBuilders.matchQuery("uNickName",keyword)).
                should(QueryBuilders.fuzzyQuery("ISBN",keyword).fuzziness(Fuzziness.AUTO));

        SearchSourceBuilder sourceBuilder=new SearchSourceBuilder().query(boolQueryBuilder);
        SearchRequest request=new SearchRequest(indexName).source(sourceBuilder);
        ArrayList<Book> list = new ArrayList<>();
        try {
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            for (SearchHit hit : response.getHits()) {
                list.add(JSON.parseObject(hit.getSourceAsString(), Book.class));
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void deleteBookOfES(Integer bId) throws IOException {
        DeleteRequest request=new DeleteRequest(indexName).id(String.valueOf(bId));
        client.delete(request,RequestOptions.DEFAULT);
    }

    @Override
    public void updateBookOfES(Book book) throws Exception {
        String id= String.valueOf(book.getBId());
        IndexRequest request = new IndexRequest(indexName).
                id(id).
                source(new ObjectMapper().writeValueAsString(book), XContentType.JSON);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
    }
    @Override
    public void addBookOfES(Book book) throws IOException {
        IndexRequest request=new IndexRequest(indexName).id(String.valueOf(book.getBId()))
                .source(new ObjectMapper().writeValueAsString(book),XContentType.JSON);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
    }


}
