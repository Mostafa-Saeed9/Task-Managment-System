package banquemisr.challenge05.taskManagement.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TaskDto {

    private Long id ;
    private String title;
    private String description;
    private String status;
    private String priority;
    private LocalDate date;
}
