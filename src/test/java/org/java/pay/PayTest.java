package org.java.pay;

import lombok.extern.slf4j.Slf4j;
import org.java.model.pay.strategy.PayStrategy;
import org.java.model.pay.strategy.StrategyFactory;
import org.junit.Test;

@Slf4j
public class PayTest {

    @Test
    public void test(){
        PayStrategy payStrategy = StrategyFactory.getPayStrategy("org.java.model.pay.strategy.AliPayStrategy");
        log.info(payStrategy.getPayHtml(null,null));
    }
}
