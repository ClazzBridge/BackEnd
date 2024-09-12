package com.example.academy.repository;

import com.example.academy.domain.PostList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostList, Long> {


}
