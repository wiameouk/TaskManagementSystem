package io.github.tms.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.tms.Entity.Task;
import io.github.tms.EntityRepository.TaskRepository;
import io.github.tms.Enum.EStatus;
import io.github.tms.Exception.TaskNotFoundException;
import io.github.tms.IService.ITaskService;
import io.github.tms.Payload.Mapper.TaskMapper;
import io.github.tms.Payload.Request.TaskRequest;
import io.github.tms.Payload.Response.TaskResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService{
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskResponse getTaskById(String taskid) {
        return taskRepository.findById(UUID.fromString(taskid))
            .map(taskMapper::toTasksResponse)
            .orElseThrow(() -> new TaskNotFoundException("Cannot find task with id : " + taskid));
    }

    @Override
    public List<TaskResponse> getAllTask() {
        return taskRepository.findAll()
            .stream()
            .map(taskMapper::toTasksResponse)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void saveTask(TaskRequest taskRequest) {
        Task task = taskMapper.toTask(taskRequest);
        task.setStatus(EStatus.PENDING);
        taskRepository.save(task);
    }

    @Override
    @Transactional
    public void updateTask(String taskid, TaskRequest taskRequest) {
        Task task = taskRepository.findById(UUID.fromString(taskid))
            .orElseThrow(() -> new TaskNotFoundException("Cannot find task with id : " + taskid));
        task.setTitle(taskRequest.title());
        task.setDescreption(taskRequest.descreption());
        taskRepository.save(task);
    }

    @Override
    @Transactional
    public void deleteTaskById(String taskid) {
        if(taskRepository.existsById(UUID.fromString(taskid))){
            taskRepository.deleteById(UUID.fromString(taskid));
        }else{
            throw new TaskNotFoundException("Cannot find task with id : " + taskid);
        }
    }

    @Override
    @Transactional
    public void markAsFinished(String taskId ,boolean isFinished) {
        if(isFinished){
            Task task = taskRepository.findById(UUID.fromString(taskId))
                .orElseThrow(() -> new TaskNotFoundException("Cannot find task with id : " + taskId));
            task.setStatus(EStatus.FINISHED);
            task.setDueDate(LocalDateTime.now());
            taskRepository.save(task);
        }
    }


}
