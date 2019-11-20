package com.lihang.service.impl;

import com.lihang.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public void hello(String name) {
        System.out.println(name);
    }
}
