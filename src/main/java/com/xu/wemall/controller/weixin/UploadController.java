package com.xu.wemall.controller.weixin;

import com.xu.wemall.components.weixin.UploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类名称: UploadController
 * 类描述: 临时素材接口
 */
@Slf4j
@RestController
@Api(tags = "临时素材接口")
@RequestMapping(value = "/material")
public class UploadController {

    @Autowired
    private UploadUtil uploadUtil;

    @ApiOperation(value = "上传临时素材")
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name="filePath",value="文件位置", paramType="query",dataType="String"),
            @ApiImplicitParam(name="type",value="媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）", paramType="query",dataType="String"),

    })
    public Object upload(String filePath, String type) throws Exception{

        String result = uploadUtil.uploadFile(filePath,type);
        return result;
    }

    @ApiOperation(value = "下载临时素材")
    @RequestMapping(value = "/getFile", method = RequestMethod.GET)
    public Object upload(String mediaId) throws Exception{

        ResponseEntity<byte[]> result = uploadUtil.getImage(mediaId);
        return result;
    }
}
