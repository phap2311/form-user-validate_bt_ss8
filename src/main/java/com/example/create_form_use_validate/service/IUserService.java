package com.example.create_form_use_validate.service;

import com.example.create_form_use_validate.model.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();
    void save(User user);
}
