package com.xu.wemall.components.weixin;

import com.alibaba.fastjson.JSONObject;
import com.xu.wemall.commons.constants.URIConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 获取微信服务器IP地址
 */
@Slf4j
@Component
public class IPUtil {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AccessTokenUtil accessTokenUtil;

    public JSONObject getCallbackIp(){

        String accessToken = accessTokenUtil.getAccessToken();
        if(accessToken != null){
            String url = URIConstant.GET_CALLBACK_IP.replace("ACCESS_TOKEN", accessToken);
            //发起GET请求
            JSONObject resultString = restTemplate.getForObject(url, JSONObject.class);
            return resultString;
        }
        return null;
    }

    public JSONObject getApiDomainIp(){

        String accessToken = accessTokenUtil.getAccessToken();
        if(accessToken != null){
            String url = URIConstant.GET_API_DOMAIN_IP.replace("ACCESS_TOKEN", accessToken);
            //发起GET请求
            JSONObject resultString = restTemplate.getForObject(url, JSONObject.class);
            return resultString;
        }
        return null;
    }
}
