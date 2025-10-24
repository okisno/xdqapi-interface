package com.xdq.xdqapiinterface;

import com.xdq.xdqapiinterface.client.XdqApiClient;
import com.xdq.xdqapiinterface.model.User;

public class Main {
    public static void main(String[] args) {
        String accessKey = "xdq";
        String secretKey = "abcdefgh";
        XdqApiClient xdqApiClient = new XdqApiClient(accessKey, secretKey);
        String result1 = xdqApiClient.getNameByGet("心荡秋");
        String result2 = xdqApiClient.getNameByPost("张三");
        User user = new User();
        user.setUsername("李四");
        String result3 = xdqApiClient.getUserNameByPost(user);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }
}
