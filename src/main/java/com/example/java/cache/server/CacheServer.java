package com.example.java.cache.server;

import com.example.java.cache.CacheableAnnotation.CacheExpire;
import com.example.java.cache.CacheableAnnotation.MyCacheAble;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @描述
 * @参数 $params$
 * @返回值 $return$
 * @创建人 czb
 * @创建时间 $date$
 * @修改人和其它信息
 */
@Service
public class CacheServer {

    private static AtomicInteger a = new AtomicInteger(0);

    @MyCacheAble(key="username" ,refreshTTL = true,expire = CacheExpire.ONE_MIN)
    public String textCache(String username,String password){
        a.getAndIncrement();
        System.out.println(a.get());

        return username+"  "+password;
    }
}
