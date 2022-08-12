package com.springboot.study.web;

import com.springboot.study.config.auth.LoginUser;
import com.springboot.study.config.auth.dto.SessionUser;
import com.springboot.study.service.posts.PostsService;
import com.springboot.study.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        if(user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("posts", dto);

        return "posts-update";
    }

    /**
     * 세션 저장소로 데이터베이스 사용하기
     * 보통 세션 저장소에 대해 3가지중 한가지를 선택한다
     * 1. 톰캣 세션을 사용한다.
     *  - 일반적으로 별다른 설정을 하지 않을 때 기본적으로 선택되는 방식이다.
     *  - 이렇게 될경우 톰캣에 세션이 저장되기 때문에 2대 이상의 WAS가 구동외는 환경에서는 톰캣들 간의 세션 공유를 위한 추가 설정이 필요하다
     *
     *  2. mysql과 같은 디비를 세션 저장소로 사용한다
     *  - 여러 was간의 공유 세션을 사용할 수 있는 가장 쉬운 방법이다.
     *  - 많은 설정이 필요 없지만, 결국 로그인 요청마다 디비 IO가 발생하여 성능상 이슈가 발생할 수 있다
     *  - 보통 로그인 요청이 많이 없는 백오피스, 사내 시스템 용도에서 사용함
     *
     *  3. Redis, Memcached와 같은 메모리 DB를 세션 저장소로 사용한다
     *  - B2C 서비스에서 가장 많이 사용하는 방식
     *  - 실제 서비스로 사용하기 위해서는 임베디드 레디스와 같은 방식이 아닌 외부 메모리 서버가 팔요함
     */
}
