package ar.com.ada.aprende.controller;

import ar.com.ada.aprende.model.dto.CourseDTO;
import ar.com.ada.aprende.service.SearchEngineServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    @Qualifier("searchEngineServices")
    private SearchEngineServices searchEngineServices;

    // http://localhost:8080/search/courses/category/1?page=1
    @GetMapping({"/courses/category/{categoryCourseId}", "/courses/category/{categoryCourseId}/"})
    public ResponseEntity getAllCourseByCategory(@RequestParam Optional<Integer> page, @PathVariable Long categoryCourseId) {

        List<CourseDTO> allCourseByCategory = searchEngineServices
                .getAllCourseByCategory(categoryCourseId, page.orElse(0));

        return ResponseEntity.ok(allCourseByCategory);
    }

    // http://localhost:8080/search/courses/company/1?page=1
    @GetMapping({"/courses/company/{companyId}", "/courses/company/{companyId}/"})
    public ResponseEntity getAllCourseByCompany(@RequestParam Optional<Integer> page, @PathVariable Long companyId) {

        List<CourseDTO> allCourseByCompany = searchEngineServices.getAllCourseByCompany(companyId, page.orElse(0));

        return ResponseEntity.ok(allCourseByCompany);
    }

    // http://localhost:8080/search/courses/company/1/category/1?page=1
    @GetMapping({"/courses/company/{companyId}/category/{categoryCourseId}", "/courses/company/{companyId}/category/{categoryCourseId}/"})
    public ResponseEntity getAllCourseByCompanyAndCategory(@RequestParam Optional<Integer> page, @PathVariable Long companyId, Long categoryCourseId) {

        List<CourseDTO> allCourseByCompanyAndCategory = searchEngineServices.getAllCourseByCompanyAndCategory(companyId, categoryCourseId, page.orElse(0));

        return ResponseEntity.ok(allCourseByCompanyAndCategory);
    }

    // http://localhost:8080/search/courses/participants/status/1?page=1
    @GetMapping({"/courses/participants/status/{courseHasFinish}/", "/courses/available/"})
    public ResponseEntity getAllCourseByParticipantsProgressStatus(@RequestParam Optional<Integer> page, @PathVariable Long courseHasFinish) {

        List<CourseDTO> allCourseByParticipantsProgressStatu = searchEngineServices.getAllCourseByParticipantsProgressStatus(courseHasFinish, page.orElse(0));

        return ResponseEntity.ok(allCourseByParticipantsProgressStatu);
    }

}
