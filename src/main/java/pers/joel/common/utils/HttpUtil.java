package pers.joel.common.utils;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class HttpUtil {
    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

    private static String username = "admin";
    private static String password = "Biyesheji@2019";

    // client工具函数，信任对方（https）所有证书
    public static CloseableHttpClient createSSLInsecureClient() {
        try {
            // 创建安全套接字对象
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (chain, authType) -> true).build();
            // 获取分层tls/ssl连接
            SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(sslContext,
                    new String[]{"TLSv1", "TLSv1.1", "TLSv1.2"},
                    null,
                    SSLConnectionSocketFactory.getDefaultHostnameVerifier());
            return HttpClients.custom().setSSLSocketFactory(sf).build();
        } catch (KeyManagementException e) {
            log.error("connect caused KeyManagementException!" + e);
        } catch (NoSuchAlgorithmException e) {
            log.error("connect caused NoSuchAlgorithmException!" + e);
        } catch (KeyStoreException e) {
            log.error("connect caused KeyStoreException!");
        }
        return HttpClients.createDefault();
    }

    public static String getResponseData(String url) throws Exception{
        log.info("请求url:{}",url);
        String result = "";
        JSONObject obj = new JSONObject();
        obj.put("param", "paramValue");
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(username, password));
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .build();
        HttpGet get = new HttpGet(url);
//        StringEntity entity = new StringEntity(obj.toString(),"utf-8");
//        entity.setContentEncoding("UTF-8");
//        entity.setContentType("application/json");
//        get.setHeader(entity);
        try {
            CloseableHttpResponse response = httpClient.execute(get);
            try {
                HttpEntity httpEntity = response.getEntity();//获取返回内容
                int statusCode = response.getStatusLine().getStatusCode();
                log.warn("statusCode:{}",statusCode);
                if (statusCode != 200)
                    throw new Exception("连接不上服务器");
                result = (httpEntity != null) ? EntityUtils.toString(httpEntity) : null;//获取结果
                log.debug("返回字符串:"+result);
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            log.warn("HttpException", e);
            throw e;
        } catch (IOException e) {
            log.warn("IOException", e);
            throw e;
        } finally {
            httpClient.close();
        }
        return result;
    }
}
