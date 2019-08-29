package com.zxx.demorepository.test;

import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @auther: tuosen
 * @date: 9:50 2019-08-15
 * @description: 获取某些controller参数字段含有某些字符的访问路径的集合，比如要获取参数含有mobile或Mobile字段的接口的访问路径list,待优化:(递归查看参数是否包含，路径写成活的，方法解耦，拆解)
 */
public class GetVisitPathByControllerParamHavaAnyStrUtil2 {

    //预定义
    private static final String JAVA_CLASS_SUFFIX = ".class";

    private static final String FILE_SEPARATOR = "\\";

    private static final String DOT = ".";

    private static final String EMPTY = "";

    private static final String COMPILE_FOLDER = "\\classes\\";

    //自定义
    private static final String JAVA_TYPE_FEATURE = "Controller";

    private static final String[] PARAM_STR = {"mobile", "Mobile"};

    public static void main(String[] args) {
        List<String> list = getControllerPathByUseMobileFieldForParam();
        list.forEach(System.out::println);
    }

    public static List<String> getControllerPathByUseMobileFieldForParam() {
        List<String> all = new ArrayList<>();
        List<Class> allControllerList = getAllClsList();

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
        if (parameter.getType().isAssignableFrom(List.class)) {
            return listIsContain(parameter);
        }
        //无法处理map，因为map内容属于方法的runtime
        if (parameter.getType().isAssignableFrom(Map.class)) {
            return false;
        }
        return beanIsContain(parameter);
    }

    private static boolean listIsContain(Parameter parameter) {
        //TODO
        return true;
    }

    private static boolean beanIsContain(Parameter parameter) {
        Field[] fields = parameter.getType().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(Boolean.TRUE);
            if (field.getType().isAssignableFrom(List.class)) {
                beanIsContain(field.getGenericType());
            } else if (isContainStr(field.getName())) {
                return true;
            }
        }
        return false;
    }

    private static boolean beanIsContain(Type type) {
        Field[] fields = type.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (isContainStr(field.getName())) {
                return true;
            }
        }
        return false;
    }

    private static boolean isContainStr(String source) {
        for (String str : PARAM_STR) {
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
                    sb.append(Objects.nonNull(requestMapping.value()[0]) ? requestMapping.value()[0] : EMPTY);
                } else if (requestMapping.path().length > 0) {
                    sb.append(Objects.nonNull(requestMapping.path()[0]) ? requestMapping.path()[0] : EMPTY);
                }
                break;
            }
        }
    }

    private static List<File> getAllFileByPath(File file, List<File> fileList) {
        if (file.isDirectory()) {
            for (File son : Objects.requireNonNull(file.listFiles())) {
                getAllFileByPath(son, fileList);
            }
        } else if (file.isFile()) {
            fileList.add(file);
        }
        return fileList;
    }

    private static List<Class> getAllClsList() {
        List<File> fileList = new ArrayList<>();
        getAllFileByPath(new File(ClassLoader.getSystemResource(EMPTY).getFile()), fileList);
        return fileList.stream()
                .map(GetVisitPathByControllerParamHavaAnyStrUtil2::getClsByFile)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private static Class getClsByFile(File file) {
        boolean controllerClsFile = file.getName().contains(JAVA_TYPE_FEATURE) && file.getName().endsWith(JAVA_CLASS_SUFFIX);
        if (controllerClsFile) {
            try {
                String className = file.getPath().substring(file.getPath().indexOf(COMPILE_FOLDER))
                        .replace(COMPILE_FOLDER, EMPTY).replace(FILE_SEPARATOR, DOT)
                        .replace(JAVA_CLASS_SUFFIX, EMPTY);
                return Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

