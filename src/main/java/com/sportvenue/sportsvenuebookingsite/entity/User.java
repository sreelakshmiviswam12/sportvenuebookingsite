package com.sportvenue.sportsvenuebookingsite.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    private String phoneNo;

    @Enumerated(EnumType.STRING)
    private Role role;
}