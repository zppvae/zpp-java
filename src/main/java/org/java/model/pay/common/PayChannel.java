package org.java.model.pay.common;

import lombok.Data;

/**
 * 支付通道，支付宝、微信、银联
 *
 * @author zpp
 */
@Data
public class PayChannel {

    private int channelId;

    private String channelName;
}
