package co.bugu.feign;

import org.springframework.stereotype.Service;

/**
 * @Author daocers
 * @Date 2019/1/9:11:03
 * @Description:
 */
@Service
public class ScheduleService implements IScheduleService {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry, " + name;
    }
}
