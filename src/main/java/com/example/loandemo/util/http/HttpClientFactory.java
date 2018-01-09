package com.example.loandemo.util.http;


import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * 用于创建HttpClient，非线程安全。依赖httpclient-4.3
 *
 * @author dhf
 */
public class HttpClientFactory {
    private static final char[] KEY_STORE_PASSWORD = "changeit".toCharArray();

    private int requestTimeout = 50000;

    private int connectTimeout = 50000;

    private int poolSize = 200;

    private int maxPerRoute = 10;

    private boolean allowCircularRedirect = false;

    private SSLContext sslContext;

    /**
     * 根据已设定的参数，生成httpclient。请查看所有setter方法的注释以了解默认值
     */
    public CloseableHttpClient create() {
        RequestConfig reqConf = RequestConfig.custom()
                .setCircularRedirectsAllowed(allowCircularRedirect)
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(requestTimeout)
                .setSocketTimeout(requestTimeout).build();

        if (null == sslContext) {
            try {
                sslContext = SSLContexts.custom()
                        .loadTrustMaterial(null, TrustAnyStrategy.get()).build();
            } catch (Throwable t) {
                throw new RuntimeException(t);
            }
        }
        return HttpClients.custom().setMaxConnPerRoute(maxPerRoute)
                .setMaxConnTotal(poolSize).disableAuthCaching()
                .disableAutomaticRetries().disableCookieManagement()
                .setDefaultRequestConfig(reqConf).setSslcontext(sslContext)
                .setSSLSocketFactory(new SSLConnectionSocketFactory(sslContext,
                        SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER))
                .build();
    }

    /**
     * 根据已设定的参数，生成httpclient。请查看所有setter方法的注释以了解默认值
     *
     * @param keystore
     *            证书
     * @param keyPassword
     *            证书密码
     * @return
     */
    public CloseableHttpClient create(KeyStore keystore, char[] keyPassword) {
        RequestConfig reqConf = RequestConfig.custom()
                .setCircularRedirectsAllowed(allowCircularRedirect)
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(requestTimeout)
                .setSocketTimeout(requestTimeout).build();

        try {
            setKeystore(keystore, keyPassword);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
        return HttpClients.custom().setMaxConnPerRoute(maxPerRoute)
                .setMaxConnTotal(poolSize).disableAuthCaching()
                .disableAutomaticRetries().disableCookieManagement()
                .setDefaultRequestConfig(reqConf).setSslcontext(sslContext)
                .setSSLSocketFactory(new SSLConnectionSocketFactory(sslContext,
                        new String[] { "TLSv1" }, null,
                        SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER))
                .build();
    }

    /**
     * 是否允许循环重定向。默认不允许
     *
     * @param allowCircularRedirect
     */
    public void setAllowCircularRedirect(boolean allowCircularRedirect) {
        this.allowCircularRedirect = allowCircularRedirect;
    }

    /**
     * http连接超时，单位毫秒。默认5000毫秒
     *
     * @param connectTimeout
     */
    public void setConnectTimeout(long connectTimeout) {
        this.connectTimeout = (int) connectTimeout;
    }

    /**
     * http请求超时时间，单位毫秒。默认5000毫秒
     *
     * @param requestTimeout
     */
    public void setRequestTimeout(long requestTimeout) {
        this.requestTimeout = (int) requestTimeout;
    }

    /**
     * 设置SSL连接的上下文（证书校验）。默认不校验，信任所有证书
     *
     * @param sslContext
     */
    public void setSslContext(SSLContext sslContext) {
        this.sslContext = sslContext;
    }

    /**
     * 设置可信SSL证书的keystore
     *
     * @param keystore
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     * @throws UnrecoverableKeyException
     */
    public void setKeystore(KeyStore keystore) throws KeyManagementException,
            NoSuchAlgorithmException, KeyStoreException {
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(keystore)
                .build();
        setSslContext(sslContext);
    }

    /**
     * 设置可信SSL证书的keystore
     *
     * @param keystore
     * @param keyPassword
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     * @throws UnrecoverableKeyException
     */
    public void setKeystore(KeyStore keystore, char[] keyPassword)
            throws KeyManagementException, NoSuchAlgorithmException,
            KeyStoreException, UnrecoverableKeyException {
        SSLContext sslContext = SSLContexts.custom()
                .loadKeyMaterial(keystore, keyPassword).build();
        setSslContext(sslContext);
    }

    /**
     * 设置可信SSL证书的keystore
     *
     * @param keyStoreIn
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws CertificateException
     * @throws IOException
     * @throws KeyManagementException
     * @throws UnrecoverableKeyException
     */
    public void setKeystore(InputStream keyStoreIn) throws KeyStoreException,
            NoSuchAlgorithmException, CertificateException, IOException,
            KeyManagementException, UnrecoverableKeyException {
        setKeystore(keyStoreIn, null);
    }

    /**
     * 设置可信SSL证书的keystore
     *
     * @param keyStoreIn
     * @param password
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws CertificateException
     * @throws IOException
     * @throws KeyManagementException
     * @throws UnrecoverableKeyException
     */
    public void setKeystore(InputStream keyStoreIn, char[] password)
            throws KeyStoreException, NoSuchAlgorithmException,
            CertificateException, IOException, KeyManagementException,
            UnrecoverableKeyException {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        if (null == password) {
            password = KEY_STORE_PASSWORD;
        }
        keyStore.load(keyStoreIn, password);
        setKeystore(keyStore);
    }

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    public int getMaxPerRoute() {
        return maxPerRoute;
    }

    public void setMaxPerRoute(int maxPerRoute) {
        this.maxPerRoute = maxPerRoute;
    }

}
