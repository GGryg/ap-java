package pl.ug.edu.ap.project.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MangaDTO {
    private long id;
    private String title;
    private String authorId;
    private String authorName;
    private String authorLastName;
    private DescriptionDTO description;
    private List<String> genres;
    private int chapters;
    private boolean finished;
    private LocalDate createdDate;
}