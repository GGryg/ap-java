package pl.ug.edu.ap.project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.ug.edu.ap.project.domain.Author;
import pl.ug.edu.ap.project.domain.request.author.CreateAuthorRequest;
import pl.ug.edu.ap.project.domain.request.author.EditAuthorRequest;
import pl.ug.edu.ap.project.domain.response.AuthorDTO;
import pl.ug.edu.ap.project.mapper.AuthorMapper;
import pl.ug.edu.ap.project.mapper.MangaMapper;
import pl.ug.edu.ap.project.service.author.AuthorService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/author")
@RequiredArgsConstructor
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        return ResponseEntity.ok().body(AuthorMapper.INSTANCE.authorsToAuthorDTOs(authorService.getAllAuthors()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(AuthorMapper.INSTANCE.authorToAuthorDTO(authorService.getAuthor(id)));
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@Valid @RequestBody CreateAuthorRequest request) {
        Author createdAuthor = authorService.addAuthor(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdAuthor.getId())
                .toUri();

        return ResponseEntity.created(location).body(AuthorMapper.INSTANCE.authorToAuthorDTO(createdAuthor));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AuthorDTO> editAuthor(@PathVariable("id") Long id, @Valid @RequestBody EditAuthorRequest request) {
        return ResponseEntity.ok().body(AuthorMapper.INSTANCE.authorToAuthorDTO(authorService.editAuthor(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("id") Long id) {
        authorService.deleteAuthor(id);

        return ResponseEntity.noContent().build();
    }
}
