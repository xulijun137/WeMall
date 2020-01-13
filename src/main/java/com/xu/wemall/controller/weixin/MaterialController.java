package com.xu.wemall.controller.weixin;

import com.xu.wemall.components.weixin.MaterialUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 类名称: MaterialController
 * 类描述: 永久素材接口
 */
@Slf4j
@RestController
@Api(tags = "永久素材接口")
@RequestMapping(value = "/material")
public class MaterialController {

    @Autowired
    private MaterialUtil materialUtil;

    @ApiOperation(value = "获取各类素材的数量")
    @RequestMapping(value = "/getMaterialcount", method = RequestMethod.GET)
    public Object getMaterialcount() {

        String tempString = materialUtil.getMaterialcount();
        return tempString;

    }

    /**
     * 根据media_id获取永久素菜
     */
    @ApiOperation(value = "根据media_id获取永久素菜")
    @RequestMapping(value = "/downloadMaterial", method = RequestMethod.POST)
    public Object getMaterial(String mediaId) {

        ResponseEntity<byte[]> tempString = materialUtil.downloadMaterialImage(mediaId);
        return tempString;

    }

    /**
     * 获取永久素材列表
     */
    @ApiOperation(value = "获取永久素菜列表")
    @RequestMapping(value = "/batchgetMaterial", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name="type",value="素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）", paramType="query",dataType="String"),
            @ApiImplicitParam(name="offset",value="从全部素材的该偏移位置开始返回，0表示从第一个素材返回", paramType="query",dataType="Integer"),
            @ApiImplicitParam(name="count",value="返回素材的数量，取值在1到20之间", paramType="query",dataType="Integer")
    })
    public Object batchgetMaterial(String type, Integer offset, Integer count) {

        String tempString = materialUtil.batchgetMaterial(type, offset, count);
        return tempString;

    }

    /**
     * 添加永久素材列表
     */
    @ApiOperation(value = "添加永久素材MultipartFile")
    @RequestMapping(value = "/addMaterialMultipartFile", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name="title",value="视频素材的标题", paramType="query",dataType="String"),
            @ApiImplicitParam(name="introduction",value="视频素材的描述", paramType="query",dataType="String"),
            @ApiImplicitParam(name="type",value="素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）", paramType="query",dataType="String")
    })
    public Object addMaterialMultipartFile(@ApiParam(name = "upfile",value="短视频",required=true)MultipartFile upfile, String title, String introduction, String type)
            throws Exception{

        String tempString = materialUtil.addMaterialMultipartFile(upfile, title, introduction, type);
        return tempString;

    }

    /**
     * 添加永久素材列表
     */
    @ApiOperation(value = "添加永久素材FilePath")
    @RequestMapping(value = "/addMaterialFilePath", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name="filePath",value="文件路径", paramType="query",dataType="String"),
            @ApiImplicitParam(name="title",value="视频素材的标题", paramType="query",dataType="String"),
            @ApiImplicitParam(name="introduction",value="视频素材的描述", paramType="query",dataType="String"),
            @ApiImplicitParam(name="type",value="素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）", paramType="query",dataType="String")
    })
    public Object addMaterialFilePath(String filePath, String type,
                                      String title, String introduction) {

        String tempString = materialUtil.addMaterialFilePath(filePath, type, title, introduction);
        return tempString;

    }

}
