package banquemisr.challenge05.taskManagement.repository;

import banquemisr.challenge05.taskManagement.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task , Long> , JpaSpecificationExecutor<Task> {

     List<Task> findByDeadlineBetween(LocalDateTime from, LocalDateTime to);
}
