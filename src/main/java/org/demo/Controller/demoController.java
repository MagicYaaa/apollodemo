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
    String method(){
        return people.toString();
    }

    @RequestMapping("/application")
    @ResponseBody
    String method2(){ //获取配置文件信息
        return "a="+env.getProperty("a")+
                " b="+env.getProperty("b");
    }

    @RequestMapping("/listener")
    @ResponseBody
    String method3(){
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
        return "Listener";
    }

}
