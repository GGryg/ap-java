package pl.ug.edu.ap.project.domain.request.author;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateAuthorRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
}
