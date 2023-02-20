package com.example.todoapp.repository;

import com.example.todoapp.entity.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface userRepository extends JpaRepository<userEntity, Long> {

    List<userEntity> findByLastName(String lastName);

    userEntity findById(long id);

}