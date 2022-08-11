package com.springboot.study.config.auth.dto;

import com.springboot.study.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

/**
 * User 클래스를 사용하지 않는 이유
 * User 클래스를 사용하려면 직렬화를 구현해야하는데 직렬화를 구현하면
 * User은 엔티티기 떄문에 직렬화가 되면 자식들까지 직렬화 대상이 되기 때문에 성능 이슈와 부수 효과가 발생할 수 있다
 * 그래서 직렬화 기능을 가지 세션 Dto를 하나 추가로 만드는게 좋다
 */

// 인증된 사용자 정보만 필요하다.
@Getter
public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
