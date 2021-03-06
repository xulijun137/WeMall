package com.xu.wemall.pojo.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @Description: 音乐消息
 * @Parameters:
 * @Return:
 * @Create Date:
 * @Version: V1.00
 * @author:来日可期
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MusicMessage extends BaseMessage {

    //音乐
    private Music music;

}

