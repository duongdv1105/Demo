package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    private UserRepository userRepository;

    public Date date() {
        long miliSeconds = System.currentTimeMillis();
        Date date1 = new Date();
        return date1;
    }

    @Override
    public User addUser(User user) throws IOException {

        if (user != null) {
            user.setIs_delete(false);
            user.setStatus(true);
            user.setCreator_date(date().toString());
            return userRepository.save(user);
        }
        return null;
    }

    @Override

    public User updateUser(long id, User user) {
        try {
            if (user != null) {
                User user1 = userRepository.findById(id).get();
                if (user1 != null) {
                    user1.setUser_name(user.getUser_name());
                    user1.setUsers_code(user.getUsers_code());
                    user1.setFull_name(user.getFull_name());
                    user1.setAddress(user.getAddress());
                    user1.setStatus(user.isStatus());
                    user1.setIs_delete(user.isIs_delete());
                    user1.setCreator_date(date().toString());
                    return userRepository.save(user1);
                }
            }
        } catch (Exception e) {
            LOG.error("update user : " + e.getMessage());
        }
        return null;
    }


    @Override
    public User deleteUser(long id) {
        try {
            User user1 = userRepository.findById(id).get();
            if (user1 != null && user1.isIs_delete() == false) {
                user1.setIs_delete(true);
                return userRepository.save(user1);
            }
        } catch (Exception e) {
            LOG.error("delete user : " + e.getMessage());
        }
        return null;
    }


    @Override
    public Optional<User> getOneUser(long id) {
        return userRepository.findById(id);
    }


    public List<User> list(User user) {
        return userRepository.findAll();
    }

    @Override
    public List<User> findPaginated(int pageNo, int pageSize) {
        PageRequest paging = PageRequest.of(pageNo, pageSize);
        Page<User> pagedResult = userRepository.findAll(paging);
        return pagedResult.toList();
    }

    @Override
    public List<User> findByFull_nameOrAddress(String full_name, String address) {
        return userRepository.fillByFull_nameOrAddress(full_name, address);
    }

}