package banquemisr.challenge05.taskManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TaskSearchResponse {
    private int page;
    private int size;
    private long count;
    private List<TaskDto> tasks;
}
