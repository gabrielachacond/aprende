package ar.com.ada.aprende.component.data;

import ar.com.ada.aprende.model.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataCleaner implements ApplicationRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(TypeCategoryCompanyLoaderData.class);

    @Value("${spring.application.env}")
    private String appEnv;

    @Autowired
    @Qualifier("typeCategoryCompanyRepository")
    private TypeCategoryCompanyRepository typeCategoryCompanyRepository;

    @Autowired
    @Qualifier("courseRepository")
    private CourseRepository courseRepository;

    @Autowired
    @Qualifier("companyRepository")
    private CompanyRepository companyRepository;

    @Autowired
    @Qualifier("typeDocumentRepository")
    private TypeDocumentRepository typeDocumentRepository;

    @Autowired
    @Qualifier("companyRepresentativeRepository")
    private CompanyRepresentativeRepository companyRepresentativeRepository;

    @Autowired
    @Qualifier("typeCategoryCourseRepository")
    private TypeCategoryCourseRepository typeCategoryCourseRepository;

    @Autowired
    @Qualifier("typeModalityCourseRepository")
    private TypeModalityCourseRepository typeModalityCourseRepository;


    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        if (appEnv.equals("dev") || appEnv.equals("qa")) {
            LOGGER.info("Data Cleaner initializer...\n");

            // Para borrar los registros de las tablas y por eso las elimine de Categorydata
            companyRepresentativeRepository.deleteAll();
            companyRepresentativeRepository.resetAutoincrementValue();
            courseRepository.deleteAll();
            courseRepository.resetAutoincrementValue();
            companyRepository.deleteAll();
            companyRepository.resetAutoincrementValue();
            typeCategoryCompanyRepository.deleteAll();
            typeCategoryCompanyRepository.resetAutoincrementValue();
            typeDocumentRepository.deleteAll();
            typeDocumentRepository.resetAutoincrementValue();
            typeCategoryCourseRepository.deleteAll();
            typeCategoryCourseRepository.resetAutoincrementValue();
            typeModalityCourseRepository.deleteAll();
            typeModalityCourseRepository.resetAutoincrementValue();
        }
    }
}
