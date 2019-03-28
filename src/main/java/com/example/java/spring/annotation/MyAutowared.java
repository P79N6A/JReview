package com.example.java.spring.annotation;

import com.sun.org.glassfish.gmbal.Description;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Description("MyAutowared")
public @interface MyAutowared {
    boolean required() default true;
}
