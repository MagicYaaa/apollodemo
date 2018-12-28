package org.demo.Bean;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.ctrip.framework.apollo.spring.annotation.ApolloJsonValue;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * @program: apollodemo
 * @description: 这是官方文档的一个使用例子,这里用作展示
 * @author: Ya
 * @create: 2018-12-28 13:05
 **/
public class TestApolloAnnotationBean {
    @ApolloConfig
    private Config config; //注入"application"命名空间的配置信息
    @ApolloConfig("application")
    private Config anotherConfig; //注入"application"命名空间的配置信息
    @ApolloConfig("FX.apollo")
    private Config yetAnotherConfig; //注入"FX.apollo"命名空间的配置信息

    /**
     * ApolloJsonValue annotated on fields example, the default value is specified as empty list - []
     * <br />
     * jsonBeanProperty=[{"someString":"hello","someInt":100},{"someString":"world!","someInt":200}]
     */
    @ApolloJsonValue("${jsonBeanProperty:[]}")
    private List<JsonBean> anotherJsonBeans; //ApolloJsonValue注解，用来把配置的json字符串自动注入为对象

    @Value("${batch:100}")
    private int batch;

    //config change listener for namespace application
    @ApolloConfigChangeListener
    private void someOnChange(ConfigChangeEvent changeEvent) {
        //update injected value of batch if it is changed in Apollo
        if (changeEvent.isChanged("batch")) {
            batch = config.getIntProperty("batch", 100);
        }
    }

    //config change listener for namespace application
    @ApolloConfigChangeListener("application")
    private void anotherOnChange(ConfigChangeEvent changeEvent) {
        //do something
    }

    //config change listener for namespaces application and FX.apollo
    @ApolloConfigChangeListener({"application", "FX.apollo"})
    private void yetAnotherOnChange(ConfigChangeEvent changeEvent) {
        //do something
    }

    //example of getting config from Apollo directly
    //this will always return the latest value of timeout
    public int getTimeout() {
        return config.getIntProperty("timeout", 200);
    }

    //example of getting config from injected value
    //the program needs to update the injected value when batch is changed in Apollo using @ApolloConfigChangeListener shown above
    public int getBatch() {
        return this.batch;
    }

    private static class JsonBean{
        private String someString;
        private int someInt;
    }
}
