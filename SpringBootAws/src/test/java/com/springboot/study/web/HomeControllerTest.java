package com.springboot.study.web;

import com.springboot.study.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebAppConfiguration
@RunWith(SpringRunner.class) // 스프링부트 테스트와 jUnit 사이에 연결자 역할을 한다
@WebMvcTest(controllers = HelloController.class,
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
    }
) // Web(Spring MVC)에 집중할 수 있는 어노테이션
// @Controller은 읽지만 @WebMvcTest는 @Service @Component는 스캔 대상이 아님, 스캔 대상에서 securityConfig를 제고해야한다.
@AutoConfigureMockMvc(addFilters = false)
public class HomeControllerTest {

    // 웹 api를 테스트할때 사용
    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(roles="USER")
    public void hello2() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }


    @Test
    @WithMockUser(roles="USER")
    public void dto_return() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }

    /**
     * jsonPath
     * JSON 응답값을 필드별로 검증할 수 있는 메소드
     * $를 기준으로 필드명을 명시함
     *
     * param
     * api 테스트할때 사용될 요청파라미터를 설정
     * 단 값은 String만 허용
     */
}
