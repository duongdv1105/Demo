package com.example.demo.service;

import com.example.demo.model.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface UserService {

    //Ham lay danh sach user
    List<User> list(User user);

    // Ham them nhan vien
    public  User addUser(User user) throws IOException;

    // Ham chinh sua thong tin nhan vien
    public User updateUser(long id, User user);

    // Ham xoa nhan vien
    public User deleteUser(long id);

    // Ham lay ra 1 nhan vien
    public Optional<User> getOneUser(long id);

    List<User> findPaginated(int pageNo,int pageSize);

    List<User> findByFull_nameOrAddress(String full_name, String address );


}
