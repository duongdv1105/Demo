package com.example.demo.repositories;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    @Query("SELECT t FROM User t WHERE t.full_name = ?1 OR t.address = ?2")
    List<User> fillByFull_nameOrAddress(String full_name, String address);

}
