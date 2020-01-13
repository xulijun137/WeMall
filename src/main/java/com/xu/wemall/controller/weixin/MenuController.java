package com.xu.wemall.controller.weixin;

import com.alibaba.fastjson.JSONObject;
import com.xu.wemall.components.weixin.MenuUtil;
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
@Api(tags = "微信菜单接口")
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    private MenuUtil menuUtil;

    @ApiOperation(value = "创建自定义菜单")
    @RequestMapping(value = "/createMenu", method = RequestMethod.POST)
    public Object createMenu() {

        JSONObject jsonObject = menuUtil.creatMenu();
        return jsonObject;

    }

    @ApiOperation(value = "查询自定义菜单")
    @RequestMapping(value = "/getMenu", method = RequestMethod.GET)
    public Object getMenu() {

        JSONObject jsonObject = menuUtil.getWXMenu();
        return jsonObject;

    }

}
