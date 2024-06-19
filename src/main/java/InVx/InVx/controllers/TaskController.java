package InVx.InVx.controllers;


import InVx.InVx.exceptions.EntityNotFoundException;
import InVx.InVx.models.Task;
import InVx.InVx.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;


    // Create a new task
    @PostMapping("/post")
    public ResponseEntity<?> createTask(@Valid @RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        try {
            return ResponseEntity.ok(createdTask);
        }  catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
