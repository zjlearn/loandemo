package com.example.loandemo.controller.IPS;

import com.alibaba.fastjson.JSON;
import com.example.loandemo.model.dto.AssureProjectDTO;
import com.example.loandemo.model.view.Result;
import com.example.loandemo.model.view.ResultGenerator;
import com.example.loandemo.util.IPS.IPSHttpUtil;
import com.example.loandemo.util.IPSOperationTypeEnum;
import com.example.loandemo.model.IpsResponse;
import com.example.loandemo.model.ModelParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * create by zhangjun1 on 2018/1/14
 * 后台查询接口
 */
@RestController
@RequestMapping("/xhr")
public class AssureProjectController {
    @Autowired
    IPSHttpUtil ipsHttpUtil;
    @RequestMapping("/assureProject")
    public Result assureProject(@RequestBody AssureProjectDTO assureProjectDTO){
        IpsResponse result = ipsHttpUtil.post(ModelParam.merchantID, IPSOperationTypeEnum.PROJECT_ASSUREPROJECT.getName(),
                JSON.toJSONString(assureProjectDTO));
        return ResultGenerator.genSuccessResult();
    }
}
