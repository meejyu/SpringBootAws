package com.springboot.study.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Repository를 추가안해도 됨.
 * Entity 클래스와 기본 Entity Repository는 함께 위치해야함
 * Entity 클래스는 기본 Repository 없이는 제대로 역할을 할 수가 없습니다
 *
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
