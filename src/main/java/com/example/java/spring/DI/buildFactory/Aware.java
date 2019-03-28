package com.example.java.spring.DI.buildFactory;

import java.util.HashMap;
import java.util.Map;

public interface Aware extends BeanContent {
    void init();
}
