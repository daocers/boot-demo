package co.bugu.fdfs;

import com.alibaba.fastjson.JSON;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author daocers
 * @Date 2019/1/12:14:31
 * @Description:
 */
public class FdfsClient {
    private static Logger logger = LoggerFactory.getLogger(FdfsClient.class);
    private static TrackerClient trackerClient;
    private static TrackerServer trackerServer;
    private static StorageServer storageServer;
    private static StorageClient storageClient;

    static {
        try {
            String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
            ClientGlobal.init(filePath);
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageServer = trackerClient.getStoreStorage(trackerServer);
        } catch (Exception e) {
            logger.error("初始化fdfs客户端失败", e);
        }
    }


    /**
     * 上传文件，返回文件的地址，不带域名
     *
     * @param
     * @return
     * @auther daocers
     * @date 2019/1/12 18:32
     */
    public static String upload(FastDFSFile file) {
        logger.info("上传文件：{}", JSON.toJSONString(file, true));
        NameValuePair[] metaList = new NameValuePair[1];
        metaList[0] = new NameValuePair("ownerId", file.getOwnerId() + "");

        long startTime = System.currentTimeMillis();

        String[] uploadResults = null;
        try {
            storageClient = new StorageClient(trackerServer, storageServer);
            uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), metaList);
        } catch (Exception e) {
            logger.error("fdfs上传文件失败", e);
        }

        logger.info("上传文件耗时： {} ms。", System.currentTimeMillis() - startTime);

        if (null == uploadResults) {
            logger.error("upload file fail, error code: {}", storageClient.getErrorCode());
        }

        String groupName = uploadResults[0];
        String remoteFileName = uploadResults[1];
        logger.info("upload file successfully, {}", JSON.toJSONString(uploadResults, true));
        return "/" + groupName + "/" + remoteFileName;
    }


    public static InputStream downFile(String groupName, String remoteFileName) {
        try {
            storageClient = new StorageClient(trackerServer, storageServer);
            byte[] fileByte = storageClient.download_file(groupName, remoteFileName);
            InputStream ins = new ByteArrayInputStream(fileByte);
            return ins;
        } catch (IOException e) {
            logger.error("IO Exception: Get File from Fast DFS failed", e);
        } catch (Exception e) {
            logger.error("Non IO Exception: Get File from Fast DFS failed", e);
        }
        return null;
    }


    /**
     * 删除文件
     *
     * @param
     * @return
     * @auther daocers
     * @date 2019/1/12 18:39
     */
    public static void deleteFile(String groupName, String remoteFileName)
            throws Exception {
        storageClient = new StorageClient(trackerServer, storageServer);
        int i = storageClient.delete_file(groupName, remoteFileName);
        logger.info("delete file successfully!!!" + i);
    }
}
