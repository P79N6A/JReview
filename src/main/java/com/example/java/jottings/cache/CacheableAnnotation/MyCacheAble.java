package com.example.java.jottings.cache.CacheableAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @描述
 * @参数 $params$
 * @返回值 $return$
 * @创建人 czb
 * @创建时间 $date$
 * @修改人和其它信息
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyCacheAble {
    /**key**/
    public String key();
    /**是否刷新失效时间**/
    public boolean refreshTTL() default false;
    /**缓存失效时间*/
    public CacheExpire expire() default CacheExpire.NONE; // 缓存时效,默认无限期
}
