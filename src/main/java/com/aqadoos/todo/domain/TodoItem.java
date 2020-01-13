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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
}
