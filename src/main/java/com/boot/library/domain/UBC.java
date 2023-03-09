package com.boot.library.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UBC {
    @JsonProperty("user")
    User user;
    @JsonProperty("bookList")
    List<Book> bookList;
    @JsonProperty("bookcollectionList")
    List<Bookcollection> bookcollectionList;
}
