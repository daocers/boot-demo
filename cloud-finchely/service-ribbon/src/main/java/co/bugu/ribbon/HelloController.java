package co.bugu.ribbon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author daocers
 * @Date 2019/1/9:10:34
 * @Description:
 */
@RestController
public class HelloController {
    private Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    HelloService helloService;

    @Autowired
    RestTemplate template;


    @GetMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return helloService.hiService(name);
    }


    @GetMapping(value = "/getVideo")
    public String getVideo(Long id) throws Exception {
        String appId = "ZB4Lxdd3g5jS0ffMSV";
        String liveId = "238477*1";
        String sign = "5f0ed7e7b23a310f4cca18daf559dc82";

        String url = "http://rest.zhibo.tv/woao-video/playback-video";


        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);


        Map<String, String> params = new HashMap<>();
        params.put("appId", appId);
        params.put("liveId", liveId);
        params.put("sign", sign);

        String res = HttpUtil.postParameters(url, params);

        logger.info("回放信息：", res);
        return res;

    }
}
