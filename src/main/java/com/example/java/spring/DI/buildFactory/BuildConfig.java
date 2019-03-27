package com.example.java.spring.DI.buildFactory;

import com.google.common.base.Splitter;
import com.google.common.base.Utf8;
import com.google.common.io.Files;
import net.sf.cglib.proxy.Enhancer;
import org.apache.commons.lang.CharSetUtils;
import org.aspectj.weaver.ast.Var;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Map;

public class BuildConfig {

    //構建工具替你创建后放到map容器中
    public BuildConfig(Map<String, Object> mapContent) {
//        读取bean配置
        String bean = null;
        try {
              Files.readLines(getFile("bean/bean.txt"), Charset.defaultCharset());
//            bean = Files.toString(new File("classpath:bean/bean.txt"), Charset.defaultCharset()).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> result = Splitter.on("\r\n").withKeyValueSeparator("=").split(bean);
        proxyBean(mapContent, result);
    }

    //    通过cglib进行反射实例化bean
    public void proxyBean(Map<String, Object> mapContent, Map<String, String> mapObj) {
        mapObj.forEach((k, v) -> {
            try {
                Class<?> name = Class.forName(v);
                Object instance = name.newInstance();
                mapContent.put(k, instance);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            }
        });
    }


    private File getFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(fileName);
        File file = new File(url.getFile());
        System.out.println(file.exists());
        if (file.exists()) {
            return file;
        }
        return null;
    }
}
