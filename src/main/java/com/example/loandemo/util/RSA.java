package com.example.loandemo.util;

import org.apache.commons.lang3.ArrayUtils;

import javax.crypto.Cipher;
import java.security.*;

/**
 * create by zhangjun1 on 2018/1/9
 */
public class RSA {

    public static KeyPair buildKeyPair() throws NoSuchAlgorithmException {
        final int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);
        return keyPairGenerator.genKeyPair();
    }

    /**
     * 加密算法，
     * 参考： http://zhuyuehua.iteye.com/blog/1112722

     * @param publicKey
     * @param message
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(PublicKey publicKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        // 加密时超过117字节就报错。为此采用分段加密的办法来加密
        byte[] enBytes = null;
        byte [] data = message.getBytes(CONSTANTS.UTF_8_ENCODING);
        for (int i = 0; i < data.length; i += 64) {
        // 注意要使用2的倍数，否则会出现加密后的内容再解密时为乱码
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i, i + 64));
            enBytes = ArrayUtils.addAll(enBytes, doFinal);
        }

        return enBytes;
    }

    public static byte[] decrypt(PrivateKey privateKey, byte[] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return cipher.doFinal(encrypted);
    }

    public static void main(String[] args) throws Exception {
        // generate public and private keys
        KeyPair keyPair = buildKeyPair();
        PublicKey pubKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // encrypt the message
        byte[] encrypted = encrypt(pubKey, "This is a secret message");
        System.out.println(new String(encrypted));  // <<encrypted message>>

        // decrypt the message
        byte[] secret = decrypt(privateKey, encrypted);
        System.out.println(new String(secret));     // This is a secret message
    }

    /**
     * 根据私钥和data 得到签名
     *
     * @param privateKey
     * @param data
     * @return 参考：https://www.cnblogs.com/chengxuyuanzhilu/p/5194306.html
     */
    public static byte[] signSHA256(PrivateKey privateKey, byte[] data) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data);
        byte[] result = signature.sign();
        return result;
    }

    /**
     * 根据公钥，数据， 以及sign进行验证
     *
     * @param publicKey
     * @param data
     * @param sign
     * @return
     */
    public static boolean verifySHA256(PublicKey publicKey, byte[] data, byte[] sign) throws Exception {
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(publicKey);
        signature.update(data);
        boolean bool = signature.verify(sign);
        return bool;
    }

}
