package com.crud.tasks.client;

import com.crud.tasks.controller.TaskController;
import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskClientTestSuite {
    @Autowired
    private TaskController taskController;

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void TaskNotFoundTest() {
        assertThrows(TaskNotFoundException.class, () -> {
            taskController.getTask(999999L);
        });
    }

    @Test
    public void TaskMapperTest() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "TaskDto Title", "TaskDto content");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(task.getId(), taskDto.getId());
        assertEquals(task.getTitle(), taskDto.getTitle());
        assertEquals(task.getContent(), taskDto.getContent());
    }
}
