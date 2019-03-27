package pers.joel.common.utils;

import com.zhenzi.sms.ZhenziSmsClient;

public class SmsUtils {

    private static String apiUrl = "https://sms_developer.zhenzikj.com";
    private static String appId = "101013";
    private static String appSecret = "4ec673f3-52c5-4b04-a0fb-acc6be863952";

    public static String sendMessage(String phone,String content){
        String result = null;
        try{

            ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
            result = client.send(phone, content);

        }catch (Exception e){
           e.printStackTrace();
//           return Error;
        }

        return result;
    }
}
