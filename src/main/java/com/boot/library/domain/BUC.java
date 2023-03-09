package com.boot.library.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BUC {
    @JsonProperty("book")
    Book book;

    @JsonProperty("categoryList")
    List<Category> categoryList;

    @JsonProperty("user")
    User user;
    @JsonProperty("recommendBookCollection")
    List<Bookcollection> recommendBookCollection;
}
