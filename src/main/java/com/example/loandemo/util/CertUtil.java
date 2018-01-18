package com.example.loandemo.util;
import org.apache.commons.io.FileUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

/**
 * create by zhangjun1 on 2018/1/9
 */
public class CertUtil {

    public static  String publickeyFile = "D:\\home\\project_manman\\rsa\\bank_publicKey.cer";
    public static  String privatekeyFile ="D:\\home\\project_manman\\rsa\\mer_privateKey.pfx";
    /**
     * 获取银行公钥
     */
    public static  PublicKey getBankCa(byte[] bytes) throws Exception {
        CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            X509Certificate Cert = (X509Certificate) certificatefactory.generateCertificate(bais);
            return Cert.getPublicKey();
        } finally {
            if (bais != null) {
                bais.close();
            }
        }
    }

    /**
     * 获取商户的私钥
     */
    public static PrivateKey getMerCa(byte[] bytes) throws Exception {
        ByteArrayInputStream fis = new ByteArrayInputStream(bytes);
        String strPassword = "sft12#";//密码
        char[] nPassword = strPassword.toCharArray();
        PrivateKey privateKey = null;
        try {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            fis = new ByteArrayInputStream(bytes);
            ks.load(fis, nPassword);
            Enumeration<?> enumas = ks.aliases();
            String keyAlias = null;
            if (enumas.hasMoreElements()) {
                keyAlias = (String) enumas.nextElement();
            }
            privateKey = (PrivateKey) ks.getKey(keyAlias, nPassword);
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        return privateKey;
    }

    /**
     * fixme
     * @return
     * @throws Exception
     */
    public static PublicKey getEncryptCertPublicKey() throws Exception {
        File file = new File(publickeyFile);
        byte [] data = FileUtils.readFileToByteArray(file);
        return getBankCa(data);
    }

    public static PrivateKey getSignCertPrivateKey() throws Exception {
        File file = new File(privatekeyFile);
        byte [] data = FileUtils.readFileToByteArray(file);
        return getMerCa(data);
    }
}
