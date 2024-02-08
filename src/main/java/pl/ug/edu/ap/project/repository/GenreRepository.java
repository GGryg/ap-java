package pl.ug.edu.ap.project.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import pl.ug.edu.ap.project.domain.Genre;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GenreRepository extends ListCrudRepository<Genre, Long> {
    Optional<Genre> findByGenre(String genre);
}
