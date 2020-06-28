package ar.com.ada.aprende.component.data;

import ar.com.ada.aprende.model.entity.TypeCategoryCourse;
import ar.com.ada.aprende.model.repository.TypeCategoryCourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;


@Component
@Order(2)
public class TypeCategoryCourseLoaderData implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(TypeCategoryCourseLoaderData.class);

    @Value("${spring.application.env}")
    private String appEnv;


    @Autowired
    @Qualifier("typeCategoryCourseRepository")
    private TypeCategoryCourseRepository typeCategoryCourseRepository;


    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        if (appEnv.equals("dev") || appEnv.equals("qa")) {
            LOGGER.info("Loading TypeCategoryCourse initial data...");

            List<TypeCategoryCourse> typeCategoryCourseList = Arrays.asList(
                    new TypeCategoryCourse().setId(1L).setCategory("programacion"),
                    new TypeCategoryCourse().setId(2L).setCategory("diseño"),
                    new TypeCategoryCourse().setId(3L).setCategory("idiomas")
            );
            typeCategoryCourseRepository.saveAll(typeCategoryCourseList);
        }
    }
}
