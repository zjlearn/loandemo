package com.example.loandemo.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * create by zhangjun1 on 2017/12/3
 */
public class RequestUtil {
    public static IpsResponse request(String operationType,  String merchantID, String request){
        CloseableHttpClient httpclient = HttpClients.createDefault();

        //每个系统的换行符 不一样
        String lineSeparator = System.getProperty("line.separator", "\n");

        //对request进行加密
        String encrypedRequest= IpsCrypto.triDesEncrypt(request, ModelParam.encryptKey, ModelParam.iv).replaceAll(lineSeparator, "");

        //MD5 for sign
        String  sign=operationType+merchantID+encrypedRequest+ModelParam.mdstr;
        String signMD5=IpsCrypto.md5Sign(sign).toLowerCase();

        //设置http报文的参数， operationType， merchantID，sign， request
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("operationType", operationType));
        formparams.add(new BasicNameValuePair("merchantID", ModelParam.merchantID));
        formparams.add(new BasicNameValuePair("sign", signMD5));
        formparams.add(new BasicNameValuePair("request", encrypedRequest));
        UrlEncodedFormEntity urlentity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        //新建一个HttpPost代表发送的是http协议，POST方法。 ModelParam.TestServerAdderss是常量， 保存在ModelParam类中
        //这里测试系统，所以为     public final static String TestServerAdderss="http://180.168.26.114:20010/p2p-deposit/gateway.htm";
        HttpPost httppost = new HttpPost(ModelParam.TestServerAdderss);
        //设置构造的表单参数
        httppost.setEntity(urlentity);

        try {
            // 执行httppost， 也就是将请求报文发送出去，得到结果。
            CloseableHttpResponse response = httpclient.execute(httppost);

            HttpEntity entity = response.getEntity();
            long len= entity.getContentLength();
            //获得返回结果的内容，将其读入到text字符串中
            Reader reader= new BufferedReader( new InputStreamReader(entity.getContent()));
            char [] data= new char[1024];
            int readLen=0;
            StringBuilder sb= new StringBuilder();
            if( (readLen=reader.read(data))!= -1){
                sb.append(data,0,readLen);
            }
            String text=sb.toString();
            //现在从IPS系统中获取到了所有的数据， 存储在了text字符串中， 字符串可能包含两种类型。html或者json
            if(entity.getContentType().getValue().contains("html")){
                System.out.println(text);
                return new IpsResponse(text);
            }else { //json格式的字符串
                //从字符串转到JSONObject，这样我们就可以根据key得到value.
                JSONObject result = JSONObject.parseObject(text);
                System.out.println("the  result code is " + (String) result.get("resultCode"));
                //我们构造一个IpsResponse对象代表返回的结果
                return new IpsResponse((String) result.get("resultCode"), (String) result.get("resultMsg"), (String) result.get("sign"), (String) result.get("response"));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("get some exception!\n");
        }
        System.out.println("new Response!");
        return new IpsResponse("exception!\n");
    }
}