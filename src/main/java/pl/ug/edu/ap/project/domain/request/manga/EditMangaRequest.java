package pl.ug.edu.ap.project.domain.request.manga;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Optional;

@Data
public class EditMangaRequest {
    private Optional<@Size(min = 1) String> title = Optional.empty();
    private Optional<@Size(min = 1) String> description = Optional.empty();
    private Optional<@Min(0) Integer> chapters = Optional.empty();
    private Optional<Boolean> isFinished = Optional.empty();
}
