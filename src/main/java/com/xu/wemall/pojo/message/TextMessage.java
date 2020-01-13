package com.xu.wemall.pojo.message;

import lombok.*;

/**
 *
 * @Description: 文本消息
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
@EqualsAndHashCode(callSuper = true)
public class TextMessage extends BaseMessage {

    private String Content;// 文本消息内容

    private String MsgId;// 消息id，64位整型

}
