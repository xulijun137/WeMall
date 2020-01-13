package com.xu.wemall.pojo.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
/**
  * 
  * @Description: 图片model
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
public class Image {

    //媒体文件ID
    private String MediaId;

}
