package com.xu.wemall.components.weixin;

import com.alibaba.fastjson.JSONObject;
import com.xu.wemall.commons.constants.URIConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class TemplateUtil {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AccessTokenUtil accessTokenUtil;

    /**
     * 组件模板
     * @return
     */
    public String createTemplateString(){

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("touser", "发送到用户的openid");   // openid
        jsonObject.put("template_id", "你的模板id");
        jsonObject.put("url", "http://www.baidu.com");

        JSONObject data = new JSONObject();
        JSONObject first = new JSONObject();
        first.put("value", "hello");
        first.put("color", "#173177");
        JSONObject keyword1 = new JSONObject();
        keyword1.put("value", "hello");
        keyword1.put("color", "#173177");
        JSONObject keyword2 = new JSONObject();
        keyword2.put("value", "hello");
        keyword2.put("color", "#173177");
        JSONObject keyword3 = new JSONObject();
        keyword3.put("value", "hello");
        keyword3.put("color", "#173177");
        JSONObject remark = new JSONObject();
        remark.put("value", "hello");
        remark.put("color", "#173177");

        data.put("first",first);
        data.put("keyword1",keyword1);
        data.put("keyword2",keyword2);
        data.put("keyword3",keyword3);
        data.put("remark",remark);

        jsonObject.put("data", data);

        return jsonObject.toJSONString();

    }

    /**
     * 发送模板消息
     * @return
     */
    public JSONObject sendTemplate(){

        String templateString = this.createTemplateString();
        String accessToken = accessTokenUtil.getAccessToken();
        if(accessToken != null){
            log.info("URL{}",URIConstant.SEND_TEMPLATE_URL);
            String url = URIConstant.SEND_TEMPLATE_URL.replace("ACCESS_TOKEN", accessToken);
            log.info("URL_ACCESS_TOKEN:{}",url);
            //将菜单对象转换成JSON字符串
            String jsonMenu = JSONObject.toJSONString(templateString);
            log.info("JSONMENU:{}",jsonMenu);
            //发起POST请求创建菜单
            JSONObject jsonObject = restTemplate.postForObject(url, jsonMenu, JSONObject.class);

            return jsonObject;
        }
        return null;

    }

}
