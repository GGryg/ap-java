package pl.ug.edu.ap.project.exception.manga;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such manga ID in DB")
public class MangaNotFoundException extends RuntimeException {
    public MangaNotFoundException(Long id) {
        super("Manga not found with ID:" + id);
    }
}
