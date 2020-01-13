package com.xu.wemall.pojo.menu;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ViewButton extends Button{

    private String type;

    private String url;

}
