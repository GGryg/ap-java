package pl.ug.edu.ap.project.service.manga;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.ug.edu.ap.project.domain.Manga;
import pl.ug.edu.ap.project.domain.request.manga.CreateMangaRequest;
import pl.ug.edu.ap.project.domain.request.manga.EditMangaRequest;

import java.util.List;

public interface MangaService {
    List<Manga> getAllManga();

    Manga getManga(Long id);

    Manga addManga(CreateMangaRequest mangaRequest);

    Manga editManga(Long id, EditMangaRequest request);

    void deleteManga(Long id);

    Manga addGenre(Long id, List<String> genres);

    List<Object[]> countMangaByAuthors();

    Page<Manga> searchManga(String title, String authorName, String authorLastName, String description,
                            Boolean finished, List<String> genres, Pageable pageable);
}
