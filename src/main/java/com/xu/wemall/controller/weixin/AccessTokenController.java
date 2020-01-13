package com.xu.wemall.controller.weixin;

import com.xu.wemall.components.weixin.AccessTokenUtil;
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
@Api(tags = "ACCESS_TOKEN接口")
@RequestMapping(value = "/token")
public class AccessTokenController {

    @Autowired
    private AccessTokenUtil accessTokenUtil;

    @ApiOperation(value = "获取ACCESS_TOKEN")
    @RequestMapping(value = "/getAccessToken", method = RequestMethod.GET)
    public String getAccessToken() {

        String accessToken = accessTokenUtil.getAccessToken();
        return accessToken;

    }

}
