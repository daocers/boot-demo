package co.bugu.web.agree.controller;

import co.bugu.apple.agree.api.IAgreeApi;
import co.bugu.apple.agree.dto.AgreeDto;
import co.bugu.apple.file.api.IFileApi;
import co.bugu.apple.worker.api.IWorkerApi;
import co.bugu.common.RespDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @Autowired
    IFileApi fileApi;

    @RequestMapping("/get")
    public RespDto<AgreeDto> get() {
        workerApi.findById(1L);
        return agreeApi.findById(1L);
    }

    @PostMapping(value = "/upload")
    public RespDto<String> upload(MultipartFile file) throws IOException {
        RespDto<String> respDto = fileApi.upload(file);

        return respDto;
    }
}
