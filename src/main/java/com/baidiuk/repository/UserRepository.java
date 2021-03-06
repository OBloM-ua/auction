package com.baidiuk.repository;

import com.baidiuk.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Integer>{
    @Query("select u from User u where u.email = :email")
    User getByEmail(@Param("email") String email);
}
