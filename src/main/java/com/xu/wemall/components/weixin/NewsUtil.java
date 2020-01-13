package com.xu.wemall.components.weixin;

import com.alibaba.fastjson.JSONObject;
import com.xu.wemall.commons.constants.URIConstant;
import com.xu.wemall.pojo.news.Articles;
import com.xu.wemall.pojo.news.News;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：图文素材工具类
 */
@Slf4j
@Component
public class NewsUtil {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AccessTokenUtil accessTokenUtil;

    private Articles createArticles(){

        Articles articles = new Articles();

        List<News> dataList = new ArrayList<>();
        News  news1 = new News();
        news1.setTitle("标题");
        news1.setThumb_media_id("J49eq_VE823b_wZH3Op4DFkLa4Lm4jkTSxX_VbiBWhY");
        news1.setAuthor("作者");
        news1.setDigest("图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空。如果本字段为没有填写，则默认抓取正文前64个字。");
        news1.setShow_cover_pic(1);//显示封面
        news1.setContent("图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS,涉及图片url必须来源 \"上传图文消息内的图片获取URL\"接口获取。外部图片url将被过滤。");
        news1.setContent_source_url("https://www.baidu.com/");  //图文消息的原文地址，即点击“阅读原文”后的URL
        news1.setNeed_open_comment(1);   //Uint32	是否打开评论，0不打开，1打开
        news1.setOnly_fans_can_comment(1);    //Uint32	是否粉丝才可评论，0所有人可评论，1粉丝才可评论


        News  news2 = new News();
        news2.setTitle("标题");
        news2.setThumb_media_id("J49eq_VE823b_wZH3Op4DOvK45tuhPJfr3n1_h1w1h8");
        news2.setAuthor("作者");
        news2.setDigest("图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空。如果本字段为没有填写，则默认抓取正文前64个字。");
        news2.setShow_cover_pic(1);//显示封面
        news2.setContent("图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS,涉及图片url必须来源 \"上传图文消息内的图片获取URL\"接口获取。外部图片url将被过滤。");
        news2.setContent_source_url("https://www.baidu.com/");  //图文消息的原文地址，即点击“阅读原文”后的URL
        news2.setNeed_open_comment(1);   //Uint32	是否打开评论，0不打开，1打开
        news2.setOnly_fans_can_comment(1);    //Uint32	是否粉丝才可评论，0所有人可评论，1粉丝才可评论
        dataList.add(news1);
        dataList.add(news2);

        articles.setArticles(dataList);
        return articles;

    }
    /**
     *新增永久图文素材
     */
    public String addNews() {

        Articles articles = this.createArticles();
        String accessToken = accessTokenUtil.getAccessToken();
        if (accessToken != null) {
            log.info("URL{}", URIConstant.ADD_NEWS_URL);
            String url = URIConstant.ADD_NEWS_URL.replace("ACCESS_TOKEN", accessToken);
            log.info("ADD_NEWS_URL:{}", url);

            //将菜单对象转换成JSON字符串
            String jsonNews = JSONObject.toJSONString(articles);
            log.info("JSONNEWS:{}",jsonNews);

            //发起POST请求创建菜单
            String jsonObject = restTemplate.postForObject(url, jsonNews,String.class);

            return jsonObject;
        }
        return null;
    }

    /**
     上传图文消息内的图片获取URL
     */
    public String uploadimg(String filePath) {

        String accessToken = accessTokenUtil.getAccessToken();
        if (accessToken != null) {
            String url = URIConstant.UPLOAD_IMG_URL.replace("ACCESS_TOKEN", accessToken);
            log.info("UPLOAD_IMG_URL:{}",url);

            //设置请求体，注意是LinkedMultiValueMap
            MultiValueMap<String, Object> data = new LinkedMultiValueMap<>();

            //设置上传文件
            FileSystemResource fileSystemResource = new FileSystemResource(filePath);
            data.add("media", fileSystemResource);

            //上传文件,设置请求头
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
            httpHeaders.setContentLength(fileSystemResource.getFile().length());

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(data,
                    httpHeaders);
            try{
                //这里RestTemplate请求返回的字符串直接转换成JSONObject会报异常,后续深入找一下原因
//                ResponseEntity<JSONObject> resultEntity = restTemplate.exchange(url,
//                        HttpMethod.POST, requestEntity, JSONObject.class);
                String resultJSON = restTemplate.postForObject(url, requestEntity, String.class);
                log.info("上传返回的信息是：{}",resultJSON);
                return resultJSON;
            }catch (Exception e){
                log.error(e.getMessage());
            }
        }
        return null;

    }

}

