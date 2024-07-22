package com.example.demo.src.hanamoa;

import com.example.demo.src.hanamoa.dto.PostDto;
import com.example.demo.src.hanamoa.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    @Autowired
    private final PostRepository postRepository;


    @Override
    public void createPost(PostDto post) {

    }
}
