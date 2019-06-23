package com.spring.webservices.restfulwebservices.Repository;

import com.spring.webservices.restfulwebservices.Domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
}
