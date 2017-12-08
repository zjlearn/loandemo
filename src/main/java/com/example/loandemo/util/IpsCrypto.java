package com.example.loandemo.util;

/**
 * create by zhangjun1 on 2017/12/5
 */
import java.io.StringReader;
import java.security.Key;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMReader;
import org.bouncycastle.openssl.PasswordFinder;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class IpsCrypto {
    private static final String MD5_ALGORITHM = "MD5";
    private static final String DES_ALGORITHM = "DESede";
    private static final String DES_ALGORITHM_MODE = "/CBC/PKCS5Padding";
    private static final String CHARSET_NAME = "UTF-8";
    private static final String MD5WITHRSA_ALGORITHM = "MD5withRSA";
    private static final String MD5WITHRSA_KEY_PASSWORD = "xinxin is pig";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public IpsCrypto() {
    }

    public static String md5Sign(String input) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(input.getBytes("UTF-8"));
            byte[] byteArray = messageDigest.digest();
            StringBuffer md5StrBuff = new StringBuffer();

            for(int i = 0; i < byteArray.length; ++i) {
                if (Integer.toHexString(255 & byteArray[i]).length() == 1) {
                    md5StrBuff.append("0").append(Integer.toHexString(255 & byteArray[i]));
                } else {
                    md5StrBuff.append(Integer.toHexString(255 & byteArray[i]));
                }
            }

            return md5StrBuff.toString();
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public static String triDesEncrypt(String input, String desKey, String desIv) {
        Cipher cipher = null;

        try {
            SecureRandom sr = new SecureRandom();
            DESedeKeySpec dks = new DESedeKeySpec(desKey.getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            Key key = keyFactory.generateSecret(dks);
            IvParameterSpec iv = new IvParameterSpec(desIv.getBytes("UTF-8"));
            cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            cipher.init(1, key, iv, sr);
            byte[] array = cipher.doFinal(input.getBytes("UTF-8"));
            return (new BASE64Encoder()).encode(array);
        } catch (Exception var10) {
            var10.printStackTrace();
            return null;
        }
    }

    public static String triDesDecrypt(String input, String desKey, String desIv) {
        Cipher cipher = null;

        try {
            SecureRandom sr = new SecureRandom();
            DESedeKeySpec dks = new DESedeKeySpec(desKey.getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey key = keyFactory.generateSecret(dks);
            IvParameterSpec iv = new IvParameterSpec(desIv.getBytes("UTF-8"));
            cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            cipher.init(2, key, iv, sr);
            byte[] array = cipher.doFinal((new BASE64Decoder()).decodeBuffer(input));
            return new String(array, "UTF-8");
        } catch (Exception var10) {
            var10.printStackTrace();
            return null;
        }
    }

    public static String md5WithRSASign(String input, String priKey) {
        try {
            PEMReader reader = new PEMReader(new StringReader(priKey), new PasswordFinder() {
                public char[] getPassword() {
                    return "xinxin is pig".toCharArray();
                }
            });
            KeyPair pair = (KeyPair)reader.readObject();
            PrivateKey key = pair.getPrivate();
            Signature sign = Signature.getInstance("MD5withRSA");
            sign.initSign(key);
            sign.update(input.getBytes("UTF-8"));
            return Hex.encodeHexString(sign.sign());
        } catch (Exception var6) {
            var6.printStackTrace();
            return null;
        }
    }

    public static boolean md5WithRSAVerify(String plainText, String signedText, String pubKey) {
        try {
            PEMReader reader = new PEMReader(new StringReader(pubKey));
            PublicKey key = (PublicKey)reader.readObject();
            Signature sign = Signature.getInstance("MD5withRSA");
            sign.initVerify(key);
            sign.update(plainText.getBytes("UTF-8"));
            return sign.verify(Hex.decodeHex(signedText.toCharArray()));
        } catch (Exception var6) {
            var6.printStackTrace();
            return false;
        }
    }
}
