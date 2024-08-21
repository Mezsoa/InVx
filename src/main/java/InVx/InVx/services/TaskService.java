package InVx.InVx.services;
import InVx.InVx.exceptions.EntityNotFoundException;
import InVx.InVx.models.Task;
import InVx.InVx.models.User;
import InVx.InVx.payload.task.CreateTaskDTO;
import InVx.InVx.payload.task.UpdateTaskDTO;
import InVx.InVx.repositories.TaskRepository;
import InVx.InVx.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserService userService;


    public ResponseEntity<?> createTask(CreateTaskDTO createTaskDTO) {
      Task newTask = new Task();
      newTask.setUserId(createTaskDTO.getUserId());
      newTask.setTitle(createTaskDTO.getTitle());

      taskRepository.save(newTask);
      return ResponseEntity.ok("Task was created successfully");
    }

    // Lists all the tasks created of a user
    public List<Task> getAllTasks(String userId) {
        return taskRepository.findByUserId(userId);
    }

    // Gets a task by the task id
    // Här var ja tvungen att byta till att kasta ett undantag istället för att köra (Optional där ett objekt kan vara tomt).
    public Task getTaskById(String taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task with id " + taskId + " was not found"));
    }


//    public Optional<Task> getTaskById(String id) {
//        return taskRepository.findById(id);
//    }


    // update a task
    public Task updateTask(String taskId, UpdateTaskDTO updateTaskDTO) {
        return taskRepository.findById(taskId).map(existingTask -> {
            Optional.ofNullable(updateTaskDTO.getTitle()).ifPresent(existingTask::setTitle);
            return taskRepository.save(existingTask);
        }).orElseThrow(() -> new EntityNotFoundException("Task with id " + taskId + " not found"));
    }
    // Radera en task och uppdatera användarens poäng
    public ResponseEntity<?> deleteTask(String taskId) {
        if (taskRepository.existsById(taskId)) {
            Task task = getTaskById(taskId);
            taskRepository.deleteById(taskId);
            userService.updateUserPoints(task.getUserId(), 100); // set back to 1 for corrrect score value a user should get, 100 is set due to development mode
            return ResponseEntity.ok("Task was deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }
    }



//    public void deleteTask(String id) {
//        Task task = getTaskById(id);
//        taskRepository.deleteById(id);
//
//        // Uppdatera användarens poäng
//        userService.updateUserPoints(task.getUserId(), 1);  // Lägg till 1 poäng
//


}

