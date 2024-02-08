package pl.ug.edu.ap.project.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    @NotBlank
    @Size(min = 2, message = "Too short")
    @Size(max = 50, message = "Too long")
    @Column(unique = true)
    private String username;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    List<Review> reviews;
}
