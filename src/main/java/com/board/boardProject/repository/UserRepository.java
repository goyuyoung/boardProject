package com.board.boardProject.repository;

import com.board.boardProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    List<User> findByUserId(String userId);

    List<User> findByUuid(String uuid);

    List<User> findByUserIdAndUserPassword(String userId, String userPassword);
}
