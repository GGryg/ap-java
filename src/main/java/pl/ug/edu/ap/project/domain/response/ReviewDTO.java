package pl.ug.edu.ap.project.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDTO {
    private Long id;
    private boolean recommended;
    private String review;
    private long mangaId;
    private String mangaTitle;
    private Long userId;
    private String username;
}