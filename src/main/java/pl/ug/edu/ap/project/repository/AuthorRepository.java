package pl.ug.edu.ap.project.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import pl.ug.edu.ap.project.domain.Author;

import java.util.Optional;

@Repository
public interface AuthorRepository extends ListCrudRepository<Author, Long> {
    @Query("Select a from Author a where a.name=?1 and a.lastName=?2")
    Optional<Author> findByNameAndLastName(String name, String lastName);
}
