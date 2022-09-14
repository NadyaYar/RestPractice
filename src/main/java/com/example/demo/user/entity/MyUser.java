package com.example.demo.user.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String city;

    private int age;

    @Version
    private Long version;

    public MyUser(String name, String city, int age) {
        this.name = name;
        this.city = city;
        this.age = age;
    }
}
