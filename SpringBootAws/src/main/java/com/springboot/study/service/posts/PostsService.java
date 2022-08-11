package com.springboot.study.service.posts;

import com.springboot.study.domain.posts.Posts;
import com.springboot.study.domain.posts.PostsRepository;
import com.springboot.study.web.dto.PostsListResponseDto;
import com.springboot.study.web.dto.PostsResponseDto;
import com.springboot.study.web.dto.PostsSaveRequestDto;
import com.springboot.study.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게실글이 없습니다. id="+id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // postsRepository결과로 넘어온 posts의 Stream을 map을 통해 PostListResponseDto 변환 -> List로 반환하는 메소드
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        postsRepository.delete(posts);
    }

    /**
     * update 할때 데이터베이스 쿼리를 날리는 부분이 없다.
     * JPA의 영속성 컨텍스트 때문이다.
     * 영속성 컨텍스트란 엔티티를 영구 저장하는 환경이다.
     * Entity 객체의 값만 변경하면 별도로 Update 쿼리를 날릴 필요가 없다.
     * 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영한다.
     * 이것을 더티 체킹이라고 함.
     */
}
