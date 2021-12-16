package ltd.muhuzhongxun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EnableTransactionManagement  //开启事务支持
public class MogApplication {
    public static void main(String[] args) {
        SpringApplication.run(MogApplication.class, args);
    }
}
