package ar.com.ada.aprende.model.mapper;

import ar.com.ada.aprende.model.dto.CourseDTO;
import ar.com.ada.aprende.model.entity.Course;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseMapper extends DataCycleMapper<CourseDTO, Course> {
    CourseMapper MAPPER = Mappers.getMapper(CourseMapper.class);

    @InheritInverseConfiguration
    @Mapping(target = "companyId", ignore = true)
    CourseDTO toDto(Course entity, @Context CycleAvoidingMappingContext context);
}
