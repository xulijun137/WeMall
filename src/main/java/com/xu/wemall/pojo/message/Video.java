package com.xu.wemall.pojo.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
/**
  * 
  * @Description: 视频model
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
public class Video {

    //媒体文件ID
    private String MediaId;

    private String Title;

    private String Description;

}

