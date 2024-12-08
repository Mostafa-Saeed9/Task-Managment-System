package banquemisr.challenge05.taskManagement.entity;

import banquemisr.challenge05.taskManagement.dto.TaskFilterDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TaskSpecification {

    public static Specification<Task> getTasksWithFilter(TaskFilterDto filter) {
        return (Root<Task> task, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicateList = new ArrayList<>();

            // Filter by title
            if (filter.getTitle() != null && !filter.getTitle().isEmpty()) {
                predicateList.add(cb.like(cb.lower(task.get("title")), "%" + filter.getTitle().toLowerCase() + "%"));
            }

            // Filter by description
            if (filter.getDescription() != null && !filter.getDescription().isEmpty()) {
                predicateList.add(cb.like(cb.lower(task.get("description")), "%" + filter.getDescription().toLowerCase() + "%"));
            }

            // Filter by status
            if (filter.getStatus() != null && !filter.getStatus().isEmpty()) {
                predicateList.add(cb.equal(cb.lower(task.get("status")), filter.getStatus().toLowerCase()));
            }

            // Filter by priority
            if (filter.getPriority() != null && !filter.getPriority().isEmpty()) {
                predicateList.add(cb.equal(cb.lower(task.get("priority")), filter.getPriority().toLowerCase()));
            }

            // Filter by date
            if (filter.getDate() != null) {
                // Check if date is present, otherwise skip the filter
                predicateList.add(cb.equal(task.get("date"), filter.getDate()));
            }

            return cb.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
