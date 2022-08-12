package com.springboot.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


// 아래의 어노테이션을 사용하기 위해서는 최소 하나의 @Entity 클래스가 필요하다.
// @WebMvcTest이다 보니 당연히 없다.
//@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
