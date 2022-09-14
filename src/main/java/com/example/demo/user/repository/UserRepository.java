package com.example.demo.user.repository;

import com.example.demo.user.entity.MyUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<MyUser,Long> {
}
