package com.example.loandemo.controller.IPS;

import com.alibaba.fastjson.JSON;
import com.example.loandemo.model.IpsResponse;
import com.example.loandemo.model.ModelParam;
import com.example.loandemo.model.dto.FreezeDTO;
import com.example.loandemo.model.view.Result;
import com.example.loandemo.model.view.ResultGenerator;
import com.example.loandemo.util.IPS.IPSHttpUtil;
import com.example.loandemo.util.IPSOperationTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by zhangjun1 on 2018/1/14
 */
@RestController
@RequestMapping("/xhr")
public class TradeFreezeController {
    @Autowired
    IPSHttpUtil ipsHttpUtil;

    @RequestMapping("/tradeFreeze")
    public Result tradeFreeze(@RequestBody FreezeDTO freezeDTO) {
        IpsResponse result = ipsHttpUtil.post(ModelParam.merchantID, IPSOperationTypeEnum.TRADE_FREEZE.getName(),
                JSON.toJSONString(freezeDTO));
        return ResultGenerator.genSuccessResult();
    }
}
