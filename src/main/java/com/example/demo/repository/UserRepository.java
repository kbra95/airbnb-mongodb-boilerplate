package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.domain.Users;

public interface UserRepository extends MongoRepository<Users, String>{

}
