package com.xu.wemall.controller.weixin;

import com.alibaba.fastjson.JSONObject;
import com.xu.wemall.commons.utils.CheckUtil;
import com.xu.wemall.components.weixin.MessageUtil;
import com.xu.wemall.components.weixin.WeiXinUserUtil;
import com.xu.wemall.pojo.message.Article;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 类名称: LoginController
 * 类描述: 与微信对接登陆验证
 *
 * @author RonnieXu
 * 创建时间:2017年12月5日上午10:52:13
 */
@Slf4j
@RestController
@Api(tags = "接入验证接口")
@RequestMapping(value = "/weChart")
public class WeiXinController {

    @Autowired
    private WeiXinUserUtil weiXinUserUtil;

    @Autowired
    private MessageUtil messageUtil;

    @RequestMapping(value = "/connect", method = RequestMethod.GET)
    public String connect(@RequestParam(value = "signature") String signature,
                          @RequestParam(value = "timestamp") String timestamp,
                          @RequestParam(value = "nonce") String nonce,
                          @RequestParam(value = "echostr") String echostr) {

        log.info("-----开始校验签名-----");
        PrintWriter out = null;
        if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
            log.info("-----签名校验通过-----");
            return echostr;
        } else {
            log.info("-----校验签名失败-----");
            return null;
        }

    }

    @RequestMapping(value = "connect", method = RequestMethod.POST)
    public String dopost(HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setCharacterEncoding("utf-8");

        //将微信请求xml转为map格式，获取所需的参数
        Map<String, String> map = MessageUtil.parseXml(request);
        String ToUserName = map.get("ToUserName");
        String FromUserName = map.get("FromUserName");
        String MsgType = map.get("MsgType");
        String Content = map.get("Content");
        String Event = map.get("Event");
        String EventKey = map.get("EventKey");

        if(MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(MsgType)){

            if(MessageUtil.EVENT_TYPE_SUBSCRIBE.equals(Event)){
                String xmlString = messageUtil.subscribeForText(ToUserName,FromUserName);

                //关注了公众号，调用接口获得用户的详细信息并保存到后台
                JSONObject jsonObject = weiXinUserUtil.handdleWeixinUserInfo(FromUserName);
                log.info("获取用户的详细信息：{}",jsonObject.toJSONString());

                return xmlString;

            }else if(MessageUtil.EVENT_TYPE_UNSUBSCRIBE.equals(Event)){

                String xmlString = messageUtil.unsubscribeForText(ToUserName,FromUserName);
                return xmlString;

            }else if(MessageUtil.EVENT_TYPE_SCAN.equals(Event)){
                JSONObject jsonObject = weiXinUserUtil.handdleWeixinUserInfo(FromUserName);
                log.info("获取用户的详细信息：{}",jsonObject.toJSONString());

            }

        }

        //处理文本类型，实现输入1，回复相应的封装的内容
        if (MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(MsgType)) {
            String xmlString = messageUtil.replyForText(ToUserName,FromUserName,"你发送的是：" + Content);
            log.info(xmlString);
            return xmlString;

        }

        if (MessageUtil.REQ_MESSAGE_TYPE_IMAGE.equals(MsgType)) {

            String filePath = "C:\\Users\\RonnieXu\\Pictures\\2.jpg";
            String xmlString = messageUtil.replyForImage(ToUserName,FromUserName,filePath);
            return xmlString;
        }

        if ("1".equals(EventKey)) {

            List<Article> articles = new ArrayList<>();
            Article article = new Article();
            article.setTitle("Hello, Ronnie");
            article.setDescription("这是一条描述，这是一条描述");
            article.setPicUrl("https://www.baidu.com/img/superlogo_c4d7df0a003d3db9b65e9ef0fe6da1ec.png?where=super");
            article.setUrl("https://www.baidu.com/");
            articles.add(article);

            String xmlString = messageUtil.replyForArticles(ToUserName,FromUserName,articles);
            return xmlString;
        }

        return null;
    }

}
