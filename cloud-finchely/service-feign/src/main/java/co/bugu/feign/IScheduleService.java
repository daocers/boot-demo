package co.bugu.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author daocers
 * @Date 2019/1/9:10:48
 * @Description:
 */
@FeignClient(value = "service-hi", fallback = ScheduleService.class)
public interface IScheduleService {
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);

}
