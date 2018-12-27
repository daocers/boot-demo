package co.bugu.web.agree.controller;

import co.bugu.apple.agree.api.IAgreeApi;
import co.bugu.apple.agree.dto.AgreeDto;
import co.bugu.apple.worker.api.IWorkerApi;
import co.bugu.common.RespDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author daocers
 * @Date 2018/12/26:15:29
 * @Description:
 */

@RestController
public class AgreeController {
    @Autowired
    IAgreeApi agreeApi;
    @Autowired
    IWorkerApi workerApi;


    @RequestMapping("/get")
    public RespDto<AgreeDto> get() {
        workerApi.findById(1L);
        return agreeApi.findById(1L);
    }
}
