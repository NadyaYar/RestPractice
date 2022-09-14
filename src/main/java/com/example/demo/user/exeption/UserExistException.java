package com.example.demo.user.exeption;

public class UserExistException extends Exception{
    public UserExistException(String message) {
        super(message);
    }
}
