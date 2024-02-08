package pl.ug.edu.ap.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.ug.edu.ap.project.domain.Review;
import pl.ug.edu.ap.project.domain.response.ReviewDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @Mapping(target = "mangaId", source = "manga.id")
    @Mapping(target = "mangaTitle", source = "manga.title")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    ReviewDTO reviewToReviewDTO(Review review);

    List<ReviewDTO> reviewsToReviewDTOs(List<Review> reviews);
}
