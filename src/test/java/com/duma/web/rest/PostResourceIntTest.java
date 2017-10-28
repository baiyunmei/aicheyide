package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.Post;
import com.duma.repository.PostRepository;
import com.duma.service.PostService;
import com.duma.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PostResource REST controller.
 *
 * @see PostResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class PostResourceIntTest {

    private static final Long DEFAULT_COMPANY_ID = 1L;
    private static final Long UPDATED_COMPANY_ID = 2L;

    private static final Long DEFAULT_DEPARTMENT_ID = 1L;
    private static final Long UPDATED_DEPARTMENT_ID = 2L;

    private static final String DEFAULT_POST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_POST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STAFFING = "AAAAAAAAAA";
    private static final String UPDATED_STAFFING = "BBBBBBBBBB";

    private static final Integer DEFAULT_IN_EMPLOYEE = 1;
    private static final Integer UPDATED_IN_EMPLOYEE = 2;

    private static final Integer DEFAULT_FROM_EMPLOYEE = 1;
    private static final Integer UPDATED_FROM_EMPLOYEE = 2;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPostMockMvc;

    private Post post;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PostResource postResource = new PostResource(postService);
        this.restPostMockMvc = MockMvcBuilders.standaloneSetup(postResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Post createEntity(EntityManager em) {
        Post post = new Post()
                .companyId(DEFAULT_COMPANY_ID)
                .departmentId(DEFAULT_DEPARTMENT_ID)
                .postName(DEFAULT_POST_NAME)
                .staffing(DEFAULT_STAFFING)
                .inEmployee(DEFAULT_IN_EMPLOYEE)
                .fromEmployee(DEFAULT_FROM_EMPLOYEE);
        return post;
    }

    @Before
    public void initTest() {
        post = createEntity(em);
    }

    @Test
    @Transactional
    public void createPost() throws Exception {
        int databaseSizeBeforeCreate = postRepository.findAll().size();

        // Create the Post

        restPostMockMvc.perform(post("/api/posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(post)))
            .andExpect(status().isCreated());

        // Validate the Post in the database
        List<Post> postList = postRepository.findAll();
        assertThat(postList).hasSize(databaseSizeBeforeCreate + 1);
        Post testPost = postList.get(postList.size() - 1);
        assertThat(testPost.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testPost.getDepartmentId()).isEqualTo(DEFAULT_DEPARTMENT_ID);
        assertThat(testPost.getPostName()).isEqualTo(DEFAULT_POST_NAME);
        assertThat(testPost.getStaffing()).isEqualTo(DEFAULT_STAFFING);
        assertThat(testPost.getInEmployee()).isEqualTo(DEFAULT_IN_EMPLOYEE);
        assertThat(testPost.getFromEmployee()).isEqualTo(DEFAULT_FROM_EMPLOYEE);
    }

    @Test
    @Transactional
    public void createPostWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = postRepository.findAll().size();

        // Create the Post with an existing ID
        Post existingPost = new Post();
        existingPost.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPostMockMvc.perform(post("/api/posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingPost)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Post> postList = postRepository.findAll();
        assertThat(postList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPosts() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get all the postList
        restPostMockMvc.perform(get("/api/posts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(post.getId().intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].departmentId").value(hasItem(DEFAULT_DEPARTMENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].postName").value(hasItem(DEFAULT_POST_NAME.toString())))
            .andExpect(jsonPath("$.[*].staffing").value(hasItem(DEFAULT_STAFFING.toString())))
            .andExpect(jsonPath("$.[*].inEmployee").value(hasItem(DEFAULT_IN_EMPLOYEE)))
            .andExpect(jsonPath("$.[*].fromEmployee").value(hasItem(DEFAULT_FROM_EMPLOYEE)));
    }

    @Test
    @Transactional
    public void getPost() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get the post
        restPostMockMvc.perform(get("/api/posts/{id}", post.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(post.getId().intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.departmentId").value(DEFAULT_DEPARTMENT_ID.intValue()))
            .andExpect(jsonPath("$.postName").value(DEFAULT_POST_NAME.toString()))
            .andExpect(jsonPath("$.staffing").value(DEFAULT_STAFFING.toString()))
            .andExpect(jsonPath("$.inEmployee").value(DEFAULT_IN_EMPLOYEE))
            .andExpect(jsonPath("$.fromEmployee").value(DEFAULT_FROM_EMPLOYEE));
    }

    @Test
    @Transactional
    public void getNonExistingPost() throws Exception {
        // Get the post
        restPostMockMvc.perform(get("/api/posts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePost() throws Exception {
        // Initialize the database
        postService.save(post);

        int databaseSizeBeforeUpdate = postRepository.findAll().size();

        // Update the post
        Post updatedPost = postRepository.findOne(post.getId());
        updatedPost
                .companyId(UPDATED_COMPANY_ID)
                .departmentId(UPDATED_DEPARTMENT_ID)
                .postName(UPDATED_POST_NAME)
                .staffing(UPDATED_STAFFING)
                .inEmployee(UPDATED_IN_EMPLOYEE)
                .fromEmployee(UPDATED_FROM_EMPLOYEE);

        restPostMockMvc.perform(put("/api/posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPost)))
            .andExpect(status().isOk());

        // Validate the Post in the database
        List<Post> postList = postRepository.findAll();
        assertThat(postList).hasSize(databaseSizeBeforeUpdate);
        Post testPost = postList.get(postList.size() - 1);
        assertThat(testPost.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testPost.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testPost.getPostName()).isEqualTo(UPDATED_POST_NAME);
        assertThat(testPost.getStaffing()).isEqualTo(UPDATED_STAFFING);
        assertThat(testPost.getInEmployee()).isEqualTo(UPDATED_IN_EMPLOYEE);
        assertThat(testPost.getFromEmployee()).isEqualTo(UPDATED_FROM_EMPLOYEE);
    }

    @Test
    @Transactional
    public void updateNonExistingPost() throws Exception {
        int databaseSizeBeforeUpdate = postRepository.findAll().size();

        // Create the Post

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPostMockMvc.perform(put("/api/posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(post)))
            .andExpect(status().isCreated());

        // Validate the Post in the database
        List<Post> postList = postRepository.findAll();
        assertThat(postList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePost() throws Exception {
        // Initialize the database
        postService.save(post);

        int databaseSizeBeforeDelete = postRepository.findAll().size();

        // Get the post
        restPostMockMvc.perform(delete("/api/posts/{id}", post.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Post> postList = postRepository.findAll();
        assertThat(postList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Post.class);
    }
}
