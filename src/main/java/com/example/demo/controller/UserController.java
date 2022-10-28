package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;

import com.example.demo.service.UserService;
import com.example.demo.util.ResponseJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
//@Scope("request")
public class UserController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository repository;


    @GetMapping("/test")
    public Date test(){
        long miliSeconds = System.currentTimeMillis();
        Date date1 = new Date();
        return date1;
    }


    // API add employee
    @PostMapping("/add")
    User addUser( @RequestBody User user) throws IOException {
        return userService.addUser(user);
    }

    // API update employee
    @PutMapping("/update")
    public ResponseEntity<ResponseJson> updateUser( @RequestParam("id") long id, @RequestBody User user){
        try {
            User user1 = userService.updateUser(id, user);
            if (user1 != null) {
                return createSuccessResponse(user1, HttpStatus.OK);
            }
        } catch (Exception ex) {
            return createErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return updateUser(id, user);
    }

//     API delete employee
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long id) {
        User isRemoved = userService.deleteUser(id);
        if (isRemoved != null) {
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


//     API lay thong tin 1 user
    @GetMapping("")
    Optional<User> findByID(@RequestParam Long id){
        Optional<User> foundUser = repository.findById(id);
        return foundUser;
    }

    //  API lay danh sach user

    @GetMapping("/list")
    List<User> getAllUser (){
        return repository.findAll();
    }
    @GetMapping(value = "/paging/{pageNo}/{pageSize}")
    public List<User> getPaginated(@PathVariable int pageNo,@PathVariable int pageSize){
        return userService.findPaginated(pageNo, pageSize);
    }

    @GetMapping("/search")
    List<User> findByFull_nameOrAddress(@RequestParam(required = false) String full_name, @RequestParam(required = false) String address){
        return userService.findByFull_nameOrAddress(full_name, address);
    }

}

