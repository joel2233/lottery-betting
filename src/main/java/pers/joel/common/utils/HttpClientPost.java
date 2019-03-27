package pers.joel.common.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;

import javax.net.ssl.SSLContext;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class HttpClientPost
{

    public HttpClientPost()
    {

    }

    // 返回結果
    String strHttpResult = null;

    /**
     * 发送Http请求到Web站点
     *
     * @param path
     *          Web站点请求地址
     * @param map
     *          Http请求参数
     * @param encode
     *          编码格式
     * @return string, Web站点响应的字符串
     *
     */
    public HttpClientPost(final String strURL, String messageText, final String encode)
    {
        strHttpResult = null;
        HttpClient httpclient = null;
        try {
            // 创建安全套接字对象
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (chain, authType) -> true).build();
            // 获取分层tls/ssl连接
            SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(sslContext,
                    new String[]{"TLSv1", "TLSv1.1", "TLSv1.2"},
                    null,
                    SSLConnectionSocketFactory.getDefaultHostnameVerifier());
            httpclient =  HttpClients.custom().setSSLSocketFactory(sf).build();
        } catch (KeyManagementException e) {
            System.out.println("connect to liberty caused KeyManagementException!" + e);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("connect to liberty caused NoSuchAlgorithmException!" + e);
        } catch (KeyStoreException e) {
            System.out.println("connect to liberty caused KeyStoreException!");
        }
        HttpPost httppost = new HttpPost(strURL);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(120000)
                .setConnectTimeout(30000)
                .setConnectionRequestTimeout(30000)
                .build();
        httppost.setConfig(requestConfig);
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        StringEntity entitys = null;


        List param=new ArrayList();
        param.add(new BasicNameValuePair("username", "admin"));
        param.add(new BasicNameValuePair("password", "Biyesheji@2019"));
        try {
            entitys = new UrlEncodedFormEntity(param, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        httppost.setEntity(entitys);

        try
        {
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                InputStream instream = entity.getContent();
                try
                {
                    // stream to string
                    String strResult = InputStream2String(instream);
                    strHttpResult = strResult;
                }
                finally
                {
                    instream.close();
                }
            }
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (ClientProtocolException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * get string from stream
     *
     * @param is
     * @return
     * @throws IOException
     */
    private String InputStream2String(InputStream inputStream)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int i = -1;
        String result = null;

        try
        {
            while ((i = inputStream.read()) != -1)
            {
                outputStream.write(i);
            }

            result = outputStream.toString();
            // result = new String(outputStream.toByteArray(), "gbk");

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 返回 http 结果
     *
     * @return
     */
    public String getHttpResultString()
    {
        return strHttpResult;
    }
    /**
     * 进行加密算法，主要是md5
     */
    public static String encodeString(String str, String method) {
        if (str == null)
            return null;

        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance(method);
            md.update(str.getBytes("utf-8"));
            // digest()最后确定返回md5 hash值，返回值为8为字符串。
            // 因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            // dstr = new BigInteger(1, md.digest()).toString(16);
            return byteArrayToHexString(md.digest());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static String byteArrayToHexString(byte[] b) {
        String result = "";
        for (byte aB : b) {
            result += Integer.toString((aB & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }
}
