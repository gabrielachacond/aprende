package ar.com.ada.aprende.service;

import ar.com.ada.aprende.model.dto.CourseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

//todo hacer lo que falta
@Service("courseServices")
public class CourseServices implements Services<CourseDTO> {

    @Override
    public List<CourseDTO> findAll() {
        return null;
    }

    @Override
    public CourseDTO save(CourseDTO dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
