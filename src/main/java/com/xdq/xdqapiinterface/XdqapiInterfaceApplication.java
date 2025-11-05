package com.xdq.xdqapiinterface;

import com.xdq.xdqapiclientsdk.XdqApiClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(XdqApiClientConfig.class)
public class XdqapiInterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(XdqapiInterfaceApplication.class, args);
    }

}
