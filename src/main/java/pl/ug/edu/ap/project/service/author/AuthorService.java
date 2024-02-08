package pl.ug.edu.ap.project.service.author;

import pl.ug.edu.ap.project.domain.Author;
import pl.ug.edu.ap.project.domain.request.author.CreateAuthorRequest;
import pl.ug.edu.ap.project.domain.request.author.EditAuthorRequest;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();

    Author getAuthor(Long id);

    Author addAuthor(CreateAuthorRequest request);

    Author editAuthor(Long id, EditAuthorRequest request);

    void deleteAuthor(Long id);
}
