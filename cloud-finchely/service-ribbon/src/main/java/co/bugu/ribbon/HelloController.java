package co.bugu.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author daocers
 * @Date 2019/1/9:10:34
 * @Description:
 */
@RestController
public class HelloController {
    @Autowired
    HelloService helloService;


    @GetMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return helloService.hiService(name);
    }
}
