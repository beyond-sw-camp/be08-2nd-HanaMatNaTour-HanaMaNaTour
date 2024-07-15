package com.example.demo.src.hanamoa;

import com.example.demo.src.hanamoa.dto.PostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("")
    public String post(@RequestBody PostDto post) {
        System.out.println("=========");
        log.info(post.toString());
        System.out.println("=========");
        postService.createPost(post);
        return "등록 완료";
    }
}
