package pers.joel.newsCenter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.joel.common.controller.BaseController;
import pers.joel.common.services.NewsManager;
import pers.joel.common.utils.HttpUtil;
import pers.joel.common.utils.JSONUtil;
import pers.joel.newsCenter.models.ApiChannel;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/news")
public class NewsController extends BaseController {
    protected transient final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private NewsManager newsManager;

    @RequestMapping({"/footNews","basketNews"})
    public String footNews(){
        String code = getRequest().getParameter("id");
        if(code == null)
            code="f1";
        try {
            ApiChannel apiChannel = newsManager.getChannelByCode(code);
            if(apiChannel == null){
               saveErrorMessage("系统错误，请稍后重试!");
               return ERROR;
            }
            String url = apiChannel.getUrl();
            String result = HttpUtil.getResponseData(url);
            if(JSONUtil.readJson2Map(result).get("code").toString().equals("200")){
                getRequest().setAttribute("itemlist", JSONUtil.readJson2Map(result).get("data"));
            }else {
                getRequest().setAttribute("itemlist", "获取数据失败，请重试");
            }
        } catch (Exception e) {
            saveErrorMessage("系统错误，请稍后重试!");
            log.error("获取数据异常");
            return ERROR;
        }
        return "/news/newsinfo" +code;
    }

    @RequestMapping("/getNewsInfo")
    @ResponseBody
    public Object getNewsInfo(){
        Map<String,Object> map = new HashMap<>();
        map.put("code","1");

        String url = getRequest().getParameter("url");
        try {
            String result = HttpUtil.getResponseData(url);
            if(JSONUtil.readJson2Map(result).get("code").toString().equals("200")){
                map.put("code","0");
                map.put("msg","");
                map.put("count",JSONUtil.readJson2Map(result).size());
                map.put("data", JSONUtil.readJson2Map(result).get("data"));
            }else {
                map.put("msg","");
                map.put("count",0);
                map.put("data", "");
            }

        } catch (Exception e) {
            map.put("msg","获取数据异常");
            log.error("获取数据异常");
        }

        return map;
    }
}
