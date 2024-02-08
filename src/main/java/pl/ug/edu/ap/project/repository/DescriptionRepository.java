package pl.ug.edu.ap.project.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import pl.ug.edu.ap.project.domain.Description;

import java.util.UUID;

@Repository
public interface DescriptionRepository extends ListCrudRepository<Description, Long> {
}
