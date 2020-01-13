package com.xu.wemall.components.weixin;

import com.alibaba.fastjson.JSONObject;
import com.xu.wemall.commons.constants.URIConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.TreeMap;

@Slf4j
@Component
public class QrCodeUtil {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AccessTokenUtil accessTokenUtil;

    private String createQrCodeString(String sceneStr) {

        TreeMap<String, String> params = new TreeMap<>();
        // output data
        JSONObject data = new JSONObject();
        data.put("action_name", "QR_SCENE");
        data.put("expire_seconds", 3600);//一小时

        JSONObject scene = new JSONObject();
        scene.put("scene_str", sceneStr);
        JSONObject actionInfo = new JSONObject();
        actionInfo.put("scene", scene);
        data.put("action_info", actionInfo);

        return data.toJSONString();

    }

    public JSONObject createQrCode(String sceneId) {

        String qrCodeString = this.createQrCodeString(sceneId);
        log.info("qrCodeString:{}", qrCodeString);

        String accessToken = accessTokenUtil.getAccessToken();
        if (accessToken != null) {
            log.info("URL{}", URIConstant.CREATE_QRCODE_URL);
            String url = URIConstant.CREATE_QRCODE_URL.replace("ACCESS_TOKEN", accessToken);
            log.info("URL_ACCESS_TOKEN:{}", url);
            //将菜单对象转换成JSON字符串

            //发起POST请求创建菜单
            JSONObject jsonObject = restTemplate.postForObject(url, qrCodeString, JSONObject.class);

            return jsonObject;
        }
        return null;

    }

    public void showQrCode(String ticket) throws UnsupportedEncodingException {

        String TICKET = URLEncoder.encode(ticket, "utf-8");

        log.info("URL{}", URIConstant.SHOW_QRCODE_URL);
        String url = URIConstant.SHOW_QRCODE_URL.replace("TICKET", TICKET);
        log.info("SHOW_QRCODE_URL:{}", url);
        //将菜单对象转换成JSON字符串

        //发起POST请求创建菜单
        restTemplate.getForEntity(url, JSONObject.class);

    }

}
