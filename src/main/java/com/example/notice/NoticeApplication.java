package com.example.notice;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
@MapperScan(basePackages = {"com.example.notice"})
public class NoticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoticeApplication.class, args);
	}


}
