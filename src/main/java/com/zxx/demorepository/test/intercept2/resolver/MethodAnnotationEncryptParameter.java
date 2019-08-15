package com.zxx.demorepository.test.intercept2.resolver;

import com.zxx.demorepository.test.intercept2.annotation.CryptField;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 方法加密注解了的参数
 *
 * @author junliang.zhuo
 * @date 2019-03-29 11:38
 */
@AllArgsConstructor
@Getter
class MethodAnnotationEncryptParameter {

    private String paramName;
    private CryptField cryptField;
    private Class cls;
}
