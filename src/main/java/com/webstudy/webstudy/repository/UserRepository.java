package com.webstudy.webstudy.repository;

import com.webstudy.webstudy.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserName(String userName); // 유저 이름을 통해 유저 조회

    UserEntity findByUserId(Long userId); // 유저 아이디를 통해 유저 조회

    boolean existsByUserName(String userName);

}
