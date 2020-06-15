package ar.com.ada.aprende.model.mapper;

import ar.com.ada.aprende.model.dto.CourseDTO;
import ar.com.ada.aprende.model.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseMapper extends DataCycleMapper<CourseDTO, Course> {
    CourseMapper MAPPER = Mappers.getMapper(CourseMapper.class);
}
