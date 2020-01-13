//package com.xu.wemall.components.weixin;
//
//import com.alibaba.fastjson.JSONObject;
//import com.xu.wemall.commons.constants.URIConstant;
//import com.xu.wemall.pojo.menu.Button;
//import com.xu.wemall.pojo.menu.ComplexButton;
//import com.xu.wemall.pojo.menu.Menu;
//import com.xu.wemall.pojo.menu.ViewButton;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//@Slf4j
//@Component
//public class WxAuthUtil {
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Autowired
//    private AccessTokenUtil accessTokenUtil;
//
//    private Menu createMenuString(){
//
//        ViewButton btn11 = new ViewButton();
//        btn11.setName("自定义上墙");
//        btn11.setType("view");
//        btn11.setUrl("https://mp.weixin.qq.com/mp/homepage?__biz=MzU0MzgwODY1NA==&hid=1&sn=29d121fa4b68f29842b918d6adc406fe");
//
//        ViewButton btn12 = new ViewButton();
//        btn12.setName("自定义活动");
//        btn12.setType("view");
//        btn12.setUrl("https://mp.weixin.qq.com/mp/homepage?__biz=MzU0MzgwODY1NA==&hid=2&sn=77daea8c9f9435e20cd5e5954c3d3748");
//
//        ViewButton btn13 = new ViewButton();
//        btn13.setName("信息自由发布");
//        btn13.setType("view");
//        btn13.setUrl("https://www.baidu.com/");
//
//        ViewButton btn14 = new ViewButton();
//        btn14.setName("Top10");
//        btn14.setType("view");
//        btn14.setUrl("https://www.toutiao.com/");
//
//        ViewButton btn21 = new ViewButton();
//        btn21.setName("经典回顾");
//        btn21.setType("view");
//        btn21.setUrl("https://www.toutiao.com/");
//
//        ViewButton btn22 = new ViewButton();
//        btn22.setName("信息查询");
//        btn22.setType("view");
//        btn22.setUrl("https://www.toutiao.com/");
//
////        viewButton btn23 = new viewButton();
////        btn23.setName("美女电台");
////        btn23.setType("view");
////        btn23.setKey("23");
////
////        viewButton btn24 = new viewButton();
////        btn24.setName("人脸识别");
////        btn24.setType("view");
////        btn24.setKey("24");
////
////        viewButton btn25 = new viewButton();
////        btn25.setName("聊天唠嗑");
////        btn25.setType("view");
////        btn25.setKey("25");
//
//        ViewButton btn31 = new ViewButton();
//        btn31.setName("提建议");
//        btn31.setType("view");
//        btn31.setUrl("https://www.toutiao.com/");
//
//        ViewButton btn32 = new ViewButton();
//        btn32.setName("举报");
//        btn32.setType("view");
//        btn32.setUrl("https://www.toutiao.com/");
//
//        ViewButton btn33 = new ViewButton();
//        btn33.setName("下墙");
//        btn33.setType("view");
//        btn33.setUrl("https://www.toutiao.com/");
//
//        ComplexButton mainBtn1 = new ComplexButton();
//        mainBtn1.setName("自由风");
//        mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13, btn14});
//
//        ComplexButton mainBtn2 = new ComplexButton();
//        mainBtn2.setName("浏览");
//        mainBtn2.setSub_button(new Button[] { btn21, btn22});
//
//        ComplexButton mainBtn3 = new ComplexButton();
//        mainBtn3.setName("联系作者");
//        mainBtn3.setSub_button(new Button[] { btn31, btn32, btn33});
//
//        Menu menu = new Menu();
//        menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });
//
//        return menu;
//
//    }
//
//    public JSONObject creatMenu(){
//
//        Menu menu = this.createMenuString();
//        String accessToken = accessTokenUtil.getAccessToken();
//        if(accessToken != null){
//            log.info("URL{}",URIConstant.MENU_CREATE_URL);
//            String url = URIConstant.MENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken);
//            log.info("URL_ACCESS_TOKEN:{}",url);
//            //将菜单对象转换成JSON字符串
//            String jsonMenu = JSONObject.toJSONString(menu);
//            log.info("JSONMENU:{}",jsonMenu);
//            //发起POST请求创建菜单
//            JSONObject jsonObject = restTemplate.postForObject(url, jsonMenu, JSONObject.class);
//
//            return jsonObject;
//        }
//        return null;
//
//    }
//    /**
//     *
//     * @Description: 查询菜单
//     * @Parameters:
//     * @Return:
//     * @Create Date: 2018年3月13日下午2:24:02
//     * @Version: V1.00
//     * @author: 来日可期
//     */
//    public JSONObject getWXMenu(){
//
//        String accessToken = accessTokenUtil.getAccessToken();
//        String requestUrl = URIConstant.MENU_GET_URL.replace("ACCESS_TOKEN", accessToken);
//        //发起GET请求查询菜单
//        JSONObject jsonObject = restTemplate.getForObject(requestUrl, JSONObject.class);
//        return jsonObject;
//
//    }
//    /**
//     *
//     * @Description: 删除菜单
//     * @Parameters:
//     * @Return:
//     * @Create Date: 2018年3月13日下午2:31:15
//     * @Version: V1.00
//     * @author: 来日可期
//     */
//    public JSONObject deleteMenu(String accessToken){
//
//        String requestUrl = URIConstant.MENU_DELETE_URL.replace("ACCESS_TOKEN", accessToken);
//        //发起GET请求删除菜单
//        JSONObject jsonObject = restTemplate.getForObject(requestUrl, JSONObject.class);
//
////        if (null != jsonObject) {
////            int errorCode = jsonObject.getIntValue("errcode");
////            String errorMsg = jsonObject.getString("errmsg");
////            if (0== errorCode) {
////                result = true;
////            } else {
////                result = false;
////                log.error("创建菜单失败 errcode：{} errmsg：{} ",errorCode,errorMsg);
////            }
////        }
//        return jsonObject;
//
//    }
//
//}
