package io.github.tms.IService;

import java.util.List;

import io.github.tms.Payload.Request.TaskRequest;
import io.github.tms.Payload.Response.TaskResponse;

public interface ITaskService {
    TaskResponse getTaskById(String taskid);
    List<TaskResponse> getAllTask();
    void saveTask(TaskRequest taskRequest);
    void updateTask(String taskid , TaskRequest taskRequest);
    void deleteTaskById(String taskid);
    void markAsFinished(String taskId ,boolean isFinished);
}
