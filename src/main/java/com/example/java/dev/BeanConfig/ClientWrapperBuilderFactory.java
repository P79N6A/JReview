package com.example.java.dev.BeanConfig;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ClientWrapperBuilderFactory implements ApplicationContextAware {

    private static Map<String, ClientWrapperBuilder> ClientWrapperMap;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ClientWrapperMap = applicationContext.getBeansOfType(ClientWrapperBuilder.class);
    }

    public static ClientWrapperBuilder getInstance(String serviceCode) {
        String key = serviceCode.toLowerCase() + ClientWrapperBuilder.class.getSimpleName();
        ClientWrapperBuilder ClientWrapperBuilder = ClientWrapperMap.get(key);
        if (ClientWrapperBuilder == null) {
            throw new RuntimeException("Unsupported serviceCode:" + serviceCode);
        }
        return ClientWrapperBuilder;
    }

}
