package pers.joel.common.utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SecurityUtil {
    public static String  AESPASSWORD = "3531";
    public static final String AES_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * AES加密
     * @param content 需要被加密的字符串
     * @param password 加密需要的密码
     * @return 密文
     * @throws Exception
     * @created by ONO on 2016-07-22
     */
    public static String aesEncrypt(String content, String password) {

        try {
            //创建AES的Key生成器
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            //手动指定SecureRandom随机数生成规则
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());
            //利用用户密码作为随机数初始化Key生成器
            keyGenerator.init(128, random);
            /*加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，
            只要种子相同，序列就一样，所以解密只要有password就行*/
            //根据用户密码生成一个密钥
            SecretKey secretKey = keyGenerator.generateKey();
            // 返回基本编码格式的密钥，如果此密钥不支持编码，则返回null
            byte[] enCodeFormat = secretKey.getEncoded();
            // 转换为AES专用密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            //创建密码器
            Cipher cipher =Cipher.getInstance(AES_ALGORITHM);
            byte[] byteContent = content.getBytes("utf-8");
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //加密
            byte[] result = cipher.doFinal(byteContent);
            return (parseByte2HexStr(result));
        }
        catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密AES加密过的字符串
     * @param content AES加密过过的内容
     * @param password 加密时的密码
     * @return 明文
     * @throws Exception
     * @author       : gaoying
     * @update       :
     * @date         : 2015-7-27
     */
    public static String aesDecrypt(String content, String password) {

        //把16进制的密文转化为二进制的密文
        byte[] encrypt = parseHexStr2Byte(content);

        try {
            // 创建AES的Key生产者
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            //手动指定SecureRandom随机数生成规则
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());
            //利用用户密码作为随机数初始化Key生成器
            keyGenerator.init(128, random);
            // 根据用户密码，生成一个密钥
            SecretKey secretKey = keyGenerator.generateKey();
            // 返回基本编码格式的密钥
            byte[] enCodeFormat = secretKey.getEncoded();
            // 转换为AES专用密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            // 创建密码器
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            // 初始化为解密模式的密码器
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(encrypt );
            // 明文
            return (new String(result));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 二进制转化为16进制
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
