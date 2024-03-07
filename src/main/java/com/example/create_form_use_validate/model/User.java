package com.example.create_form_use_validate.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;

import javax.validation.constraints.*;

@Entity
@Table(name = "user")
public class User implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size(min = 5, max = 45)
    private String firstName;
    @NotEmpty
    @Size(min = 5, max = 45)
    private String lastName;
    //  @Pattern(regexp = "^0[0-9]{9}$", message = "Số điện thoại có 10 số và bắt đầu bằng số 0")
    private String phoneNumber;
    @Min(18)
    private int age;
    @Email
    private String email;

    public User() {
    }

    public User(Long id, @NotEmpty @Size(min = 5, max = 45) String firstName, @NotEmpty @Size(min = 5, max = 45) String lastName, String phoneNumber, @Min(18) int age, @Email String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.email = email;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User phoneNumber = (User) target;
        String number = phoneNumber.getPhoneNumber();
        ValidationUtils.rejectIfEmpty(errors, "number", "number.empty");
        if (number.length() > 11 || number.length() < 10) {
            errors.rejectValue("number", "number.length");
        }
        if (!number.startsWith("0")) {
            errors.rejectValue("number", "number.startsWith");
        }
        if (!number.matches("(^$|[0-9]*$)")) {
            errors.rejectValue("number", "number.matches");
        }


    }


}
