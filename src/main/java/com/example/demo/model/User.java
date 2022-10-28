package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name = "USERS")
@Data
public class User {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ")
    @SequenceGenerator(sequenceName = "USERS_SEQ", initialValue = 1, allocationSize = 1, name = "USERS_SEQ")
    private long id;

    @Basic
    @NotEmpty(message = "Username không được để trống")
    @Size(max= 255)
    @Column(name = "USER_NAME")
    private String user_name;

    @Basic
    @NotEmpty(message = "Usercode không được để trống")
    @Size(max= 255)
    @Column(name = "USERS_CODE")
    private String users_code;

    @Basic
    @NotEmpty(message = "FullName không được để trống")
    @Size(max= 255)
    @Column(name = "FULL_NAME")
    private String full_name;

    @Basic
    @NotEmpty(message = "Address không được để trống")
    @Size(max= 255)
    @Column(name = "ADDRESS")
    private String address;

    @Basic
    @Column(name = "STATUS")
    private boolean Status;

    @Basic
    @Column(name = "IS_DELETE")
    private boolean is_delete;

    @Basic
    @Column(name = "CREATOR_DATE")
    private String creator_date;


    public User() {
    }

    public User(String user_name, String users_code, String full_name, String address, boolean status, boolean is_delete, String creator_date) {
        this.user_name = user_name;
        this.users_code = users_code;
        this.full_name = full_name;
        this.address = address;
        Status = status;
        this.is_delete = is_delete;
        this.creator_date = creator_date;
    }




    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUsers_code() {
        return users_code;
    }

    public void setUsers_code(String users_code) {
        this.users_code = users_code;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public boolean isIs_delete() {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }

    public String getCreator_date() {
        return creator_date;
    }

    public void setCreator_date(String creator_date) {
        this.creator_date = creator_date;
    }
}
