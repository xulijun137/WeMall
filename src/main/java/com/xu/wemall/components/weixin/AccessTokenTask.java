//package com.xu.wemall.components.accessToken;
//
//import com.xu.wemall.components.redis.RedisUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
///**
// * 定时获取access_token,并更新到redis中保存
// */
//@Slf4j
//@Component
//public class AccessTokenTask {
//
//    @Autowired
//    private AccessTokenUtil accessTokenUtil;
//
//    @Autowired
//    private RedisUtil redisUtil;
//
//    private final String TOKEN_KEY = "access_token";
//
//    /**
//     * 每115分钟（反正不到2小时）获取一次access_token，并将其放到redis，同时保证可以从redis中读取出来
//     * 项目启动1秒中之后执行一次，然后每90min执行一次，单位都为ms
//     */
//    @Scheduled(fixedRate= 1000*60*115, initialDelay = 1000)
//    public void getWeixinAccessToken(){
//
//        log.info("获取到的微信access_token的定时任务开始启动了……");
//        try {
//            String accessToken = accessTokenUtil.getAccessToken();
//            if(accessToken != null){
//                redisUtil.set(TOKEN_KEY,accessToken,60*120);//设置2小时过期
//                log.info("获取到的微信access_token为:{}", accessToken);
//            }
//
//        } catch (Exception e) {
//            log.error("获取微信access_toke出错，信息如下{}", e.getMessage());
//            this.getWeixinAccessToken();
//        }
//    }
//
//}
//
