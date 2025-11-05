package com.xdq.xdqapiinterface.controller;

import com.xdq.xdqapiclientsdk.model.User;
import com.xdq.xdqapiclientsdk.utils.SignUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

/**
 * @author xdq
 * @description
 */
@RestController
@RequestMapping("/name")
public class NameController {

    @GetMapping("/")
    public String getNameByGet(String name, HttpServletRequest request) {
        System.out.println(request.getHeader("X-Request-red"));
        return "GET 你的名字是" + name;
    }

    @PostMapping("/")
    public String getNameByPost(@RequestParam String name) {
        return "POST 你的名字是" + name;
    }

    @PostMapping("/json")
    public String getUserNameByPost(@RequestBody User user, HttpServletRequest request) {
        String accessKey = request.getHeader("accessKey");
        String nonce = request.getHeader("nonce");
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");
        String body = URLDecoder.decode(request.getHeader("body"));
        //不能直接获取密钥
        //String secretKey = request.getHeader("secretKey");

        //校验权限
        //TODO:实际情况，需要从数据库中查询
        if (!accessKey.equals("xdq")){
            throw new RuntimeException("无权限");
        }

        if(Long.parseLong((nonce)) > 10000){
            throw new RuntimeException("无权限");
        }

        // 时间戳差不能超过5分钟
        if (Math.abs(System.currentTimeMillis() / 1000 - Long.parseLong(timestamp)) > 60 * 5){
            throw new RuntimeException("无权限");
        }

        // 服务端使用同样算法生成签名
        //TODO:实际情况，secretKey由服务端签发，这里从数据库查出
        String serverSign = SignUtils.genSign(body, "abcdefgh");
        if (!sign.equals(serverSign)){
            throw new RuntimeException("无权限");
        }

        String result = "POST 用户名字是" + user.getUsername();
        return result;
    }


}
