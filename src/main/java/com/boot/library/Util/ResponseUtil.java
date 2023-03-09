package com.boot.library.Util;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseUtil<T> {


    public ResponseEntity<T> addMessage(String value,HttpStatus status,T body)
    {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json;charset=utf-8");
        if(value!=null)
        {
            httpHeaders.add("message",value);
        }
        return new ResponseEntity<T>(body,httpHeaders, status);
    }

    public ResponseEntity<T> addBatchMessage(List<String> value, HttpStatus status,T body)
    {
        HttpHeaders httpHeaders = new HttpHeaders();
        for (int i = 0; i < value.size(); i++) {
            httpHeaders.add("message"+i,value.get(i));
        }
        httpHeaders.add("Content-Type", "application/json;charset=utf-8");
        return new ResponseEntity<T>(body,httpHeaders, status);
    }
}
