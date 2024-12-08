package banquemisr.challenge05.taskManagement.scheduler;

import banquemisr.challenge05.taskManagement.entity.Task;
import banquemisr.challenge05.taskManagement.service.EmailService;
import banquemisr.challenge05.taskManagement.service.TaskService;
import jakarta.mail.MessagingException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class NotificationScheduler {


    private final EmailService emailService;

    private final TaskService taskService;

    public NotificationScheduler(EmailService emailService, TaskService taskService) {
        this.emailService = emailService;
        this.taskService = taskService;
    }


    // Runs every hour
    @Scheduled(cron = "0 0 * * * *")
    public void sendUpcomingTaskNotifications() {
        // Fetch tasks with deadlines within the next 24 hours
        List<Task> upcomingTasks = taskService.getTasksWithUpcomingDeadlines(LocalDateTime.now(), LocalDateTime.now().plusDays(1));

        for (Task task : upcomingTasks) {
            try {
                emailService.sendEmail(
                        task.getAssigneeEmail(),
                        "Upcoming Task Deadline",
                        String.format("Dear %s, <br>Your task '%s' is due on %s.",
                                task.getAssigneeName(), task.getAssigneeEmail(), task.getLocalDateTime())
                );
            } catch (MessagingException e) {
                // Handle email sending failure (e.g., log the error)
                e.printStackTrace();
            }
        }
    }
}
