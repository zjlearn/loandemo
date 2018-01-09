package com.example.loandemo.util;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

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
     * 参考：
     * @param publicKey
     * @param message
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(PublicKey publicKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(message.getBytes());
    }

    public static byte[] decrypt(PrivateKey privateKey, byte [] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return cipher.doFinal(encrypted);
    }

    public static void main(String [] args) throws Exception {
        // generate public and private keys
        KeyPair keyPair = buildKeyPair();
        PublicKey pubKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // encrypt the message
        byte [] encrypted = encrypt(pubKey, "This is a secret message");
        System.out.println(new String(encrypted));  // <<encrypted message>>

        // decrypt the message
        byte[] secret = decrypt(privateKey, encrypted);
        System.out.println(new String(secret));     // This is a secret message
    }

    /**
     * 根据私钥和data 得到签名
     * @param privateKey
     * @param data
     * @return
     * 参考：https://www.cnblogs.com/chengxuyuanzhilu/p/5194306.html
     */
    public static byte[] signSHA256(PrivateKey privateKey, byte[] data) throws Exception {
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(privateKey);
        signature.update(data);
        byte[] result = signature.sign();
        return result;
    }

    /**
     * 根据公钥，数据， 以及sign进行验证
     * @param publicKey
     * @param data
     * @param sign
     * @return
     */
    public static boolean verifySHA256(PublicKey publicKey, byte[] data, byte[] sign) throws Exception{
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(publicKey);
        signature.update(data);
        boolean bool = signature.verify(sign);
        return bool;
    }

}
