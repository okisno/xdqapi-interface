package com.xdq.xdqapiinterface.client;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.xdq.xdqapiinterface.model.User;
import com.xdq.xdqapiinterface.utils.SignUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class XdqApiClient {

    private String accessKey;
    private String secretKey;

    public XdqApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(String name) {
        //单独传入http参数，参数会自动做URL编码，拼接在URL中
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.get("http://localhost:8080/api/name/", paramMap);
        System.out.println(result);
        return result;
    }

    public String getNameByPost(@RequestParam String name) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.post("http://localhost:8080/api/name/", paramMap);
        System.out.println(result);
        return result;
    }

    // 构造请求体
    private Map<String, String> getHeaderMap(String body) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey", accessKey);
        //不直接发送密钥
        //hashMap.put("secretKey", secretKey);
        //生成随机数
        hashMap.put("nonce", RandomUtil.randomNumbers(4));

        String encodebody = "";
        //解决中文乱码问题
        try {
            encodebody = URLEncoder.encode(body, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        //请求体内容
        hashMap.put("body", encodebody);
        // 当前时间戳
        // 当前时间毫秒数 /1000， 得到当前秒数
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        // 生成签名
        hashMap.put("sign", SignUtils.genSign(body, secretKey));
        return hashMap;
    }

    public String getUserNameByPost(@RequestBody User user) {
        // 将user对象转为json
        String json = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse = HttpRequest.post("http://localhost:8080/api/name/json")
                // 添加请求头
                .addHeaders(getHeaderMap(json))
                // 设置请求体
                .body(json)
                // 发送POST请求
                .execute();
        // 打印响应状态码
        System.out.println(httpResponse.getStatus());
        // 打印响应体内容
        String result = httpResponse.body();
        System.out.println(result);
        return result;
    }

}
