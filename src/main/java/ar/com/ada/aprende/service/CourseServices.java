package ar.com.ada.aprende.service;

import ar.com.ada.aprende.component.BusinessLogicExceptionComponent;
import ar.com.ada.aprende.model.dto.CourseDTO;
import ar.com.ada.aprende.model.entity.Company;
import ar.com.ada.aprende.model.entity.Course;
import ar.com.ada.aprende.model.entity.TypeCategoryCourse;
import ar.com.ada.aprende.model.entity.TypeModalityCourse;
import ar.com.ada.aprende.model.mapper.CourseMapper;
import ar.com.ada.aprende.model.mapper.CycleAvoidingMappingContext;
import ar.com.ada.aprende.model.repository.CompanyRepository;
import ar.com.ada.aprende.model.repository.CourseRepository;
import ar.com.ada.aprende.model.repository.TypeCategoryCourseRepository;
import ar.com.ada.aprende.model.repository.TypeModalityCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("courseServices")
public class CourseServices implements Services<CourseDTO> {

    @Autowired
    @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired
    @Qualifier("typeCategoryCourseRepository")
    private TypeCategoryCourseRepository typeCategoryCourseRepository;

    @Autowired
    @Qualifier("typeModalityCourseRepository")
    private TypeModalityCourseRepository typeModalityCourseRepository;

    @Autowired
    @Qualifier("companyRepository")
    private CompanyRepository companyRepository;

    @Autowired
    @Qualifier("courseRepository")
    private CourseRepository courseRepository;

    @Autowired
    @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    private CourseMapper courseMapper = CourseMapper.MAPPER;


    @Override
    public List<CourseDTO> findAll() {
        return null;
    }

    @Override
    public CourseDTO save(CourseDTO dto) {
        Long companyId = dto.getCompanyId();
        Company company = companyRepository
                .findById(companyId)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("Company", companyId));

        Long typeCategoryCourseId = dto.getTypeCategoryCourse().getId();
        TypeCategoryCourse typeCategoryCourse = typeCategoryCourseRepository
                .findById(typeCategoryCourseId)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("TypeCategoryCourse", typeCategoryCourseId));

        Long typeModalityCourseId = dto.getTypeModalityCourse().getId();
        TypeModalityCourse typeModalityCourse = typeModalityCourseRepository
                .findById(typeModalityCourseId)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("TypeModalityCourse", typeModalityCourseId));
        Course courseToSave = courseMapper.toEntity(dto, context);
        courseToSave.setCompany(company);
        courseToSave.setTypeCategoryCourse(typeCategoryCourse);
        courseToSave.setTypeModalityCourse(typeModalityCourse);

        Course courseSaved = courseRepository.save(courseToSave);
        CourseDTO courseDTOSaved = courseMapper.toDto(courseSaved, context);

        return courseDTOSaved;
    }

    @Override
    public void delete(Long id) {

    }
}
