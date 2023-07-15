package com.example.UserClient.repository;

import com.example.UserClient.dto.ProductDTO;
import com.example.UserClient.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
}
