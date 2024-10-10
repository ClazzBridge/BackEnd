package com.example.academy.repository.mysql;

import com.example.academy.domain.mysql.Post;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @EntityGraph(attributePaths = {"author", "board", "board.boardType", "course"})
    List<Post> findAll();

 
}
