package InVx.InVx.services;



import InVx.InVx.models.Task;
import InVx.InVx.models.User;
import InVx.InVx.repositories.TaskRepository;
import InVx.InVx.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;




    public ResponseEntity<?> createTask(Task task) {

        Task newTask = new Task();
        newTask.setUserId(task.getUserId());
        newTask.setTitle(task.getTitle());
        taskRepository.save(newTask);
        return ResponseEntity.ok("Task created successfully");
    }

    // Lists all the tasks created
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Gets a task by the task id
    public Optional<Task> getTaskById(String id) {
        return taskRepository.findById(id);
    }

    // Update a task if it exists
    public Task updateTask(Task task) {
        return taskRepository.findById(task.getId()).map(exisistingTask -> {
            if (task.getTitle() != null) {
                exisistingTask.setTitle(task.getTitle());
            }
            return taskRepository.save(exisistingTask);
        }).orElseThrow(() -> new RuntimeException("Task was not found"));
    }

    public ResponseEntity<?> deleteTask(String id) {
        taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task was not found"));
        taskRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Task was successfully deleted!");
    }
}

