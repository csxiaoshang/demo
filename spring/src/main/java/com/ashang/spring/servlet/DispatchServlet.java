package com.ashang.spring.servlet;

import com.ashang.spring.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * @author ashang  970090853@qq.com
 * @Date 19-9-19 上午9:47
 * <p>
 * 类说明：
 *  * 此类作为启动入口,当该servlet启动时,会调用init()方法,从init方法的参数中,获取主配置文件的路径,从而获取配置文件信息
 *  * 1. 加载配置文件
 *  * 2. 从配置文件中获取类的所在, 扫描所有相关的类
 *  * 3. 初始化所有扫描到的类, 添加到IOC容器中
 *  * 4. 依赖注入
 *  * 5. 构造handlerMapping
 *  * 6. 启动服务器, 自动接收请求并转发到该servlet进行处理
 */
public class DispatchServlet extends HttpServlet {


    private static String configPath = "configFilePath";

    private static Properties properties = new Properties();

    private static ConcurrentHashMap<String, Object> iocBean = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<String, Method> handleMapping = new ConcurrentHashMap<>();

    private static List<String> className = new ArrayList<>();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //处理用户的请求
        try {
            dispatch(request,response);
        } catch (Exception e) {
            response.getWriter().write("500 Exception, Details:\r\n" + Arrays.toString(e.getStackTrace())
                    .replaceAll("\\[|\\]","")
                    .replaceAll(",\\s","\r\n"));
        }

    }


    @Override
    public void init(ServletConfig config) {

        System.out.println("spring is initing !!!");
        // 加载配置文件 保存配置文件属性
        doLoadConfig(config.getInitParameter(configPath));
        // 扫描类文件 将扫描到的文件放到容器中
        doScanBean(properties.getProperty("scanPackage"));
        // 创建实例
        doInstance();
        // 注入依赖
        doAutowired();
        // 构建映射
        handleMapping();
        System.out.println("spring 初始化完成!!");
    }


    private void doLoadConfig(String param) {
        InputStream inputStream = null;
        inputStream = this.getClass().getClassLoader().getResourceAsStream(param);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("加载配置文件完成 内容为 :" +properties);
    }

    private void doScanBean(String packageName) {
        String filePath = packageName.replaceAll("\\.", "/");
        URL url = this.getClass().getClassLoader().getResource(filePath);
        File dir = new File(url.getFile());
        for (File file : dir.listFiles()) {
            System.out.println(file.toString());
            if (file.isDirectory()) {
                doScanBean(packageName + "." + file.getName());
            } else {
                className.add(packageName + "." + file.getName().replaceAll(".class", "").trim());
            }
        }
        System.out.println("包扫描文件完成 "+className);
    }

    private void doInstance() {
        if (className.size() == 0) {
            return;
        }
        try {
            for (String name : className) {
                Class clz = null;
                clz = Class.forName(name);
                if (isMyComponent(clz) && !clz.isAnnotationPresent(MyService.class)) {
                    String beanName = clz.getSimpleName().toLowerCase();

                    iocBean.put(beanName, clz.newInstance());

                } else if (clz.isAnnotationPresent(MyService.class)) {
                    MyService myService = (MyService) clz.getAnnotation(MyService.class);
                    String beanName = myService.value();
                    // 如果设置beanName 则以beanName newInstance
                    if (!"".equals(beanName.trim())) {
                        iocBean.put(beanName, clz.newInstance());
                        continue;
                    }

                    // 没有设置beanName的话 以接口名称创建实例
                    Class<?>[] interfaces = clz.getInterfaces();
                    for (Class in : interfaces) {
                        iocBean.put(in.getName(), clz.newInstance());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("实例化bean完成 "+iocBean);
    }


    private boolean isMyComponent(Class clz) {
        return Stream.of(clz.getAnnotations())
                .map(annotation -> annotation.annotationType())
                .anyMatch(annotation -> annotation.isAnnotationPresent(MyComponent.class));
    }

    private void doAutowired() {
        if (iocBean.size() == 0) {
            return;
        }
        for (Map.Entry<String, Object> entry : iocBean.entrySet()) {
            System.out.println("依赖bean扫描 "+entry.getValue().getClass());
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                System.out.println("依赖bean field " +field);
                if (!field.isAnnotationPresent(MyAutowired.class)) {
                    continue;
                }
                MyAutowired myAutowired = field.getAnnotation(MyAutowired.class);
                String beanName = myAutowired.value();
                if ("".equals(beanName.trim())) {
                    beanName = field.getType().getName();
                }
                System.out.println("依赖注入beanName "+beanName);
                field.setAccessible(true);
                try {
                    field.set(entry.getValue(), iocBean.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("注入依赖完成");
    }

    /**
     * 将MyRequestMapping中配置url和method进行关联
     */
    private void handleMapping() {

        if (iocBean.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : iocBean.entrySet()) {
            Class clz = entry.getValue().getClass();
            if (!clz.isAnnotationPresent(MyController.class)) {
                continue;
            }
            StringBuilder baseUrl = new StringBuilder();
            if (clz.isAnnotationPresent(MyRequestMapping.class)) {
                MyRequestMapping myRequestMapping = (MyRequestMapping) clz.getAnnotation(MyRequestMapping.class);
                baseUrl.append(myRequestMapping.value());
            }

            // 处理有MyRequestMapping注解的方法
            Method[] methods = clz.getMethods();
            Arrays.stream(methods)
                    .filter(method -> method.isAnnotationPresent(MyRequestMapping.class))
                    .forEach(method -> handleMapping.put(baseUrl.append(method.getAnnotation(MyRequestMapping.class).value()).toString(),method));
        }
        System.out.println("handleMapping完成 :"+handleMapping);
    }

    /**
     *  根据url分发请求
     * @param request
     * @param response
     */
    private void dispatch(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("开始分发HTTP请求!!");
        if (handleMapping.isEmpty()) {
            return;
        }
        String url = request.getRequestURI();
        System.out.println("url :"+url);
        String contextPath = request.getContextPath();
        System.out.println("contextPath :"+contextPath);
        url = url.replace(contextPath, "");
        System.out.println("修改后url :"+url);
        if (!handleMapping.containsKey(url)) {
            try {
                response.getWriter().write("404 not find!!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 获取请求参数
        Map<String, String[]> paramMap = request.getParameterMap();
        // 根据url获取对应方法
        Method method = handleMapping.get(url);
        // 获取方法的参数列表
        Class<?>[] paramTypes = method.getParameterTypes();
        Object[] paramValues = new Object[paramTypes.length];
        for (int i = 0; i < paramTypes.length; i++) {
            Class paramType = paramTypes[i];
            if (paramType == HttpServletRequest.class) {
                paramValues[i] = request;
                continue;
            } else if (paramType == HttpServletResponse.class) {
                paramValues[i] = response;
                continue;
            }else if(paramType == String.class){
                //如果是String类型的参数,从请求中取出参数的值并赋值给变量进行存储
                for (Map.Entry<String, String[]> param : paramMap.entrySet()) {
                    String value = Arrays.toString(param.getValue())
                            .replaceAll("\\[|]]", "")
                            .replaceAll(",\\s", ",");
                    paramValues[i] = value;
                }
            }
        }
        String beanName = method.getDeclaringClass().getSimpleName().toLowerCase();
        System.out.println("beanName "+beanName);
        try {
            method.invoke(iocBean.get(beanName), paramValues);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
































