package pl.ug.edu.ap.project.service.author;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ug.edu.ap.project.domain.Author;
import pl.ug.edu.ap.project.domain.request.author.CreateAuthorRequest;
import pl.ug.edu.ap.project.domain.request.author.EditAuthorRequest;
import pl.ug.edu.ap.project.exception.author.AuthorNotFoundException;
import pl.ug.edu.ap.project.repository.AuthorRepository;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private final AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthor(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
    }

    public Author addAuthor(CreateAuthorRequest request) {
        Author author = new Author();
        author.setName(request.getName());
        author.setLastName(request.getLastName());
        author.setMangas(new HashSet<>());
        return authorRepository.save(author);
    }

    public Author editAuthor(Long id, EditAuthorRequest request) {
        Author foundAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
        request.getName().ifPresent(name -> {
            if (!name.isBlank()) {
                foundAuthor.setName(name);
            }
        });
        request.getLastName().ifPresent(lastName -> {
            if (!lastName.isBlank()) {
                foundAuthor.setLastName(lastName);
            }
        });
        return authorRepository.save(foundAuthor);
    }

    public void deleteAuthor(Long id) {
        Author foundAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
        authorRepository.delete(foundAuthor);
    }

}
