package InVx.InVx.controllers;


import InVx.InVx.exceptions.EntityNotFoundException;
import InVx.InVx.models.Task;
import InVx.InVx.models.User;
import InVx.InVx.payload.task.CreateTaskDTO;
import InVx.InVx.payload.task.UpdateTaskDTO;
import InVx.InVx.services.TaskService;
import InVx.InVx.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin(origins = "http://localhost:8080/", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Add a new task
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> createTask(@Valid @RequestBody CreateTaskDTO createTaskDTO) {
        try {
            return ResponseEntity.ok(taskService.createTask(createTaskDTO));
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // List all own task
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/find/all/{userId}")
    public ResponseEntity<?> getAllTasks(@PathVariable("userId") String userId) {
        List<Task> foundTasks = taskService.getAllTasks(userId);
        if (foundTasks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No tasks found");
        } else {
            return ResponseEntity.ok(foundTasks);
        }
    }

    // list one task by task id
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/find/{id}")
    public ResponseEntity<?> getOneTask(@Valid @PathVariable String id) {
     try {
          return ResponseEntity.ok(taskService.getTaskById(id));
     }  catch (EntityNotFoundException e) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
     }
    }

    // update a task
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateTask(@PathVariable("taskId") String taskId,
                                        @RequestBody UpdateTaskDTO updateTaskDTO) {
        Task UpdatedTask = taskService.updateTask(taskId, updateTaskDTO);
        return ResponseEntity.ok(UpdatedTask);
    }

    // delete a task
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
