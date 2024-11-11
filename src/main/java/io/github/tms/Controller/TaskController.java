package io.github.tms.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import io.github.tms.IService.ITaskService;
import io.github.tms.Payload.Request.TaskRequest;
import io.github.tms.Payload.Response.TaskResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final ITaskService taskService;
    

    @PostMapping
    public ResponseEntity<Void> createTask(@RequestBody @Valid TaskRequest taskRequest){
        taskService.saveTask(taskRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks(){
        return ResponseEntity.ok(taskService.getAllTask());
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable("id") String id){
        return ResponseEntity.ok(taskService.getTaskById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTaskById(@PathVariable("id") String id , @RequestBody @Valid TaskRequest taskRequest){
        taskService.updateTask(id, taskRequest);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable("id") String id){
        taskService.deleteTaskById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @PutMapping("/changeStatus/{id}")
    public ResponseEntity<Void> ChangeStatus(@PathVariable("id") String id, @RequestBody Boolean isFinished){
        taskService.markAsFinished(id, isFinished);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
