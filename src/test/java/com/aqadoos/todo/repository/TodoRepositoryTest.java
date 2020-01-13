package com.aqadoos.todo.repository;

import com.aqadoos.todo.domain.TodoItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@DataJpaTest
public class TodoRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TodoRepository repository;

//    @BeforeEach
    public void setup() {
        TodoItem todoItem = new TodoItem();
        todoItem.setName("test1");
        todoItem.setDetails("Some todo");
        todoItem.setScheduledDateTime(LocalDateTime.now());
        testEntityManager.merge(todoItem);

        todoItem = new TodoItem();
        todoItem.setName("test2");
        todoItem.setDetails("Some todo 2");
        todoItem.setScheduledDateTime(LocalDateTime.now());
        testEntityManager.merge(todoItem);

        testEntityManager.flush();
    }

//    @Test
    public void testFindAll() {
        assertThat(this.testEntityManager).isNotNull();
        assertThat(this.repository).isNotNull();

        List<TodoItem> result = this.repository.findAll();
        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(2);
    }
}