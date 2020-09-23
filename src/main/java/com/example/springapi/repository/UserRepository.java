package com.example.springapi.repository;

import com.example.springapi.models.User;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u.id as id, u.login as login, u.course as course, u.numgroup as group from users u where u.login = :login and u.password = :password")
    @JsonAnyGetter
    Map<String, Object> AuthUser(@Param("login") String login, @Param("password") String password);
}
