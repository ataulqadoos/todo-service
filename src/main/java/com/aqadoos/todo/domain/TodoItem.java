package com.aqadoos.todo.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

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
    @ApiModelProperty(value = "done", notes = "Marked as done or not")
    private boolean done;

    @Getter @Setter
    @ApiModelProperty("details")
    private String details;

    @Getter @Setter
    @ApiModelProperty("created at")
    private LocalDateTime createdAt;

    @Getter @Setter
    @ApiModelProperty("Completed at")
    private LocalDateTime completedAt;
}
