package co.bugu.feign.controller;

import co.bugu.feign.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author daocers
 * @Date 2019/1/9:10:50
 * @Description:
 */
@RestController
public class HiController {
    @Autowired
    IScheduleService scheduledService;

    @GetMapping(value = "/hi")
    public String sayHi(@RequestParam String name) {
        return scheduledService.sayHiFromClientOne(name);
    }
}
