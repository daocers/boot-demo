package co.bugu.apple.file.api;

import co.bugu.common.RespDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author daocers
 * @Date 2019/1/5:18:19
 * @Description:
 */
@FeignClient(value = "apple-server")
public interface IFileApi {
    @RequestMapping("/file/api/v1/upload")
    RespDto<String> upload(@RequestPart("file")MultipartFile file) throws IOException;
}
