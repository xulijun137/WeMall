package com.xu.wemall.components.weixin;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.xu.wemall.commons.constants.URIConstant;
import com.xu.wemall.commons.constants.WXConstant;
import com.xu.wemall.components.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取access_token，注意他的有效期只有7200秒（2小时），而且获取access_token次数有限
 */
@Slf4j
@Component
public class AccessTokenUtil {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisUtil redisUtil;

    private final String TOKEN_KEY = "access_token";

    private final String TOKEN_TIME = "token_time";

    public String getAccessToken() {

        //先从redis中获取access_token
        String accessToken = (String) redisUtil.get(TOKEN_KEY);
        if (accessToken != null) {
            log.info("原来的access_token还有效存在！,有效时间是：{}秒, 产生的时间是:{}"
                    ,redisUtil.getExpire(TOKEN_KEY)
                    ,redisUtil.get(TOKEN_TIME)
                    );
            return accessToken;

        } else {
            //否则就重新开始获取access_token
            try {
                String url = URIConstant.ACCESS_TOKEN_URL;
                Map<String, String> params = new HashMap<>();

                //这里设置开发者appid和secret！！！
                params.put("appid", WXConstant.TEST_APPID);
                params.put("secret", WXConstant.TEST_APPSECRET);

                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime time = LocalDateTime.now();
                String localTime = df.format(time);

                JSONObject jsonObject = restTemplate.getForObject(url, JSONObject.class, params);
                if (null != jsonObject) {
                    log.info("调用接口获取新的access_token！,时间是：{}",localTime);
                    String access_token = jsonObject.getString(TOKEN_KEY);
                    int expires_in = jsonObject.getIntValue("expires_in");
                    if (access_token != null) {
                        log.error("获得access_token成功：{} ", jsonObject.toJSONString());
                        redisUtil.set(TOKEN_KEY, access_token, 7199);//保存2小时（官方是2小时失效）
                        redisUtil.set(TOKEN_TIME, localTime);//保存时间
                        return access_token;
                    } else {
                        int errorCode = jsonObject.getIntValue("errcode");
                        String errorMsg = jsonObject.getString("errmsg");
                        log.error("获得access_token失败：{} ", jsonObject.toJSONString());
                    }
                }

            } catch (RestClientException e) {
                log.error("请根据提示信息设置接口的白名单IP：", e.getMessage());
            } catch (JSONException e) {
                log.error("字符串转换异常：", e.getMessage());
            }
            return null;
        }

    }

}
