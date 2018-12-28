package org.demo.Controller;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import org.demo.Bean.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: apollodemo
 * @description:
 * @author: Ya
 * @create: 2018-12-27 15:25
 **/
@Controller
public class demoController {
    @Autowired
    People people;

    @Autowired
    Environment env;

    @RequestMapping("/people")
    @ResponseBody
    String method(){ //通过apollo placeholder实现people类中属性的更新,
        return people.toString();
    }

    @RequestMapping("/application")
    @ResponseBody  //通过EnableApolloConfig注解，将application命名空间的配置注入spring环境中,所以我们可以直接获取环境中的属性
    String method2(){ //获取配置文件信息
        return "a="+env.getProperty("a")+
                " b="+env.getProperty("b");
    }

    @RequestMapping("/listener")
    @ResponseBody
    String method3(){   //触发监听器,这里是一种API实现方式，同样也有通过注解实现的方式，具体请查看文档
        Config config = ConfigService.getAppConfig(); //config instance is singleton for each namespace and is never null
        config.addChangeListener(new ConfigChangeListener() {
            @Override
            public void onChange(ConfigChangeEvent changeEvent) {
                System.out.println("监听器触发----Changes for namespace " + changeEvent.getNamespace());
                for (String key : changeEvent.changedKeys()) {
                    ConfigChange change = changeEvent.getChange(key);
                    System.out.println(String.format("Found change - key: %s, oldValue: %s, newValue: %s, changeType: %s", change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
                }
            }
        });
        return "触发Listener";
    }
}
