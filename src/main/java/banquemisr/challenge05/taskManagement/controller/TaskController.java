package banquemisr.challenge05.taskManagement.controller;


import banquemisr.challenge05.taskManagement.dto.TaskDto;
import banquemisr.challenge05.taskManagement.dto.TaskFilterDto;
import banquemisr.challenge05.taskManagement.dto.TaskSearchResponse;
import banquemisr.challenge05.taskManagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;


    @PostMapping("/create")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto){
        TaskDto dto = taskService.craeteTask(taskDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long taskId) {
        TaskDto task = taskService.getTaskById(taskId);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping("/getAll")
    public ResponseEntity<TaskSearchResponse> getAllTasks(TaskFilterDto filter) {
        TaskSearchResponse response = taskService.getAllTasks(filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{taskId}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long taskId, @RequestBody TaskDto taskDto) {
        TaskDto updatedTask = taskService.updateTask(taskId, taskDto);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }
}
