package com.xu.wemall.components.weixin;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xu.wemall.commons.constants.URIConstant;
import com.xu.wemall.entry.WxUser;
import com.xu.wemall.service.IWxUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class WeiXinUserUtil {

    @Autowired
    private IWxUserService iWxUserService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AccessTokenUtil accessTokenUtil;

    public JSONObject handdleWeixinUserInfo(String openId) {

        String accessToken = accessTokenUtil.getAccessToken();
        if (accessToken != null) {
            log.info("URL{}", URIConstant.OPENID_USERINFO_URL);
            String url = URIConstant.OPENID_USERINFO_URL.replace("ACCESS_TOKEN", accessToken)
                    .replace("OPENID", openId);
            log.info("OPENID_USERINFO_URL:{}", url);

            //发起POST请求创建菜单
            JSONObject jsonObject = restTemplate.getForObject(url, JSONObject.class);

            //表示订阅了该公众号
            if (jsonObject.getIntValue("subscribe") == 1) {
                //保存
                WxUser wxUser = JSONObject.parseObject(jsonObject.toJSONString(), WxUser.class);

                //先查一下是否曾经查询过(查看数据库数据）
                QueryWrapper<WxUser> queryWrapper = new QueryWrapper();
                queryWrapper.lambda().eq(WxUser::getOpenid, openId);
                WxUser wxUserExist = iWxUserService.getOne(queryWrapper);
                if (wxUserExist == null) {
                    boolean result = iWxUserService.saveOrUpdate(wxUser);
                }

            }

            return jsonObject;
        }

        return null;
    }

}
