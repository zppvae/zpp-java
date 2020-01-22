package org.java.model.pay.strategy;

import lombok.extern.slf4j.Slf4j;
import org.java.model.pay.common.PayChannel;
import org.java.model.pay.common.PayDTO;

@Slf4j
public class AliPayStrategy implements PayStrategy {

    @Override
    public String getPayHtml(PayChannel payChannel, PayDTO dto){
        //支付宝支付业务处理
        return "支付宝支付from表单html";
    }
}
