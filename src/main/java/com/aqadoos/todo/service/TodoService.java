package com.aqadoos.todo.service;

import com.aqadoos.todo.domain.TodoItem;

import java.util.List;

public interface TodoService {

    List<TodoItem> getTodoItems();
    TodoItem findById(long todoId);
    TodoItem createOrUpdate(TodoItem todoItem);
    void deleteItem(long todoId);
}
