package ar.com.ada.aprende.component.data;

import ar.com.ada.aprende.model.repository.CompanyRepository;
import ar.com.ada.aprende.model.repository.CompanyRepresentativeRepository;
import ar.com.ada.aprende.model.repository.TypeCategoryCompanyRepository;
import ar.com.ada.aprende.model.repository.TypeDocumentRepository;
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
    @Qualifier("companyRepository")
    private CompanyRepository companyRepository;

    @Autowired
    @Qualifier("typeDocumentRepository")
    private TypeDocumentRepository typeDocumentRepository;

    @Autowired
    @Qualifier("companyRepresentativeRepository")
    private CompanyRepresentativeRepository companyRepresentativeRepository;


    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        if (appEnv.equals("dev") || appEnv.equals("qa")) {
            LOGGER.info("Data Cleaner initializer...\n");

            // Para borrar los registros de las tablas y por eso las elimine de Categorydata
            companyRepresentativeRepository.deleteAll();
            companyRepository.deleteAll();
            typeCategoryCompanyRepository.deleteAll();
            typeDocumentRepository.deleteAll();

            // para Reiniciar los indices de las tablas ''
            companyRepresentativeRepository.resetAutoincrementValue();
            companyRepository.resetAutoincrementValue();
            typeCategoryCompanyRepository.resetAutoincrementValue();
            typeDocumentRepository.resetAutoincrementValue();

        }
    }
}
