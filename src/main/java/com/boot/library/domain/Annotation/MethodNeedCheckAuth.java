package com.boot.library.domain.Annotation;


import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodNeedCheckAuth {


}
