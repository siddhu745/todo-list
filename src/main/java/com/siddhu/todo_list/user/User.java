package com.siddhu.todo_list.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@Entity
@Table(name = "todo_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String email;
    private String password;
    public User(String username,String email,String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
