package com.xu.wemall.entry;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author RonnieXu
 * @since 2019-12-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_lover")
@ApiModel(value="有缘人对象", description="有缘人对象模型")
public class Lover extends BaseEntity<Lover> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="用户名")
    private String loverName;

    @ApiModelProperty(value="年龄")
    private Integer age;

    @ApiModelProperty(value="性别")
    private String sex;

    @ApiModelProperty(value="身高")
    private Double height;

    @ApiModelProperty(value="状态")
    private String state;

}
