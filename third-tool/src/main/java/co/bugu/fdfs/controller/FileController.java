package co.bugu.fdfs.controller;

import co.bugu.fdfs.FastDFSFile;
import co.bugu.fdfs.FdfsClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author daocers
 * @Date 2019/1/12:15:01
 * @Description:
 */

@RestController
public class FileController {

    @RequestMapping("/upload")
    public String upload(MultipartFile file) throws IOException {
        FastDFSFile dfsFile = new FastDFSFile();
        dfsFile.setOwnerId(1L);
        dfsFile.setContent(file.getBytes());
        String res = FdfsClient.upload(dfsFile);

        return res;
    }
}
