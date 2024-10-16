package com.example.academy.repository.mysql;

import com.example.academy.domain.Course;
import com.example.academy.domain.Post;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @EntityGraph(attributePaths = {"author", "boardType", "course"})
    List<Post> findAll();

    List<Post> findByCourse(Course course);

    Post findSinglePostByCourse(Course course);
}
