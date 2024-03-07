package com.example.create_form_use_validate.repository;

import com.example.create_form_use_validate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Long> {
}
