package pl.ug.edu.ap.project.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    @Id
    @GeneratedValue
    private Long id;
    private boolean recommended;
    @NotBlank
    private String review;
    @ManyToOne
    @JoinColumn(name = "manga_id")
    @JsonIgnoreProperties
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Manga manga;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
