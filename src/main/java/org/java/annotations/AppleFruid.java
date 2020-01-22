package org.java.annotations;

/**
 * @author zpp
 * @date 2020/1/2 17:52
 */
public class AppleFruid {

    @FruidProvider(id = 1,name = "北京供应商")
    public String appleDesc;

    public String getAppleDesc() {
        return appleDesc;
    }

    public void setAppleDesc(String appleDesc) {
        this.appleDesc = appleDesc;
    }
}
