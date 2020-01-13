package com.xu.wemall.pojo.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 * @Description: 图文消息
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
public class NewsMessage extends BaseMessage {

    //图文消息个数,限制为10条以内
    private int ArticleCount;

    //多条图文消息信息，默认第一个item为大图
    private List<Article> Articles;

}
