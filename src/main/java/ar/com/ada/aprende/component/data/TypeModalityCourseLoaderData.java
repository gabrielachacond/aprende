package ar.com.ada.aprende.component.data;

import ar.com.ada.aprende.model.entity.TypeModalityCourse;
import ar.com.ada.aprende.model.repository.TypeModalityCourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
public class TypeModalityCourseLoaderData implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(TypeModalityCourseLoaderData.class);

    @Value("${spring.application.env}")
    private String appEnv;

    @Autowired
    @Qualifier("typeModalityCourseRepository")
    private TypeModalityCourseRepository typeModalityCourseRepository;


    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        if (appEnv.equals("dev") || appEnv.equals("qa")) {
            LOGGER.info("Loading TypeModalityCourse initial data...");

            List<TypeModalityCourse> typeModalityCourseList = Arrays.asList(
                    new TypeModalityCourse().setId(1L).setModality("online"),
                    new TypeModalityCourse().setId(2L).setModality("presencial")
            );
            typeModalityCourseRepository.saveAll(typeModalityCourseList);
        }
    }
}

