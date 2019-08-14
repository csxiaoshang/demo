package com.ashang.spring.annotation;

import java.lang.annotation.*;

/**
 * @author ashang  970090853@qq.com
 * @Date 19-9-20 上午10:41
 * <p>
 * 类说明：
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestParam {
}
