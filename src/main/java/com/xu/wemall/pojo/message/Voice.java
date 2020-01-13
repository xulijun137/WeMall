package com.xu.wemall.pojo.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
  * 
  * @Description: 语音model
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
public class Voice {

    //语音消息媒体ID
    private String mediaId;

}

