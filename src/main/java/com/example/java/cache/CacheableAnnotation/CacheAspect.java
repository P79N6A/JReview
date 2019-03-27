package com.example.java.cache.CacheableAnnotation;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.TimeUnit;


/**
 * @描述 cache切面
 * @参数 $params$
 * @返回值 $return$
 * @创建人 czb
 * @创建时间 $date$
 * @修改人和其它信息
 */
@Aspect
@Component
public class CacheAspect {

    private static final  Log log = LogFactory.getLog(CacheAspect.class);

    public CacheAspect(){
        log.info("====CacheAspect create====");
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @Around("@annotation(cacheAble)")
    public Object cacheAble(final ProceedingJoinPoint pjp , MyCacheAble cacheAble){
        Object rtnObj = null;
        Object jsonObj = null;

        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate. setKeySerializer(redisSerializer);
        redisTemplate.setValueSerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        redisTemplate.setHashValueSerializer(redisSerializer);

        //缓存key
        String key = cacheAble.key();

        //获取返回类型
        Signature signature = pjp.getSignature();
        Class returnType = ((MethodSignature) signature).getReturnType();
        log.info("返回值为： "+ returnType);

        if(returnType == List.class){
            key = "LIST:" + key;
        }
        if(returnType == Map.class){
            key = "MAP:" + key;
        }
        if(returnType == Set.class){
            key = "SET:" + key;

        }
        if(returnType == SortedSet.class){
            key = "SortedSet:" + key;
        }
        if(returnType == String.class){
            key = "STRING:" + key;
        }

        //获取连接点方法运行时的入参列表
        if(pjp.getArgs()!=null && pjp.getArgs().length>0){
            key = key + ":";
            for(Object param:pjp.getArgs()){
                key = key + param + "_";
                //key:1_2_3_4
            }
        }


        //第一步从缓存中查找
        ValueOperations<String, Object> valueOper = redisTemplate.opsForValue();
        jsonObj = valueOper.get(key);

        //缓存中如果有，
        if (jsonObj != null) {
            log.info("Parse==="+ JSONObject.parse(jsonObj.toString()));
            if(cacheAble.expire().getTime()>0 && cacheAble.refreshTTL()) {
                valueOper.set(key, JSONObject.toJSON(jsonObj).toString(), cacheAble.expire().getTime(), TimeUnit.SECONDS);//重新刷新ttl时间
            }
            log.info("jsonobj" + jsonObj);
            return jsonObj;
        }else{//缓存没有
            try {
                //先执行原来的方法
                rtnObj = pjp.proceed();

            } catch (Throwable e) {
                log.info(e.getMessage());
            }
            if (rtnObj != null){
                log.info("set cache key="+key+", expireTime="+cacheAble.expire().getTime());
                if (rtnObj != null && cacheAble.expire().getTime() > 0) {
                    log.info(key+" "+JSONObject.toJSON(rtnObj).toString() +"  "+ cacheAble.expire().getTime()+" "+ TimeUnit.SECONDS);
                    valueOper.set(key, JSONObject.toJSON(rtnObj).toString(), cacheAble.expire().getTime(), TimeUnit.SECONDS);
                }else {
                    valueOper.set(key, JSONObject.toJSON(rtnObj).toString());
                }
            }
        }
        log.info("原始返回值类型" + returnType);
        log.info("实际返回值类型" + rtnObj.getClass());
        return  rtnObj;
    }



}
