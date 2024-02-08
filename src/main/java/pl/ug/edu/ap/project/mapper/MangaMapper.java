package pl.ug.edu.ap.project.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import pl.ug.edu.ap.project.domain.Genre;
import pl.ug.edu.ap.project.domain.Manga;
import pl.ug.edu.ap.project.domain.response.MangaDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MangaMapper {
    MangaMapper INSTANCE = Mappers.getMapper(MangaMapper.class);

    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "authorName", source = "author.name")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "genres", source = "genres", qualifiedByName = "extractGenres")
    MangaDTO mangaToMangaDTO(Manga manga);

    List<MangaDTO> mangasToMangaDTOS(List<Manga> mangas);

    default Page<MangaDTO> pageMangaToPageMangaDTO(Page<Manga> page) {
        return page.map(this::mangaToMangaDTO);
    }

    @Named("extractGenres")
    default List<String> extractGenres(Set<Genre> genres) {
        return genres.stream().map(Genre::getGenre).collect(Collectors.toList());
    }
}
