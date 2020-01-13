package com.xu.wemall.pojo.message;

import lombok.*;

/**
 *
 * @Description: 响应消息基类（公众账号→普通用户）
 * @Parameters:
 * @Return:
 * @Create Date:
 * @Version: V1.00
 * @author: 来日可期
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class BaseMessage {

    protected String ToUserName;
    protected String FromUserName;
    protected Long CreateTime;
    protected String MsgType;

}

