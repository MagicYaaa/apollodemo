package org.demo.Bean;

import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import org.springframework.beans.factory.annotation.Value;

/**
 * @program: apollodemo
 * @description:
 * @author: Ya
 * @create: 2018-12-27 15:32
 **/

public class People {
    @Value("${name:Tom}") //"%{key:默认值}" ，第二个参数是默认值，获取不到相应value时，以默认值代替
    private String name;
    @Value("${high:180}")
    private String high;
    @Value("${weight:70}")
    private String weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", high='" + high + '\'' +
                ", weight='" + weight + '\'' +
                '}';
    }
}
