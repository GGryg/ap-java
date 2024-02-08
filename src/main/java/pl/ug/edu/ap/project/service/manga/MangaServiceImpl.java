package pl.ug.edu.ap.project.service.manga;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.ug.edu.ap.project.domain.Author;
import pl.ug.edu.ap.project.domain.Description;
import pl.ug.edu.ap.project.domain.Genre;
import pl.ug.edu.ap.project.domain.Manga;
import pl.ug.edu.ap.project.domain.request.manga.CreateMangaRequest;
import pl.ug.edu.ap.project.domain.request.manga.EditMangaRequest;
import pl.ug.edu.ap.project.exception.manga.MangaNotFoundException;
import pl.ug.edu.ap.project.repository.AuthorRepository;
import pl.ug.edu.ap.project.repository.GenreRepository;
import pl.ug.edu.ap.project.repository.MangaRepository;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MangaServiceImpl implements MangaService {
    @Autowired
    private final MangaRepository mangaRepository;
    @Autowired
    private final AuthorRepository authorRepository;
    @Autowired
    private final GenreRepository genreRepository;

    public Manga getManga(Long id) {
        return mangaRepository.findById(id)
                .orElseThrow(() -> new MangaNotFoundException(id));
    }

    public List<Manga> getAllManga() {
        return mangaRepository.findAll();
    }

    @Transactional
    public Manga addManga(CreateMangaRequest request) {
        Author author = authorRepository.findByNameAndLastName(request.getAuthorName(), request.getAuthorLastName())
                .orElseGet(() ->
                        authorRepository.save(Author.builder().name(request.getAuthorName()).mangas(new HashSet<>())
                                .lastName(request.getAuthorLastName()).build()));

        Description description = new Description();
        description.setDescription(request.getDescription());
        LocalDate createDate = LocalDate.now();
        if (request.getCreatedDate() != null) {
            createDate = request.getCreatedDate();
        }
        Manga manga = Manga.builder()
                .title(request.getTitle())
                .author(author)
                .chapters(request.getChapters())
                .genres(new HashSet<>())
                .reviews(new ArrayList<>())
                .isFinished(request.isFinished())
                .description(description)
                .createdDate(createDate)
                .build();

        //author.getMangas().add(manga);
        //authorRepository.save(author);

        return mangaRepository.save(manga);
    }

    @Transactional
    public Manga editManga(Long id, EditMangaRequest request) {
        Manga mangaToChange = mangaRepository.findById(id)
                .orElseThrow(() -> new MangaNotFoundException(id));
        request.getTitle().ifPresent(title -> {
            if (!title.isBlank()) {
                mangaToChange.setTitle(title);
            }
        });
        request.getChapters().ifPresent(mangaToChange::setChapters);
        request.getIsFinished().ifPresent(mangaToChange::setFinished);
        request.getDescription().ifPresent(description -> {
            if (!description.isBlank()) {
                Description des = new Description();
                des.setDescription(description);
                mangaToChange.setDescription(des);
            }
        });
        return mangaRepository.save(mangaToChange);
    }

    public void deleteManga(Long id) {
        Manga manga = mangaRepository.findById(id)
                .orElseThrow(() -> new MangaNotFoundException(id));
        mangaRepository.delete(manga);
    }

    @Transactional
    public Manga addGenre(Long id, List<String> genres) {
        Manga foundManga = mangaRepository.findById(id)
                .orElseThrow(() -> new MangaNotFoundException(id));

        for (String genre : genres) {
            Genre gen = genreRepository.findByGenre(genre)
                    .orElseGet(() -> genreRepository.save(Genre.builder().genre(genre).build()));
            foundManga.getGenres().add(gen);
        }

        return mangaRepository.save(foundManga);
    }

    public List<Object[]> countMangaByAuthors() {
        return mangaRepository.countMangaByAuthor();
    }

    public Page<Manga> searchManga(String title, String authorName, String authorLastName, String description,
                                   Boolean finished, List<String> genres, Pageable pageable) {
        return mangaRepository.searchManga(title, authorName, authorLastName, description, finished, genres, pageable);
    }
}
