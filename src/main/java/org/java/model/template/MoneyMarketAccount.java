package org.java.model.template;

/**
 * 货币市场账户
 * Description:
 * @author zpp
 * @date   2018年7月10日
 */
public class MoneyMarketAccount extends Account {

    @Override
    protected String doCalculateAccountType() {
        
        return "Money Market";
    }

    @Override
    protected double doCalculateInterestRate() {
        
        return 0.045;
    }

}