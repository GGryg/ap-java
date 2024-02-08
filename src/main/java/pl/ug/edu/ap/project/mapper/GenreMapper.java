package pl.ug.edu.ap.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.ug.edu.ap.project.domain.Genre;
import pl.ug.edu.ap.project.domain.response.GenreDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    GenreDTO genreToGenreDTO(Genre genre);

    List<GenreDTO> genresToGenreDTOs(List<Genre> genres);
}
