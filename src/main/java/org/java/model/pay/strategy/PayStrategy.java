package org.java.model.pay.strategy;

import org.java.model.pay.common.PayChannel;
import org.java.model.pay.common.PayDTO;

/**
 * 支付接口
 *
 * @author zpp
 */
public interface PayStrategy {

    /**
     * 获取支付表单
     * @param payChannel
     * @param dto
     * @return
     */
    String getPayHtml(PayChannel payChannel, PayDTO dto);
}
