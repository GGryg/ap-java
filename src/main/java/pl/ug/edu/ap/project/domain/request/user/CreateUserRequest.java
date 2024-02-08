package pl.ug.edu.ap.project.domain.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequest {
    @NotBlank
    @Size(min = 2, message = "Too short")
    @Size(max = 50, message = "Too long")
    private String username;
}
