package com.zxx.demorepository.test;

import com.google.common.collect.Lists;
import com.zxx.demorepository.DemorepositoryApplication;
import com.zxx.demorepository.test.entity.User;
import com.zxx.demorepository.test.service.MyUserService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Predicate;

/**
 * @Auther: KAM1996
 * @Date: 15:52 2018-10-18
 * @Description: junit测试
 * @Version: 1.0
 */
@MapperScan("com.zxx.demorepository.test.mapper")
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringTest {

    @Value("${msg}")
    private String msg;

    @Autowired
    private MyUserService myUserService;

    @Test
    public void testMsg() {
        System.out.println(this.msg);
    }

    //新增
    @Test
    public void insert() {
        List<User> userList = Lists.newArrayList();

        for (int i = 0; i < 10; i++) {
            userList.add(User.builder().age(10 + i).username("李四" + i).password("123456").build());
        }
        this.insertBatch(userList);
    }

    public void insertBatch(List<User> userList) {
        userList.forEach(user -> this.myUserService.insertUser(user));
    }


    //修改
    @Test
    public void update1() {
        User user = User.builder().id(15).age(8).username("李四").build();
        this.myUserService.updateUser2(user);
    }

    //查找
    @Test
    public void find() {
        List<User> users = this.myUserService.selectUsers();
        Predicate<User> predicate = user -> user.getAge() > 8;
        users.stream().filter(predicate).forEach(System.out::println);

    }

    @Test
    public void encrypt() {
        User user = new User();
        user.setId(13);
        user.setUsername("哈哈哈");

        this.myUserService.updateUser(user);

    }

    @Test
    public void decrypt() {
        User user = this.myUserService.findById(6);
        if (Objects.nonNull(user)) {
            System.out.println("用户名称：" + user.getUsername());
            System.out.println("用户密码：" + user.getPassword());
        }
    }

    @Test
    public void decrypt2() {
        User user = this.myUserService.findByAgeAndName(6, "小李");
        if (Objects.nonNull(user)) {
            System.out.println("用户名称：" + user.getUsername());
        }

    }

    @Test
    public void getControllerPathByUseMobileFieldForParam(String filePath){
        List<String> all = new ArrayList<>();
        List<Class> allControllerList = null;
        try {
            allControllerList = getAllController(filePath);
        } catch (ClassNotFoundException e) {
            System.out.println("获取所有controller失败");
        }

        for (Class controllerCls : Objects.requireNonNull(allControllerList)) {
            Method[] methodList = controllerCls.getMethods();
            for (Method method : methodList) {
                if (isContainMobileParam(method)) {
                    String visitPath = getVisitPath(controllerCls, method);
                    all.add(visitPath);
                }
            }
        }
        all.forEach(System.out::println);
    }

    private boolean isContainMobileParam(Method method){
        Parameter[] parameters = method.getParameters();
        for(Parameter parameter : parameters){
            if(isContainMobile(parameter)){
                return true;
            }

        }
        return false;
    }

    private boolean isContainMobile(Parameter parameter) {
        if(parameter.getType().isAssignableFrom(String.class) && (parameter.getName().contains("mobile") || parameter.getName().contains("Mobile"))){
            return true;
        }
        if(parameter.getType().isAssignableFrom(List.class) || parameter.getType().isAssignableFrom(Map.class)){
            return false;
        }
        Field[] declaredFields = parameter.getType().getDeclaredFields();
        for(Field field: declaredFields){
            boolean containMobile = field.getName().contains("mobile") || field.getName().contains("Mobile");
            if(containMobile){
                return true;
            }
        }
        return false;
    }


    private String getVisitPath(Class controllerCls, Method method) {
        StringBuilder visitpath = new StringBuilder();
        getPathByAnotationList(visitpath, controllerCls.getAnnotations());
        getPathByAnotationList(visitpath, method.getAnnotations());
        return visitpath.toString();
    }

    private void getPathByAnotationList(StringBuilder sb, Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().isAssignableFrom(RequestMapping.class)) {
                RequestMapping requestMapping = (RequestMapping) annotation;
                sb.append(requestMapping.value()[0]);
                sb.append(requestMapping.path()[0]);
                break;
            }
        }
    }

    private List<Class> getAllController(String filepath) throws ClassNotFoundException {
        List<Class> controllerList = new ArrayList<>();
        File file = new File(filepath);
        boolean isDirectory = file.isDirectory();
        if (isDirectory) {
            File[] files = file.listFiles();
            for (File sonFile : Objects.requireNonNull(files)) {
                boolean controller = sonFile.getName().contains("Controller");
                if (controller) {
                    Class controllerClass = Class.forName(sonFile.getPath().replaceAll("/", ".").replace("java", ""));
                    controllerList.add(controllerClass);
                }

            }
        }
        return controllerList;
    }

    //删除
    @Test
    public void del() {
        this.myUserService.delUser(9);
    }

    private final ReentrantReadWriteLock cacheLock = new ReentrantReadWriteLock(true);
    private final ReentrantLock lock = new ReentrantLock(true);
    private final ReentrantReadWriteLock.ReadLock readLock = cacheLock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = cacheLock.writeLock();

    public static class lockTest {

        public Thread thread1 = new Thread(() -> {
            for (int a = 1; a <= 100; a++) {
                System.out.println(Thread.currentThread().getName() + " 第" + a + "次");
            }
        }, "线程A");

        public Thread thread2 = new Thread(() -> {
            for (int a = 1; a <= 10; a++) {
                System.out.println(Thread.currentThread().getName() + " 第" + a + "次");
            }
        }, "线程B");

    }

    public static void main(String[] args) {

    }
}
