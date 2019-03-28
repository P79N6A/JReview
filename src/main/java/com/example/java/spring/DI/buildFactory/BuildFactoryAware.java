package com.example.java.spring.DI.buildFactory;

import com.google.common.base.Splitter;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

public class BuildFactoryAware implements Aware {

    //構建工具替你创建后放到map容器中
    public void init() {
        StringBuilder bean = new StringBuilder();
        try {
            Files.readLines(getFile("bean/bean.properties"), Charset.defaultCharset()).forEach(x -> {
                if (x != null && !(x.startsWith("#"))) {
                    bean.append(x).append(":");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> result = Splitter.on(":").withKeyValueSeparator("=").split(bean.substring(0, bean.length() - 1));
        proxyBean(mapContent, result);
    }

    //    通过cglib进行反射实例化bean
    private void proxyBean(Map<String, Object> mapContent, Map<String, String> mapObj) {
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
        if (file.exists()) {
            return file;
        }
        return null;
    }
}


