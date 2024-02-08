package pl.ug.edu.ap.project.domain.request.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateReviewRequest {
    @NotNull
    private boolean recommended;
    @NotBlank
    private String review;
    // userId is only here for simplicity
    @NotNull
    private Long userId;
}
