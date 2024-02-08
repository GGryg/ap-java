package pl.ug.edu.ap.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import pl.ug.edu.ap.project.domain.Author;
import pl.ug.edu.ap.project.domain.Manga;
import pl.ug.edu.ap.project.domain.response.AuthorDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    @Mapping(target = "mangasTitles", source = "mangas", qualifiedByName = "extractMangaTitles")
    AuthorDTO authorToAuthorDTO(Author author);

    List<AuthorDTO> authorsToAuthorDTOs(List<Author> authors);

    @Named("extractMangaTitles")
    default List<String> extractMangaTitles(Set<Manga> mangas) {
        return mangas.stream().map(Manga::getTitle).collect(Collectors.toList());
    }
}
