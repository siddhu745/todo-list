package com.siddhu.todo_list.todo;

import com.siddhu.todo_list.user.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String state;

    @CreationTimestamp
    private LocalDateTime cAt;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_email", referencedColumnName = "email") // foreign key of todo_users
    private User user;

    public Todo() {}
    public Todo(String name, String description,String state,User user) {
        this.name = name;
        this.description = description;
        this.state = state;
        this.user = user;
    }
}
