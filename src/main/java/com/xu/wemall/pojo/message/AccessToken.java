//package com.xu.wemall.pojo.message;
//
//import com.alibaba.fastjson.annotation.JSONField;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//
///**
// * JSONField注解不起作用，不晓得为什么，无法实现字段映射转换
// */
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@EqualsAndHashCode(callSuper = false)
//public class AccessToken {
//
//    // 接口访问凭证
//    @JSONField(name="access_token")
//    private String access_token;
//
//    // 凭证有效期，单位：秒
//    @JSONField(name="expires_in")
//    private Integer expires_in;
//
//}
