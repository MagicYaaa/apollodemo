package org.demo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: apollodemo
 * @description:
 * @author: Ya
 * @create: 2018-12-27 15:15
 **/
@SpringBootApplication
@EnableApolloConfig //用来指示Apollo注入application namespace的配置到Spring环境中
public class App {
    public static void main(String[] args){
        SpringApplication.run(App.class,args);
    }
}
