package com.incallup.backend.repository;


import com.incallup.backend.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Optional<Post> findPostByTitle(String title);


}
