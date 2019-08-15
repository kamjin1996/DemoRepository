package com.zxx.demorepository.test.intercept;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Objects;
import java.util.Properties;


@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class TestIntercept implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("进入拦截器1号>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
        //
        Object[] args = invocation.getArgs();
        System.out.println(invocation.getTarget().toString());

        for (Object arg : args) {
            if (Objects.nonNull(arg)) {
                System.out.println("参数: " + arg.toString());
            }
        }
        //对实体类做拦截

        Object returnObject = invocation.proceed();

        return returnObject;
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
