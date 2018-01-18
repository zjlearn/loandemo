package com.example.loandemo.handler;

import com.alibaba.fastjson.JSONObject;
import com.example.loandemo.model.dto.CommonQueryDTO;
import com.example.loandemo.util.IPSCONSTANTS;
import com.example.loandemo.util.IPSRSACryptoUtil;
import com.example.loandemo.model.IpsResponse;
import com.example.loandemo.model.ModelParam;
import com.example.loandemo.util.http.HttpManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

/**
 * create by zhangjun1 on 2018/1/9
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class IPSTest {

    @Autowired
    HttpManager httpManager;

    @Test
    public void queryTest(){
        String merchantID = "1186570022";
        String operationType = "query.commonQuery";
        CommonQueryDTO commonQueryDTO = new CommonQueryDTO("1023654", "02", "1533545");
        String req = JSONObject.toJSONString(commonQueryDTO);
        JSONObject result = IPSRSACryptoUtil.genReqData(merchantID, operationType, req);
        System.out.println(result);
        // begin to send request
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map map = new HashMap<String, String>();
        map.put("Content-Type", "application/x-www-form-urlencoded");

        headers.setAll(map);

        //使用RestTemplate向MPS发送消息时, 必须使用下面的写法。
        //MPS强制的提交方式是： POST， 表单， 参数提交方式
        MultiValueMap<String, String> req_form  = new LinkedMultiValueMap<>();
        req_form.add(IPSCONSTANTS.SIGN_PARAM_merchantID, result.getString(IPSCONSTANTS.SIGN_PARAM_merchantID));
        req_form.add(IPSCONSTANTS.SIGN_PARAM_operationType, result.getString(IPSCONSTANTS.SIGN_PARAM_operationType));
        req_form.add(IPSCONSTANTS.SIGN_PARAM_REQUEST, result.getString(IPSCONSTANTS.SIGN_PARAM_REQUEST));
        req_form.add(IPSCONSTANTS.SIGN_PARAM_SIGN, result.getString(IPSCONSTANTS.SIGN_PARAM_SIGN));

        HttpEntity<?> request = new HttpEntity<>(req_form, headers);

        String url= "http://113.207.54.122:8011/p2p-dep/gateway.htm";

        IpsResponse t = httpManager.restTemplate().postForObject(ModelParam.TestServerAdderss, request, IpsResponse.class);
        System.out.println(t);
    }
}
