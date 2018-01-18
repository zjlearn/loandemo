package com.example.loandemo.controller.IPS;

import com.alibaba.fastjson.JSONObject;
import com.example.loandemo.model.dto.UserRegisterDTO;
import com.example.loandemo.util.IPSOperationTypeEnum;
import com.example.loandemo.util.IPSRSACryptoUtil;
import com.example.loandemo.model.ModelParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * create by zhangjun1 on 2018/1/14
 * 负责用户开户的controller
 */
@RestController
@RequestMapping("/xhr/userRegister")
public class UserRegisterController {

    @RequestMapping("sign")
    public JSONObject sign(@RequestParam("realName") String realName,
                           @RequestParam("idCard") String idCard){
        String Id = UUID.randomUUID().toString();
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setMerBillNo("23235413");
        userRegisterDTO.setMerDate("2018-01-10");
        userRegisterDTO.setUserName("zj");
        userRegisterDTO.setUserType("1");
        userRegisterDTO.setUserRole("1");
        userRegisterDTO.setRealName(realName);
        userRegisterDTO.setMobileNo("13016413998");
        userRegisterDTO.setBizType("1");
        userRegisterDTO.setEnterName("");
        userRegisterDTO.setIsAssureCom("1");
        userRegisterDTO.setWebUrl("http://127.0.0.1:8080/p2p-dep/test/p2pweb.html");
        userRegisterDTO.setS2SUrl("http://127.0.0.1:8080/p2p-dep/test/p2pweb.html");
        userRegisterDTO.setIdentNo(idCard);
        String reqStr= JSONObject.toJSONString(userRegisterDTO);
        JSONObject result =  IPSRSACryptoUtil.genReqData(ModelParam.merchantID, IPSOperationTypeEnum.USER_REGISTER.getName(), reqStr);
        System.out.println(result);
        return new JSONObject();
    }

    @RequestMapping("notice")
    public JSONObject notice(){
        return new JSONObject();
    }
}
