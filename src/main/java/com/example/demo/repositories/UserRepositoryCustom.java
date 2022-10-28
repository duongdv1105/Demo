package com.example.demo.repositories;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> fillByTen(@Value("USER_NAME") String ten);
    List<User> fillByTenNative(String ten);
}
