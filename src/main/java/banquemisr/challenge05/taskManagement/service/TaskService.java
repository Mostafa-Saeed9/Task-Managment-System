package banquemisr.challenge05.taskManagement.service;

import banquemisr.challenge05.taskManagement.dto.TaskDto;
import banquemisr.challenge05.taskManagement.dto.TaskFilterDto;
import banquemisr.challenge05.taskManagement.dto.TaskSearchResponse;
import banquemisr.challenge05.taskManagement.entity.Task;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskService {


    TaskDto craeteTask(TaskDto taskDto);
    TaskDto getTaskById(Long taskId);
    TaskSearchResponse getAllTasks(TaskFilterDto filterDto);
    void deleteTask(Long taskId);
    TaskDto updateTask(Long id , TaskDto taskDto);

    List<Task> getTasksWithUpcomingDeadlines(LocalDateTime from, LocalDateTime to);
}
