package com.aqadoos.todo.repository;

import com.aqadoos.todo.domain.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<TodoItem, Long> {

}
