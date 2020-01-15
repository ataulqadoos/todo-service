package com.aqadoos.todo.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
@ApiModel(value = "TodoItem", description = "Todo Item")
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @ApiModelProperty("Unique Id")
    private long id;

    @Getter @Setter
    @ApiModelProperty("name")
    @NotNull
    @Size(min = 2, max = 20, message = "Name should be between 2 - 20 chars")
    private String name;

    @Getter @Setter
    @ApiModelProperty("details")
    @Size(min = 2, max = 100, message = "Details should be between 2 - 100 chars")
    private String details;

    @Getter @Setter
    @ApiModelProperty(value = "done", notes = "Marked as done or not")
    private boolean done;

    @Getter @Setter
    @ApiModelProperty("scheduled date and time")
    @NotNull
    private LocalDateTime scheduledDateTime;

    @Getter @Setter
    @ApiModelProperty("Completed at")
    private LocalDateTime completedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoItem todoItem = (TodoItem) o;
        return id == todoItem.id &&
                done == todoItem.done &&
                Objects.equals(name, todoItem.name) &&
                Objects.equals(details, todoItem.details) &&
                Objects.equals(scheduledDateTime, todoItem.scheduledDateTime) &&
                Objects.equals(completedAt, todoItem.completedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, details, done, scheduledDateTime, completedAt);
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", done=" + done +
                ", scheduledDateTime=" + scheduledDateTime +
                ", completedAt=" + completedAt +
                '}';
    }
}
