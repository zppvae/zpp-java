package org.java.util;

import org.apache.commons.codec.binary.Base64;

/**
 * @author zpp
 * @date 2018/10/24 11:54
 */
public class Base64Utils {

    /**
     * 编码
     * @param str
     * @return
     */
    public static String encodeString(String str) throws Exception{
        return Base64.encodeBase64String(str.getBytes("UTF-8"));
    }

    /**
     * 解码
     * @param str
     * @return
     */
    public static String decodeString(String str) throws Exception{
        return new String(Base64.decodeBase64(str),"UTF-8");
    }
}
