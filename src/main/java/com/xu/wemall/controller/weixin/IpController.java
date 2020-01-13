package com.xu.wemall.controller.weixin;

import com.alibaba.fastjson.JSONObject;
import com.xu.wemall.components.weixin.IPUtil;
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
@Api(tags = "获取IP接口")
@RequestMapping(value = "/ip")
public class IpController {

    @Autowired
    private IPUtil ipUtil;

    @ApiOperation(value = "获取微信服务器IP地址")
    @RequestMapping(value = "/getIpCallback", method = RequestMethod.GET)
    public Object getIpCallback() {

        JSONObject tempString = ipUtil.getCallbackIp();
        return tempString;

    }

    @ApiOperation(value = "获取微信API接口 IP地址")
    @RequestMapping(value = "/getApiDomainIp", method = RequestMethod.GET)
    public Object getApiDomainIp() {

        JSONObject tempString = ipUtil.getApiDomainIp();
        return tempString;

    }


}
