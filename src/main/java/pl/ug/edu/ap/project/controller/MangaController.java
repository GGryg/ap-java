package pl.ug.edu.ap.project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.ug.edu.ap.project.domain.Manga;
import pl.ug.edu.ap.project.domain.request.manga.CreateMangaRequest;
import pl.ug.edu.ap.project.domain.request.manga.EditMangaRequest;
import pl.ug.edu.ap.project.domain.response.MangaDTO;
import pl.ug.edu.ap.project.mapper.MangaMapper;
import pl.ug.edu.ap.project.service.manga.MangaService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/manga")
@RequiredArgsConstructor
public class MangaController {
    @Autowired
    private final MangaService mangaService;

    @GetMapping
    public ResponseEntity<List<MangaDTO>> getAllManga() {
        return ResponseEntity.ok(MangaMapper.INSTANCE.mangasToMangaDTOS(mangaService.getAllManga()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MangaDTO> getManga(@PathVariable("id") Long id) {
        return ResponseEntity.ok(MangaMapper.INSTANCE.mangaToMangaDTO(mangaService.getManga(id)));
    }

    @PostMapping
    public ResponseEntity<MangaDTO> createManga(@Valid @RequestBody CreateMangaRequest request) {
        Manga createdManga = mangaService.addManga(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdManga.getId())
                .toUri();

        return ResponseEntity.created(location).body(MangaMapper.INSTANCE.mangaToMangaDTO(createdManga));
    }

    @PatchMapping("/{id}/addGenre")
    public ResponseEntity<MangaDTO> addGenresToManga(@PathVariable("id") Long id, @RequestBody List<String> genres) {
        return ResponseEntity.ok().body(MangaMapper.INSTANCE.mangaToMangaDTO(mangaService.addGenre(id, genres)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MangaDTO> editManga(@PathVariable("id") Long id, @Valid @RequestBody EditMangaRequest request) {
        return ResponseEntity.ok().body(MangaMapper.INSTANCE.mangaToMangaDTO(mangaService.editManga(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManga(@PathVariable("id") Long id) {
        mangaService.deleteManga(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<List<Object[]>> countManga() {
        return ResponseEntity.ok(mangaService.countMangaByAuthors());
    }

    @GetMapping("/search")
    public ResponseEntity<Page<MangaDTO>> searchManga(@RequestParam(value = "title", required = false) String title,
                                                      @RequestParam(value = "authorName", required = false) String authorName,
                                                      @RequestParam(value = "authorLastName", required = false) String authorLastName,
                                                      @RequestParam(value = "description", required = false) String description,
                                                      @RequestParam(value = "finished", required = false) Boolean finished,
                                                      @RequestParam(value = "genres", required = false) List<String> genres,
                                                      @RequestParam(value = "page", defaultValue = "0") int page,
                                                      @RequestParam(value = "size", defaultValue = "10") int size,
                                                      @RequestParam(value = "sortBy", defaultValue = "title,chapters") String[] sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Manga> pageManga = mangaService.searchManga(title, authorName,
                authorLastName, description, finished, genres, pageable);
        Page<MangaDTO> mangaDTOs = MangaMapper.INSTANCE.pageMangaToPageMangaDTO(pageManga);

        return ResponseEntity.ok(mangaDTOs);
    }
}
