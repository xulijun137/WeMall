package com.xu.wemall.controller.weixin;

import com.alibaba.fastjson.JSONObject;
import com.xu.wemall.components.weixin.NewsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类名称: NewsController
 * 类描述: 图文素材接口
 */
@Slf4j
@RestController
@Api(tags = "图文素材接口")
@RequestMapping(value = "/news")
public class NewsController {

    @Autowired
    private NewsUtil newsUtil;

    @ApiOperation(value = "上传图文素材")
    @RequestMapping(value = "/addNews", method = RequestMethod.POST)
    public Object addNews() throws Exception{

        String result = newsUtil.addNews();
        //log.info("resut:{}",JSONObject.parseObject(result).toJSONString());
        return result;
    }

    @ApiOperation(value = "上传图文消息内的图片获取URL")
    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
    public Object uploadImg(String filePath) {

        String result = newsUtil.uploadimg(filePath);
        log.info("resut:{}",JSONObject.parseObject(result).toJSONString());
        return result;
    }

}
