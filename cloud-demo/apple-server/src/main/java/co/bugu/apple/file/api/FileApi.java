package co.bugu.apple.file.api;

import co.bugu.common.RespDto;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author daocers
 * @Date 2019/1/5:18:21
 * @Description:
 */
@RestController
public class FileApi implements IFileApi {
    @Override
    public RespDto<String> upload(@RequestPart("file") MultipartFile file) throws IOException {
        File target = new File("/", file.getOriginalFilename());
        file.transferTo(target);
        return RespDto.success("abfd");
    }
}
