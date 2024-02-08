package pl.ug.edu.ap.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.ug.edu.ap.project.domain.Manga;

import java.util.List;
import java.util.UUID;

@Repository
public interface MangaRepository extends ListCrudRepository<Manga, Long> {
    @Query("Select a.name, a.lastName, count(m) from Manga m join m.author a group by a.name, a.lastName")
    List<Object[]> countMangaByAuthor();

    @Query("Select m from Manga m join m.genres g join m.description d " +
            "join m.author a where (:title is null or m.title like %:title%) " +
            "and (:authorName is null or a.name like %:authorName%) " +
            "and (:authorLastName is null or a.lastName like %:authorLastName%) " +
            "and (:description is null or d.description like %:description%) " +
            "and (:finished is null or m.isFinished = :finished) " +
            "and (:genres is null or g.genre in :genres)")
    Page<Manga> searchManga(@Param("title") String title, @Param("authorName") String authorName,
                            @Param("authorLastName") String authorLastName, @Param("description") String description,
                            @Param("finished") Boolean finished, @Param("genres") List<String> genres,
                            Pageable pageable);
}
