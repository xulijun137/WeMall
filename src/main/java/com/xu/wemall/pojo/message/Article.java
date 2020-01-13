package com.xu.wemall.pojo.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
/**
  * 
  * @Description: 图文model
  * @Parameters: 
  * @Return: 
  * @Create Date: 
  * @Version: V1.00
  * @author: 来日可期
  */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Article {

    //图文消息名称
    private String Title;

    //图文消息描述
    private String Description;

    //图片链接，支持JPG、PNG格式,较好的效果为大图640像素*320像素,小图80像素*80像素
    private String PicUrl;

    //点击图文消息跳转链接
    private String Url;

}

