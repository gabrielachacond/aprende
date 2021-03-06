package ar.com.ada.aprende.component.data;

import ar.com.ada.aprende.model.repository.*;
import ar.com.ada.aprende.model.repository.security.AuthorityRepository;
import ar.com.ada.aprende.model.repository.security.UserRepository;
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


@Component
@Order(0)
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

    @Autowired
    @Qualifier("courseHasParticipantRepository")
    private CourseHasParticipantRepository courseHasParticipantRepository;

    @Autowired
    @Qualifier("socioEconomyStudyRepository")
    private SocioEconomyStudyRepository socioEconomyStudyRepository;

    @Autowired
    @Qualifier("participantRepository")
    private ParticipantRepository participantRepository;

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Autowired
    @Qualifier("authorityRepository")
    private AuthorityRepository authorityRepository;


    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        if (appEnv.equals("dev") || appEnv.equals("qa")) {
            LOGGER.info("Data Cleaner initializer...\n");

            userRepository.deletaAllUserHasAuthority();
            userRepository.deleteAll();
            userRepository.resetAutoincrementValue();
            authorityRepository.deleteAll();
            authorityRepository.resetAutoincrementValue();
            courseHasParticipantRepository.deleteAll();
            courseHasParticipantRepository.resetAutoincrementValue();
            socioEconomyStudyRepository.deleteAll();
            socioEconomyStudyRepository.resetAutoincrementValue();
            participantRepository.deleteAll();
            participantRepository.resetAutoincrementValue();
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
