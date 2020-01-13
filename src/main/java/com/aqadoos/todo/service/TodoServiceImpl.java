package com.aqadoos.todo.service;

import com.aqadoos.todo.domain.TodoItem;
import com.aqadoos.todo.exception.TodoItemNotFoundException;
import com.aqadoos.todo.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<TodoItem> getTodoItems() {
        log.info("Get all todo items ...");
        return todoRepository.findAll();
    }

    @Override
    public TodoItem findById(long todoId) {
        log.info("Find todo item where id=" + todoId);
        Optional<TodoItem> todoItem = todoRepository.findById(todoId);

        if (!todoItem.isPresent()) {
            StringBuilder errorMessage = new StringBuilder("TodoItem not found for id: ").append(todoId);
            TodoItemNotFoundException exception = new TodoItemNotFoundException(errorMessage.toString());
            log.error(errorMessage.toString(), exception);
            throw exception;
        }

        return todoItem.get();
    }

    @Override
    public TodoItem createOrUpdate(TodoItem todoItem) {
        log.info("Create new todo item");
        return this.todoRepository.save(todoItem);
    }

    @Override
    public void deleteItem(long todoId) {
        log.info("Delete todo item where id=" + todoId);
        this.todoRepository.deleteById(todoId);
    }
}
