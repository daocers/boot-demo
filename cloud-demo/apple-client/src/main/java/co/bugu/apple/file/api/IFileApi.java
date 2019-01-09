package co.bugu.apple.file.api;

import co.bugu.common.RespDto;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author daocers
 * @Date 2019/1/5:18:19
 * @Description:
 */
@FeignClient(value = "apple-server", configuration = IFileApi.MultipartSupportConfig.class)
public interface IFileApi {
    @PostMapping(value = "/file/api/v1/upload")
    RespDto<String> upload(@RequestPart("file")MultipartFile file) throws IOException;


    @Configuration
    class MultipartSupportConfig{
        @Bean
        public Encoder feignFormEncoder(){
            return new SpringFormEncoder();
        }
    }
}
