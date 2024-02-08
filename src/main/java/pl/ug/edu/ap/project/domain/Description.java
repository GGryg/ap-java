package pl.ug.edu.ap.project.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Description {
    @Id
    @GeneratedValue
    private Long id;
    private String description;
}
