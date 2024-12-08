package banquemisr.challenge05.taskManagement.service;

import banquemisr.challenge05.taskManagement.dto.TaskDto;
import banquemisr.challenge05.taskManagement.dto.TaskFilterDto;
import banquemisr.challenge05.taskManagement.dto.TaskSearchResponse;
import banquemisr.challenge05.taskManagement.entity.Task;
import banquemisr.challenge05.taskManagement.entity.TaskSpecification;
import banquemisr.challenge05.taskManagement.enums.TaskStatus;
import banquemisr.challenge05.taskManagement.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import banquemisr.challenge05.taskManagement.repository.TaskRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TaskServiceImpl implements TaskService{


    @Autowired
    TaskRepository taskRepository;

    @Override
    public TaskDto craeteTask(TaskDto taskDto) {
        Task task = mapToEntity(taskDto);
        Task saved = taskRepository.save(task);
        return mapToDto(saved);
    }

    @Override
    public TaskDto getTaskById(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(()->
                new ResourceNotFoundException("Task not founf with this id:" + taskId));

        return mapToDto(task);
    }


    @Override
    public TaskSearchResponse getAllTasks(TaskFilterDto filter) {

        Specification<Task> spec = TaskSpecification.getTasksWithFilter(filter);

        int page = filter.getPage();
        int size = filter.getSize();

        if (size <= 0) {
            size = 10;
        }

        Pageable pegeable = PageRequest.of(page,size);

        Page<Task> tasks = taskRepository.findAll(spec ,pegeable);

        List<TaskDto> taskDtos = tasks.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        long count = taskRepository.count(spec);

        return new TaskSearchResponse(filter.getPage(), filter.getSize(), count, taskDtos);

    }

    @Override
    public void deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(()->
                new ResourceNotFoundException("Task not founf with this id:" + taskId));
        taskRepository.delete(task);
    }

    @Override
    public TaskDto updateTask(Long id, TaskDto taskDto) {
        Task task = taskRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Task not founf with this id:" + id));
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(task.getStatus());
        task.setPriority(task.getPriority());

        Task updated = taskRepository.save(task);
        return mapToDto(updated);
    }


    @Override
    public List<Task> getTasksWithUpcomingDeadlines(LocalDateTime from, LocalDateTime to) {
        return taskRepository.findByDeadlineBetween(from , to);
    }


    public Task mapToEntity(TaskDto taskDto){
        return Task.builder()
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .status(TaskStatus.valueOf(taskDto.getStatus()))
                .priority(taskDto.getPriority())
                .date(taskDto.getDate())
                .build();
    }

    public TaskDto mapToDto(Task task){
        return TaskDto.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus().name())
                .priority(task.getPriority())
                .date(task.getDate())
                .build();
    }
}
