package org.java.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;


@Slf4j
public class RSAUtil {

    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "SHA1WithRSA";
    public static final String PUBLIC_KEY = "publicKey";
    public static final String PRIVATE_KEY = "privateKey";

    //map对象中存放公私钥
    public static Map<String, Key> initKey() throws Exception {
        //获得对象 KeyPairGenerator 参数 RSA 1024个字节
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        //通过对象 KeyPairGenerator 获取对象KeyPair
        KeyPair keyPair = keyPairGen.generateKeyPair();
        //通过对象 KeyPair 获取RSA公私钥对象RSAPublicKey RSAPrivateKey
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        //公私钥对象存入map中
        Map<String, Key> keyMap = new HashMap<>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * 从公钥字符串中获取公钥
     *
     * @param key Base64的公钥字符串
     * @return 公钥
     * @throws Exception 异常
     */
    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 从私钥字符串中获取私钥
     *
     * @param key Base64的私钥字符串
     * @return 私钥
     * @throws Exception 异常
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(key.getBytes(StandardCharsets.UTF_8));
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * RSA私钥签名
     *
     * @param content    待签名数据
     * @param privateKey 私钥
     * @return 签名值
     */
    public static String signByPrivateKey(String content, String privateKey) {
        try {
            PrivateKey priKey = getPrivateKey(privateKey);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(priKey);
            signature.update(content.getBytes(StandardCharsets.UTF_8));
            byte[] signed = signature.sign();
            return new String(Base64.encodeBase64URLSafe(signed), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.warn("sign error, content: {}, priKey: {}", content, privateKey);
            log.error("sign error, message is {}", e.getMessage());
        }
        return null;
    }

    /**
     * 通过公钥验签
     *
     * @param content   验签内容
     * @param sign      签名
     * @param publicKey 公钥
     * @return 验签结果
     */
    public static boolean verifySignByPublicKey(String content, String sign, String publicKey) {
        try {
            PublicKey pubKey = getPublicKey(publicKey);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(pubKey);
            signature.update(content.getBytes(StandardCharsets.UTF_8));
            return signature.verify(Base64.decodeBase64(sign.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            log.warn("sign error, content: {}, sign: {}, pubKey: {}", content, sign, publicKey);
            log.error("sign error", e);
        }
        return false;
    }

    public static void main(String[] args) {
        String signContent = "待签名数据";
        // 第一步，生成一对公私钥
        Map<String, Key> keys = new HashMap<>(2);
        try {
            keys = RSAUtil.initKey();
        } catch (Exception e) {
            log.error("init RSA key error，message is {}", e.getMessage());
            System.exit(-1);
        }
        // 获得私钥
        Key privateKey = keys.get(PRIVATE_KEY);
        // 私钥Base64编码字符串
        String base64PrivateKeyStr = Base64.encodeBase64String(privateKey.getEncoded());
        log.info("base64 privateKey String is:{}", base64PrivateKeyStr);
        // 签名
        String signString = RSAUtil.signByPrivateKey(signContent, base64PrivateKeyStr);
        log.info("sign by privateKey ,signString is: {}", signString);
        // 获得公钥
        Key publicKey = keys.get(PUBLIC_KEY);
        String base64PublicKeyStr = Base64.encodeBase64String(publicKey.getEncoded());
        log.info("base64 publicKey String is:{}", base64PublicKeyStr);
        boolean verifySignResult = RSAUtil.verifySignByPublicKey(signContent, signString, base64PublicKeyStr);
        log.info("verify sign result is: {}", verifySignResult);
    }
}


