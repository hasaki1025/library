package com.boot.library.domain.LibraryException;

public class AuthorityException extends RuntimeException{

    public AuthorityException(String message) {
        super("权限不足");
    }
}
