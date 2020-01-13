package com.xu.wemall.controller.weixin;

import com.alibaba.fastjson.JSONObject;
import com.xu.wemall.components.weixin.TemplateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类名称: LoginController
 * 类描述: 与微信对接登陆验证
 *
 * @author RonnieXu
 * 创建时间:2017年12月5日上午10:52:13
 */
@Slf4j
@RestController
@Api(tags = "模板消息接口")
@RequestMapping(value = "/template")
public class TemplateController {

    @Autowired
    private TemplateUtil templateUtil;

    @ApiOperation(value = "发送模板消息")
    @RequestMapping(value = "/sendTemplate", method = RequestMethod.POST)
    public Object createTemplate() {

        JSONObject jsonObject = templateUtil.sendTemplate();
        return jsonObject;

    }



}
