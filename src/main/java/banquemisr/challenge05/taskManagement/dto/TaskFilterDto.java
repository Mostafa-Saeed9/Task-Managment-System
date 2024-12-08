package banquemisr.challenge05.taskManagement.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskFilterDto {

    private int page;
    private int size;

    private String title;
    private String description;
    private String status;
    private String priority;
    private LocalDate date;
}
