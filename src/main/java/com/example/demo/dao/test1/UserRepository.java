package com.example.demo.dao.test1;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.test1.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{

}
