package com.MJR.config;

import com.MJR.interceptor.SqlBeautyInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 22249
 * @version 1.0
 * @description:
 * @date 2022/12/1 10:51
 */
@Configuration
public class MybatisConfiguration {

    /**
     * description:一个插拔式的mybatis自定义SQL日志记录Interceptor
     * @author Mjr
     * @date 2022/12/1 11:18
     * @version 1.0
     */
    @Bean
    @ConditionalOnProperty(name = {"sql.beauty.show.enabled"},havingValue = "true",matchIfMissing = true)
    public SqlBeautyInterceptor sqlBeautyInterceptor(){
        return new SqlBeautyInterceptor();
    }
}
