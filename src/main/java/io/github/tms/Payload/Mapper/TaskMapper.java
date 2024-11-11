package io.github.tms.Payload.Mapper;

import org.springframework.stereotype.Service;

import io.github.tms.Entity.Task;
import io.github.tms.Payload.Request.TaskRequest;
import io.github.tms.Payload.Response.TaskResponse;

@Service
public class TaskMapper {
    
    public Task toTask(TaskRequest taskRequest)
    {
        return Task.builder()
        .title(taskRequest.title())
        .descreption(taskRequest.descreption())
        .build();
    }
    public TaskResponse toTasksResponse(Task task){
        return new TaskResponse(
           task.getId().toString(),
           task.getTitle(),
           task.getDescreption(),
           task.getStatus(),
           task.getDueDate(),
           task.getCreatedAt(),
           task.getUpdatedAt()
        );
    }
    
}
