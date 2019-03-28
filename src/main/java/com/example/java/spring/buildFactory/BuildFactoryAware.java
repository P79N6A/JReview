package com.example.java.spring.buildFactory;

import com.google.common.base.Splitter;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

public class BuildFactoryAware implements Aware {

    //構建工具替你创建后放到map容器中
    public void init() {
        StringBuilder bean = new StringBuilder();
        List<File> files = getFile("bean");
        files.forEach(file -> {
            try {
                Files.readLines(file, Charset.defaultCharset()).forEach(x -> {
                    if (x != null && !(x.startsWith("#"))) {
                        bean.append(x).append(":");
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
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
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }


    private List getFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(fileName);
        String path = url.getPath();
        File[] files = new File(path).listFiles();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (file.exists()
                    && file.getName().endsWith(".properties")
                    && file.getName().startsWith("bean-")
                    || file.getName().startsWith("bean")) {
                listFile.add(files[i]);
            }
        }
        return listFile;
    }
}


