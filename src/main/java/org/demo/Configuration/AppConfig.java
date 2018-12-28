package org.demo.Configuration;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.demo.Bean.People;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: apollodemo
 * @description:
 * @author: Ya
 * @create: 2018-12-27 15:51
 **/
@Configuration
@EnableApolloConfig("people")//注入Demo.people 命名空间的配置注入到环境中
public class AppConfig {
    @Bean
    public People people(){
        return new People();
    }
}
