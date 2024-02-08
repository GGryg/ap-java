package pl.ug.edu.ap.project.domain.request.review;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Optional;

@Data
public class EditReviewRequest {
    private Optional<@Size(min = 1, message = "Too short") String> review;
    private Optional<Boolean> recommended;
}
