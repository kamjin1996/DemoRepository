package com.zxx.demorepository.test.intercept2;

import com.zxx.demorepository.test.intercept2.resolver.MethodCryptMetadata;
import com.zxx.demorepository.test.intercept2.resolver.MethodCryptMetadataBuilder;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @auther: kam
 * @date: 16:58 2019-07-29
 * @description: Mybatis数据库脱敏插件
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class CryptInterceptor implements Interceptor {

    /**
     * true表示默认加密出参，false表示只认注解
     */
    private Boolean encryptWithOutAnnotation;
    /**
     * true表示默认解密出参，false表示只认注解
     */
    private Boolean decryptWithOutAnnotation;

    /**
     * 函数加密规则map
     */
    public static final ConcurrentHashMap<String, MethodCryptMetadata> METHOD_ENCRYPT_MAP = new ConcurrentHashMap<>();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MethodCryptMetadata methodCryptMetadata = this.getCachedMethodCryptMetaData((MappedStatement) args[0]);
        // 加密
        args[1] = methodCryptMetadata.encrypt(args[1]);
        // 获得出参
        Object returnValue = invocation.proceed();
        // 解密
        return methodCryptMetadata.decrypt(returnValue);
    }

    private MethodCryptMetadata getCachedMethodCryptMetaData(MappedStatement mappedStatement) {
        return METHOD_ENCRYPT_MAP.computeIfAbsent(mappedStatement.getId(), id ->
                new MethodCryptMetadataBuilder(id, encryptWithOutAnnotation, decryptWithOutAnnotation).build()
        );
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        encryptWithOutAnnotation = Boolean.valueOf(properties.getProperty("encryptWithOutAnnotation", "false"));
        decryptWithOutAnnotation = Boolean.valueOf(properties.getProperty("decryptWithOutAnnotation", "false"));
    }

}
