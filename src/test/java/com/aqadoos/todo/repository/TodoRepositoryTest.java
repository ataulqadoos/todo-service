package com.aqadoos.todo.repository;

import com.aqadoos.todo.domain.TodoItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TodoRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TodoRepository repository;

    private List<TodoItem> todoItems = new ArrayList<>();

    @BeforeEach
    public void setup() {
        TodoItem todoItem = new TodoItem();
        todoItem.setName("test1");
        todoItem.setDetails("Some todo");
        todoItem.setScheduledDateTime(LocalDateTime.now());
        todoItems.add(testEntityManager.merge(todoItem));

        todoItem = new TodoItem();
        todoItem.setName("test2");
        todoItem.setDetails("Some todo 2");
        todoItem.setScheduledDateTime(LocalDateTime.now());
        todoItems.add(testEntityManager.merge(todoItem));
        testEntityManager.flush();
    }

    @Test
    public void testFindAll() {
        assertThat(this.repository).isNotNull();

        List<TodoItem> result = this.repository.findAll();
        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(5);
    }

    @Test
    public void testFindById() {
        assertThat(this.repository).isNotNull();

        TodoItem item = this.todoItems.get(0);
        Optional<TodoItem> result = this.repository.findById(item.getId());
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(item);
    }

    @Test
    public void testCreate() {
        assertThat(this.repository).isNotNull();

        TodoItem todoItem = new TodoItem();
        todoItem.setName("test1");
        todoItem.setDetails("Some todo");
        todoItem.setScheduledDateTime(LocalDateTime.now());
        this.repository.save(todoItem);

        Optional<TodoItem> result = this.repository.findById(todoItem.getId());
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(todoItem);
    }

    @Test
    public void testUpdate() {
        assertThat(this.repository).isNotNull();

        TodoItem item = this.todoItems.get(0);
        item.setDone(true);
        this.repository.save(item);

        Optional<TodoItem> result = this.repository.findById(item.getId());
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().isDone()).isTrue();
    }

    @Test
    public void testDelete() {
        assertThat(this.repository).isNotNull();

        TodoItem item = this.todoItems.get(0);
        this.repository.delete(item);

        Optional<TodoItem> result = this.repository.findById(item.getId());
        assertThat(result.isPresent()).isFalse();
    }
}