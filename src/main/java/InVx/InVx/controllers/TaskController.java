package InVx.InVx.controllers;


import InVx.InVx.exceptions.EntityNotFoundException;
import InVx.InVx.models.Task;
import InVx.InVx.models.User;
import InVx.InVx.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;


    // Create a new task
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> createTask(@Valid @RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        try {
            return ResponseEntity.ok(createdTask);
        }  catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/find/all")
    public ResponseEntity<?> getAllTasks() {
        try {
            return ResponseEntity.ok(taskService.getAllTasks());
        }  catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/find/{id}")
    public ResponseEntity<?> getOneTask(@Valid @PathVariable String id) {
     try {
          return ResponseEntity.ok(taskService.getTaskById(id));
     }  catch (EntityNotFoundException e) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
     }
    }
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public Task updateTask(@PathVariable String id, @Valid @RequestBody Task task) {
        return taskService.updateTask(task);
    }
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTask(@Valid @PathVariable String id) {
        try {
             taskService.deleteTask(id);
        }  catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok().body("Task deleted successfully");
    }
}
