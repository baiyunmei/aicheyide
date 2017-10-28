package com.duma.service.impl;

import com.duma.service.PostService;
import com.duma.domain.Post;
import com.duma.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing Post.
 */
@Service
@Transactional
public class PostServiceImpl implements PostService{

    private final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);
    
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * Save a post.
     *
     * @param post the entity to save
     * @return the persisted entity
     */
    @Override
    public Post save(Post post) {
        log.debug("Request to save Post : {}", post);
        Post result = postRepository.save(post);
        return result;
    }

    /**
     *  Get all the posts.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Post> findAll(Pageable pageable) {
        log.debug("Request to get all Posts");
        Page<Post> result = postRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one post by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Post findOne(Long id) {
        log.debug("Request to get Post : {}", id);
        Post post = postRepository.findOne(id);
        return post;
    }

    /**
     *  Delete the  post by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Post : {}", id);
        postRepository.delete(id);
    }
}
