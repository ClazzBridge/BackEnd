package com.example.academy.service;

import com.example.academy.domain.Board;
import com.example.academy.domain.Classroom;
import com.example.academy.domain.Member;
import com.example.academy.domain.Post;
import com.example.academy.dto.post.PostCreateDTO;
import com.example.academy.dto.post.PostResponseDTO;
import com.example.academy.dto.post.PostUpdateDTO;
import com.example.academy.exception.post.PostBadRequestException;
import com.example.academy.exception.post.PostEmptyException;
import com.example.academy.exception.post.PostEmptyTitleException;
import com.example.academy.exception.post.PostNotFoundException;
import com.example.academy.mapper.post.PostCreateMapper;
import com.example.academy.mapper.post.PostResponseMapper;
import com.example.academy.repository.BoardRepository;
import com.example.academy.repository.ClassroomRepository;
import com.example.academy.repository.MemberRepository;
import com.example.academy.repository.PostRepository;
import java.util.Comparator;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final ClassroomRepository classroomRepository;
    private final BoardRepository boardRepository;

    private final PostResponseMapper postResponseMapper;
    private final PostCreateMapper postCreateMapper;

    public PostService(PostRepository postRepository, PostResponseMapper postResponseMapper,
        PostCreateMapper postCreateMapper, MemberRepository memberRepository,
        ClassroomRepository classroomRepository, BoardRepository boardRepository) {
        this.postRepository = postRepository;
        this.postCreateMapper = postCreateMapper;
        this.postResponseMapper = postResponseMapper;
        this.memberRepository = memberRepository;
        this.classroomRepository = classroomRepository;
        this.boardRepository = boardRepository;
    }

    public PostResponseDTO findById(Long id) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new PostNotFoundException(id));
        return postResponseMapper.toDto(post);
    }

    public List<PostResponseDTO> findAllPosts() {
        List<Post> posts = postRepository.findAll();
        return postResponseMapper.toDtoList(posts).stream()
            .sorted(Comparator.comparing(PostResponseDTO::getId).reversed()).toList();
    }

    public List<PostResponseDTO> findAllFreePosts() {
        List<Post> posts = postRepository.findAll()
            .stream()
            .filter(post -> post.getBoard().getBoardType().getDescription().equals("자유게시판"))
            .toList();

        return postResponseMapper.toDtoList(posts);
    }

    public List<PostResponseDTO> findAllNotificationPosts() {
        List<Post> posts = postRepository.findAll()
            .stream()
            .filter(post -> post.getBoard().getBoardType().getDescription().equals("공지사항"))
            .toList();

        return postResponseMapper.toDtoList(posts);
    }

    @Transactional
    public PostResponseDTO save(PostCreateDTO postDTO) {
        Member member = memberRepository.findById(postDTO.getMemberId())
            .orElseThrow(PostBadRequestException::new);

        Board board = boardRepository.findById(postDTO.getBoardId())
            .orElseThrow(PostBadRequestException::new);

        Classroom classroom = classroomRepository.findById(postDTO.getClassroomId())
            .orElseThrow(PostBadRequestException::new);

        Post savedPost = postCreateMapper.toEntity(postDTO, member, board, classroom);
        postRepository.save(savedPost);

        return postResponseMapper.toDto(savedPost);
    }

    @Transactional
    public PostResponseDTO update(PostUpdateDTO postDTO) {
        Long postId = postDTO.getId();

        Post updatedPost = postRepository.findById(postId)
            .orElseThrow(() -> new PostNotFoundException(postId));

        if (postDTO.getTitle() == null || postDTO.getTitle().trim().isEmpty()) {
            throw new PostEmptyTitleException(postId);
        }

        updatedPost.updateTitle(postDTO.getTitle());
        updatedPost.updateContent(postDTO.getContent());

        return postResponseMapper.toDto(updatedPost);
    }


    @Transactional
    public void delete(List<Long> ids) {
        if (ids.isEmpty()) {
            throw new PostEmptyException();
        }

        for (Long id : ids) {
            Post deletedPost = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
            postRepository.delete(deletedPost);
        }
    }
}
