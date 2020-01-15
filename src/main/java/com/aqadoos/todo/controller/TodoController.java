package com.aqadoos.todo.controller;

import com.aqadoos.todo.domain.TodoItem;
import com.aqadoos.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
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

    @PostMapping
    public ResponseEntity<Object> createTodo(@RequestBody TodoItem todoItem) {
        TodoItem newTodoIem = this.todoService.createOrUpdate(todoItem);

        URI newTodoItemLocation =
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(newTodoIem.getId())
                        .toUri();
        return ResponseEntity.created(newTodoItemLocation).build();
    }

    @PutMapping
    public ResponseEntity<String> updateTodo(@RequestBody TodoItem todoItem) {
        TodoItem foundTodo = this.todoService.findById(todoItem.getId());

        foundTodo.setName(todoItem.getName());
        foundTodo.setDetails(todoItem.getDetails());
        foundTodo.setDone(todoItem.isDone());
        foundTodo.setScheduledDateTime(todoItem.getScheduledDateTime());
        foundTodo.setCompletedAt(todoItem.getCompletedAt());

        this.todoService.createOrUpdate(foundTodo);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        TodoItem foundTodo = this.todoService.findById(id);
        this.todoService.deleteItem(foundTodo.getId());
//        return new ResponseEntity<String>("{status: \"success\"}", HttpStatus.OK);
    }
}
