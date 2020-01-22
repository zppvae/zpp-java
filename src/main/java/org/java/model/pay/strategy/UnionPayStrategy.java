package org.java.model.pay.strategy;

import lombok.extern.slf4j.Slf4j;
import org.java.model.pay.common.PayChannel;
import org.java.model.pay.common.PayDTO;

@Slf4j
public class UnionPayStrategy implements PayStrategy {

	@Override
	public String getPayHtml(PayChannel payChannel, PayDTO dto){
		//银联支付业务处理
		return "银联支付from表单html";
	}

}
