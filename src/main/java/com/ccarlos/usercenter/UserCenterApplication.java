package com.ccarlos.usercenter;

//import com.ccarlos.usercenter.rocketmq.MySink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.messaging.Sink;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.stream.annotation.EnableBinding;

/** 用户中心启动类 */
// 扫描mybatis哪些包里面的接口
@MapperScan("com.ccarlos.usercenter.dao")
@SpringBootApplication
@EnableBinding({Sink.class/*, MySink.class*/})
public class UserCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCenterApplication.class, args);
    }

}
