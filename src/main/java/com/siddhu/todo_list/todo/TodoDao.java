package com.siddhu.todo_list.todo;

import com.siddhu.todo_list.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoDao extends JpaRepository<Todo,Integer> {

    List<Todo> findByUser(User user);
}
