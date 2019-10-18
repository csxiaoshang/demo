package com.ashang.spring.servlet;

import com.ashang.spring.annotation.MyComponent;
import com.ashang.spring.annotation.MyController;
import org.junit.Test;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Controller;
>>>>>>> master

import java.lang.reflect.AnnotatedElement;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class DispatchServletTest {

    @Test
public void testMyComponent() {

        Class clz = Controller.class;
        Class[] interfaces =   clz.getInterfaces();
        if (interfaces.length == 0) {
            System.out.println("类 没有继承任何接口");
        }
        boolean b = Stream.of(clz.getAnnotations())
                .map(annotation -> annotation.annotationType())
                .anyMatch(annotation -> annotation.isAnnotationPresent(MyComponent.class));
        if ( b){
            System.out.println("this class is MyComponent.class");
        }else {
            System.out.println("no");
        }
}

@MyController
public  class Controller{

    private  String name = "controller";
    }

}