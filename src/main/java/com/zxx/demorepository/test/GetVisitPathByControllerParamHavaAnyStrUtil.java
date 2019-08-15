package com.zxx.demorepository.test;

import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @auther: tuosen
 * @date: 9:50 2019-08-15
 * @description: 获取某些controller参数字段含有某些字符的访问路径的集合，比如要获取参数含有mobile或Mobile字段的接口的访问路径list,待优化:(递归查看参数是否包含，路径写成活的，方法解耦，拆解)
 */
public class GetVisitPathByControllerParamHavaAnyStrUtil {

    private static final String PACKAGE_PATH = "D:\\NewWorkSpace\\sign-web-api\\src\\main\\java\\cn\\tsign\\web\\sign\\controller";

    private static final String[] STR = {"mobile", "Mobile"};

    public static void main(String[] args) {
        List<String> list = getControllerPathByUseMobileFieldForParam(PACKAGE_PATH);
        list.forEach(System.out::println);
    }

    public static List<String> getControllerPathByUseMobileFieldForParam(String controllerPackagePath) {
        List<String> all = new ArrayList<>();
        List<Class> allControllerList = getAllControllerCls(controllerPackagePath);

        for (Class controllerCls : Objects.requireNonNull(allControllerList)) {
            Method[] methodList = controllerCls.getMethods();
            for (Method method : methodList) {
                if (isContainParam(method)) {
                    String visitPath = getVisitPath(controllerCls, method);
                    all.add(visitPath);
                }
            }
        }
        return all;
    }

    private static boolean isContainParam(Method method) {
        Parameter[] parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            if (isContain(parameter)) {
                return true;
            }

        }
        return false;
    }

    private static boolean isContain(Parameter parameter) {
        if (parameter.getType().isAssignableFrom(String.class) && isContainStr(parameter.getName())) {
            return true;
        }
        if (parameter.getType().isAssignableFrom(List.class) || parameter.getType().isAssignableFrom(Map.class)) {
            return false;
        }
        Field[] declaredFields = parameter.getType().getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getType().isAssignableFrom(List.class)) {
                Field[] genericTypeFields = field.getGenericType().getClass().getDeclaredFields();
                for (Field genericTypeField : genericTypeFields) {
                    boolean containMobile = isContainStr(genericTypeField.getName());
                    if (containMobile) {
                        return true;
                    }
                }
            }
            boolean containMobile = isContainStr(field.getName());
            if (containMobile) {
                return true;
            }
        }
        return false;
    }

    private static boolean isContainStr(String source) {
        for (String str : STR) {
            boolean contains = source.contains(str);
            if (contains) {
                return true;
            }
        }
        return false;
    }


    private static String getVisitPath(Class controllerCls, Method method) {
        StringBuilder visitpath = new StringBuilder();
        //获取并拼接路径1
        getPathByAnotationList(visitpath, controllerCls.getAnnotations());
        //获取并拼接路径2
        getPathByAnotationList(visitpath, method.getAnnotations());
        return visitpath.toString();
    }

    private static void getPathByAnotationList(StringBuilder sb, Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().isAssignableFrom(RequestMapping.class)) {
                RequestMapping requestMapping = (RequestMapping) annotation;
                if (requestMapping.value().length > 0) {
                    sb.append(Objects.nonNull(requestMapping.value()[0]) ? requestMapping.value()[0] : "");
                } else if (requestMapping.path().length > 0) {
                    sb.append(Objects.nonNull(requestMapping.path()[0]) ? requestMapping.path()[0] : "");
                }
                break;
            }
        }
    }

    private static List<Class> getAllControllerCls(String filepath) {
        List<Class> controllerList = new ArrayList<>();
        File file = new File(filepath);
        boolean isDirectory = file.isDirectory();
        if (isDirectory) {
            File[] files = file.listFiles();
            for (File sonFile : Objects.requireNonNull(files)) {
                if (sonFile.isDirectory()) {
                    for (File sonFile1 : Objects.requireNonNull(sonFile.listFiles())) {
                        controllerList.add(getControllerClsByFile(sonFile1));
                    }
                } else if (sonFile.isFile()) {
                    controllerList.add(getControllerClsByFile(sonFile));
                }
            }
        } else {
            //路径非文件夹

        }
        return controllerList;
    }

    private static Class getControllerClsByFile(File file) {
        boolean controller = file.getName().contains("Controller");
        if (controller) {
            try {
                return Class.forName(file.getAbsolutePath().replace("\\", ".").replace("D:.NewWorkSpace.sign-web-api.src.main.java.", "").replace(".java", ""));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

