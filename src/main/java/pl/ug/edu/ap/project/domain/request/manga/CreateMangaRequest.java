package pl.ug.edu.ap.project.domain.request.manga;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class CreateMangaRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String authorName;
    @NotBlank
    private String authorLastName;
    @NotBlank
    private String description;
    @Min(0)
    private int chapters;
    private boolean finished;
    @Nullable
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;
}
