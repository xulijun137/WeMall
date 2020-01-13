package com.xu.wemall.controller.weixin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xu.wemall.commons.constants.WXConstant;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 类名称: LoginController
 * 类描述: 与微信对接登陆验证
 *
 * @author RonnieXu
 * 创建时间:2017年12月5日上午10:52:13
 */
@Slf4j
@RestController
@Api(tags = "授权登录接口")
@RequestMapping(value = "/wxAuth")
public class WxAuthController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/login")
    public void wxLogin(HttpServletResponse response) throws IOException {
        //请求获取code的回调地址
        //用线上环境的域名或者用内网穿透，不能用ip
        String callBack = "http://jialeyuan.nat300.top/wxAuth/callBack";

        //请求地址
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize" +
                "?appid=" + WXConstant.TEST_APPID +
                "&redirect_uri=" + URLEncoder.encode(callBack,"utf-8") +
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=STATE#wechat_redirect";
        //重定向
        response.sendRedirect(url);
    }

    //	回调方法
    @RequestMapping("/callBack")
    public void wxCallBack(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");

        //获取access_token
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=" + WXConstant.TEST_APPID +
                "&secret=" + WXConstant.TEST_APPSECRET +
                "&code=" + code +
                "&grant_type=authorization_code";

        String result = restTemplate.getForObject(url, String.class);

        System.out.println("请求获取access_token:" + result);
        //返回结果的json对象
        JSONObject resultObject = JSON.parseObject(result);

        //请求获取userInfo
        String infoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                "?access_token=" + resultObject.getString("access_token") +
                "&openid=" + resultObject.getString("openid") +
                "&lang=zh_CN";

        String resultInfo = restTemplate.getForObject(infoUrl, String.class);

        //此时已获取到userInfo，再根据业务进行处理
        log.info("请求获取userInfo:{}", resultInfo);

    }

}
