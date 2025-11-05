package com.xdq.xdqapiinterface;

import com.xdq.xdqapiclientsdk.XdqApiClientConfig;
import com.xdq.xdqapiclientsdk.client.XdqApiClient;
import com.xdq.xdqapiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@EnableConfigurationProperties(XdqApiClientConfig.class)
class XdqapiInterfaceApplicationTests {

    @Resource
    private XdqApiClient xdqApiClient;

    @Test
    void contextLoads() {
        String result = xdqApiClient.getNameByGet("xdq");
        User user = new User();
        user.setUsername("xdq");
        String userNameByPost = xdqApiClient.getUserNameByPost(user);
        System.out.println(result);
        System.out.println(userNameByPost);
    }

}
