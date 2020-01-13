package com.xu.wemall.components.weixin;

import com.alibaba.fastjson.JSONObject;
import com.xu.wemall.commons.constants.URIConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;

/**
 * 功能：永久素材工具类
 */
@Slf4j
@Component
public class MaterialUtil {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AccessTokenUtil accessTokenUtil;

    /**
     * 获取素菜数量
     * @return
     */
    public String getMaterialcount(){

        String accessToken = accessTokenUtil.getAccessToken();
        if(accessToken != null){
            String url = URIConstant.GET_MATERIALCOUNT_URL.replace("ACCESS_TOKEN", accessToken);
            //发起GET请求
            String resultString = restTemplate.getForObject(url, String.class);
            return resultString;
        }
        return null;
    }

    /**
     * 获取素菜列表
     * @return
     */
    public String batchgetMaterial(String type, Integer offset, Integer count){

        String accessToken = accessTokenUtil.getAccessToken();
        if(accessToken != null){
            String url = URIConstant.BATCHGET_MATERIAL_URL.replace("ACCESS_TOKEN", accessToken);
            log.info("BATCHGET_MATERIAL_URL:{}",url);

            JSONObject jsonObject = new JSONObject();
            //素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
            jsonObject.put("type", type);
            //从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
            jsonObject.put("offset", offset);
            //返回素材的数量，取值在1到20之间
            jsonObject.put("count", count);

            //发起POST请求
            String resultString = restTemplate.postForObject(url, jsonObject.toJSONString(),String.class);
            return resultString;
        }
        return null;
    }


    /**
     * 根据mediaId获取永久素菜
     */
    public ResponseEntity<byte[]> downloadMaterialImage(String mediaId){

        String accessToken = accessTokenUtil.getAccessToken();
        if(accessToken != null) {
            String url = URIConstant.GET_MATERIAL_URL.replace("ACCESS_TOKEN", accessToken);
            log.info("GET_MATERIAL_URL:{}", url);

            String fileName = mediaId+ ".jpg";
            JSONObject data = new JSONObject();
            data.put("media_id", mediaId);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            try {
                fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            headers.setContentDispositionFormData("attachment", fileName);// 文件名称
            ResponseEntity<byte[]> responseEntity = restTemplate.postForEntity(url, data, byte[].class);
            return responseEntity;
        }
        return null;
    }

    /**
     * 【添加永久素菜】
     * 新增永久视频素材需特别注意,在上传视频素材时需要POST另一个表单，包含素材的描述信息，内容格式为JSON
     */
    public String addMaterialMultipartFile(MultipartFile file, String type,
                              String title, String introduction) throws Exception{

        String accessToken = accessTokenUtil.getAccessToken();
        if (accessToken != null) {
            String url = URIConstant.ADD_MATERIAL_URL.replace("ACCESS_TOKEN", accessToken)
                    .replace("TYPE", type);
            log.info("URI:------{}",url);

            //设置请求体，注意是LinkedMultiValueMap
            MultiValueMap<String, Object> data = new LinkedMultiValueMap<>();
            if("vedio".equalsIgnoreCase(type)){
                if(!StringUtils.isEmpty(title) && !StringUtils.isEmpty(introduction)){
                    data.add("title", title);
                    data.add("introduction", introduction);
                }
            }

            //设置上传文件
            ByteArrayResource arrayResource = new ByteArrayResource(file.getBytes(),file.getOriginalFilename());
            data.add("media", arrayResource);

            //上传文件,设置请求头
            HttpHeaders headers = new HttpHeaders();
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(data,
                    headers);
            try{
                //这里RestTemplate请求返回的字符串直接转换成JSONObject会报异常,后续深入找一下原因
                String resultString = restTemplate.postForObject(url, requestEntity, String.class);
                log.info("上传返回的信息是：{}",resultString);
                return resultString;
            }catch (Exception e){
                log.error(e.getMessage());
            }
        }
        return null;

    }

    /**
     * 添加永久素菜
     * 新增永久视频素材需特别注意,在上传视频素材时需要POST另一个表单，包含素材的描述信息，内容格式为JSON
     */
    public String addMaterialFilePath(String filePath, String type,
                              String title, String introduction) {

        String accessToken = accessTokenUtil.getAccessToken();
        if (accessToken != null) {
            String url = URIConstant.ADD_MATERIAL_URL.replace("ACCESS_TOKEN", accessToken)
                    .replace("TYPE", type);
            log.info("ADD_MATERIAL_URL:{}",url);

            //设置请求体，注意是LinkedMultiValueMap
            MultiValueMap<String, Object> data = new LinkedMultiValueMap<>();
            if("vedio".equalsIgnoreCase(type)){
                if(!StringUtils.isEmpty(title) && !StringUtils.isEmpty(introduction)){
                    data.add("title", title);
                    data.add("introduction", introduction);
                }
            }

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
                String resultString = restTemplate.postForObject(url, requestEntity, String.class);
                log.info("上传返回的信息是：{}",resultString);
                return resultString;
            }catch (Exception e){
                log.error(e.getMessage());
            }
        }
        return null;

    }

}

