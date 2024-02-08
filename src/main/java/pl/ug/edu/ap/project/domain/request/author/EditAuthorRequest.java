package pl.ug.edu.ap.project.domain.request.author;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Optional;

@Data
public class EditAuthorRequest {
    private Optional<@Size(min = 1, message = "Too short") String> name;
    private Optional<@Size(min = 1, message = "Too short") String> lastName;
}
