package com.spring.webservices.restfulwebservices.Repository;

import com.spring.webservices.restfulwebservices.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDomainRepository extends JpaRepository<User,Integer> {

}
