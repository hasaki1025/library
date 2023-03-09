package com.boot.library.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserLikeWithCancel {
    @JsonProperty("userLike")
    public UserLike userLike;
    @JsonProperty("isCancel")
    public boolean isCancel;

}
