//import io.shardingjdbc.core.api.ShardingDataSourceFactory;
//import io.shardingjdbc.core.exception.ShardingJdbcException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * @Author daocers
 * @Date 2019/1/16:15:34
 * @Description:
 */
@SpringBootApplication
@Configuration
public class ShardingApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingApplication.class, args);
    }


//    @Bean
//    public DataSource dataSource() throws IOException, SQLException {
//
//        InputStream is = ShardingApplication.class.getClassLoader().getResourceAsStream("classpath:sharding-jdbc.yml");
//        byte[] bytes = new byte[is.available()];
//        IOUtils.readFully(is, bytes);
//
//        DataSource dataSource = YamlShardingDataSourceFactory
//                .createDataSource(bytes);
//        return dataSource;
//    }
}
