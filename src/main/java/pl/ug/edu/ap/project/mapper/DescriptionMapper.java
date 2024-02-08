package pl.ug.edu.ap.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.ug.edu.ap.project.domain.Description;
import pl.ug.edu.ap.project.domain.response.DescriptionDTO;

@Mapper(componentModel = "spring")
public interface DescriptionMapper {
    DescriptionMapper INSTANCE = Mappers.getMapper(DescriptionMapper.class);

    DescriptionDTO descriptionToDescriptionDTO(Description description);
}
