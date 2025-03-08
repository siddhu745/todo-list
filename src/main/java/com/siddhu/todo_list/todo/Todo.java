package com.siddhu.todo_list.todo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.siddhu.todo_list.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String state;

    @CreationTimestamp
    private LocalDateTime cAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false, name = "user_email", referencedColumnName = "email") // foreign key of todo_users
    private User user;

    public Todo() {
    }

    public Todo(String name, String description, String state, User user) {
        this.name = name;
        this.description = description;
        this.state = state;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getState() {
        return state;
    }

    public LocalDateTime getCAt() {
        return cAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setState(String state) {
        this.state = state;
    }



    @Override
    public String toString() {
        return "Todo{id=" + id + ", name='" + name + "', description='" + description + "', state='" + state + "'}";
    }
}
