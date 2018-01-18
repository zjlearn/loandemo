package com.example.loandemo.util;

/**
 * create by zhangjun1 on 2018/1/14
 */
public enum  IPSOperationTypeEnum {

    USER_REGISTER("user.register","用户开户"),
    USER_CLOSEACCOUNT("user.closeAccount", "用户关闭账户"),
    PROJECT_REGPROJECT("project.regProject", "项目登记"),
    PROJECT_MODIFYPROJECT("project.modifyProject", "项目信息变更"),
    PROJECT_ASSUREPROJECT("project.assureProject", "项目追加登记"),
    TRADE_COMBFREEZE("trade.combFreeze","红包组合冻结接口"),
    TRADE_FREEZE("trade.freeze","冻结接口"),
    TRADE_UNFREEZE("trade.unFreeze","解冻接口"),
    TRADE_DEPOSIT("trade.deposit","充值接口"),
    TRADE_WITHDRAW("trade.withdraw","提现接口"),
    TRADE_TRANSFER("trade.transfer","转账接口"),
    USER_AUTOSIGN("user.autoSign","自动签约接口"),
    QUERY_COMMONQUERY("query.commonQuery","自动查询接口"),
    QUERY_DOWNLOAD("query.download", "对账下载接口");


    private final String name;
    private final String desc;

     IPSOperationTypeEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
