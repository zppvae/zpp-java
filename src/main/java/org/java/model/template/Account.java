package org.java.model.template;

/**
 * 账户
 * Description:
 * @author zpp
 * @date   2018年7月10日
 */
public abstract class Account {
    /**
     * 为防止恶意操作，一般模板方法都加上 final 关键词
     *
     *
     * 模板方法，计算利息数额
     * @return    返回利息数额
     */
    public final double calculateInterest(){
        double interestRate = doCalculateInterestRate();
        String accountType = doCalculateAccountType();
        double amount = calculateAmount(accountType);
        return amount * interestRate;
    }
    /**
     * 基本方法留给子类实现
     * 
     * 账户类型
     * 
     * 钩子方法(一般以do开始)
     */
    protected abstract String doCalculateAccountType();
    
    /**
     * 基本方法留给子类实现
     * 
     * 账户的利息百分比
     */
    protected abstract double doCalculateInterestRate();
    /**
     * 基本方法，已经实现
     */
    private double calculateAmount(String accountType){
        /**
         * 省略相关的业务逻辑
         */
        return 7243.00;
    }
}