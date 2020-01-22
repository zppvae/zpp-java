package org.java.model.template;

/**
 * 定期存款账户
 * Description:
 * @author zpp
 * @date   2018年7月10日
 */
public class CDAccount extends Account {

    @Override
    protected String doCalculateAccountType() {
        return "Certificate of Deposite";
    }

    @Override
    protected double doCalculateInterestRate() {
        return 0.06;
    }

}