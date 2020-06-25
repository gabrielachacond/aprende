package ar.com.ada.aprende.service;

import ar.com.ada.aprende.component.BusinessLogicExceptionComponent;
import ar.com.ada.aprende.model.dto.CourseDTO;
import ar.com.ada.aprende.model.entity.Course;
import ar.com.ada.aprende.model.mapper.CourseMapper;
import ar.com.ada.aprende.model.mapper.CycleAvoidingMappingContext;
import ar.com.ada.aprende.model.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("searchEngineServices")
public class SearchEngineServices {

    @Autowired
    @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired
    @Qualifier("courseRepository")
    private CourseRepository courseRepository;

    @Autowired
    @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    private CourseMapper courseMapper = CourseMapper.MAPPER;


    public List<CourseDTO> getAllCourseByCategory(Long courseCategoryId, Integer page) {
        Page<Course> allByCategoryPage = courseRepository
                .findAllByCategory(courseCategoryId, PageRequest.of(page, 5, Sort.Direction.ASC, "id"));

        List<Course> allByCategory = allByCategoryPage.getContent();
        List<CourseDTO> allByCategoryListDTO = courseMapper.toDto(allByCategory, context);

        return allByCategoryListDTO;
    }

    public List<CourseDTO> getAllCourseByCompany(Long companyId, Integer page) {
        Page<Course> allByCompanyPage = courseRepository
                .findAllByCompany(companyId, PageRequest.of(page, 5, Sort.Direction.ASC, "id"));

        List<Course> allByCompany = allByCompanyPage.getContent();
        List<CourseDTO> allByCompanyListDTO = courseMapper.toDto(allByCompany, context);

        return allByCompanyListDTO;
    }

    public List<CourseDTO> getAllCourseByCompanyAndCategory(Long companyId, Long categoryCourseId, Integer page) {
        Page<Course> allByCompanyAndCategoryPage = courseRepository
                .findAllByCompanyAndCategory(companyId, categoryCourseId, PageRequest.of(page, 5, Sort.Direction.ASC, "id"));

        List<Course> allByCompanyAndCategory = allByCompanyAndCategoryPage.getContent();
        List<CourseDTO> allByCompanyAndCategoryListDTO = courseMapper.toDto(allByCompanyAndCategory, context);

        return allByCompanyAndCategoryListDTO;
    }

    public List<CourseDTO> getAllCourseAvailable(Integer page) {
        Page<Course> allByCourseAvailablePage = courseRepository
                .findAllByCoursesAvailable(PageRequest.of(page, 5, Sort.Direction.ASC, "id"));

        List<Course> allByCourseAvailable = allByCourseAvailablePage.getContent();
        List<CourseDTO> allByCourseAvailableListDTO = courseMapper.toDto(allByCourseAvailable, context);

        return allByCourseAvailableListDTO;
    }

    public List<CourseDTO> getAllCourseByParticipantsProgressStatus(Long courseHasFinish, Integer page) {
        Page<Course> allCourseByParticipantsProgressStatusPage = courseRepository
                .findAllCourseByParticipantsProgressStatus(courseHasFinish, PageRequest.of(page, 5, Sort.Direction.ASC, "id"));

        List<Course> allCourseByParticipantsProgressStatus = allCourseByParticipantsProgressStatusPage.getContent();
        List<CourseDTO> allCourseByParticipantsProgressStatusListDTO = courseMapper.toDto(allCourseByParticipantsProgressStatus, context);

        return allCourseByParticipantsProgressStatusListDTO;
    }


}
// todo Al menos una clase de cada capa con UNIT e INTEGRATIONS test