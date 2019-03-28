package com.example.java.jottings.cache.controller;

import com.example.java.jottings.cache.server.CacheServer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @描述
 * @参数 $params$
 * @返回值 $return$
 * @创建人 czb
 * @创建时间 $date$
 * @修改人和其它信息
 */
@RestController
public class CacheTest {

    private  static final Log log = LogFactory.getLog(CacheTest.class);

    @Autowired
    CacheServer cacheServer;

    @RequestMapping("/")
    public void text(){
        String login = cacheServer.textCache("张三", "1231");
        log.info(login);
    }
}
