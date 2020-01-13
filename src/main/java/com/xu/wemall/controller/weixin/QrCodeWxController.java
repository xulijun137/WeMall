package com.xu.wemall.controller.weixin;

import com.alibaba.fastjson.JSONObject;
import com.xu.wemall.components.weixin.QrCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * 类名称: LoginController
 * 类描述: 与微信对接登陆验证
 *
 * @author RonnieXu
 * 创建时间:2017年12月5日上午10:52:13
 */
@Slf4j
@RestController
@Api(tags = "微信生成带参的二维码接口")
@RequestMapping(value = "/weixin/qrcode")
public class QrCodeWxController {

    @Autowired
    private QrCodeUtil qrCodeUtil;

    @ApiOperation(value = "创建带参的二维码")
    @RequestMapping(value = "/createQrCode", method = RequestMethod.POST)
    public Object createQrCode(String sceneId) {

        JSONObject jsonObject = qrCodeUtil.createQrCode(sceneId);
        return jsonObject;

    }

    @ApiOperation(value = "获取带参的二维码")
    @RequestMapping(value = "/showQrCode", method = RequestMethod.GET)
    public void showQrCode(String ticket) throws UnsupportedEncodingException {
        qrCodeUtil.showQrCode(ticket);

    }

}
