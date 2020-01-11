package com.aqadoos.todo.controller;

import com.aqadoos.todo.domain.TodoItem;
import com.aqadoos.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<TodoItem> getTodoList() {
        return todoService.getTodoItems();
    }

    @GetMapping("/{id}")
    public TodoItem getTodoItem(@PathVariable Long id) {
        return todoService.findById(id);
    }
}
