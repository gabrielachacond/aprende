package ar.com.ada.aprende.component.data;

import ar.com.ada.aprende.model.entity.TypeCategoryCompany;
import ar.com.ada.aprende.model.repository.CompanyRepository;
import ar.com.ada.aprende.model.repository.TypeCategoryCompanyRepository;
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
public class TypeCategoryCompanyLoaderData implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(TypeCategoryCompanyLoaderData.class);

    @Value("${spring.application.env}")
    private String appEnv;

    @Autowired
    @Qualifier("typeCategoryCompanyRepository")
    private TypeCategoryCompanyRepository typeCategoryCompanyRepository;

    @Autowired
    @Qualifier("companyRepository")
    private CompanyRepository companyRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        if (appEnv.equals("dev") || appEnv.equals("qa")) {
            LOGGER.info("Loading TypeCategoryCompany initial data...");

            // borra los registros de las tablas
            companyRepository.deleteAll(); // borra las compañias registradas
            typeCategoryCompanyRepository.deleteAll(); // borra las categorias
            companyRepository.resetAutoincrementValue(); // se reinicia el contador del auto_incremente
            typeCategoryCompanyRepository.resetAutoincrementValue(); // se reinicia el contador del auto_incremente

            List<TypeCategoryCompany> typeCategoryCompanyList = Arrays.asList(
                    new TypeCategoryCompany()
                            .setId(1L)
                            .setCaterory("Category Company 1"),
                    new TypeCategoryCompany()
                            .setId(2L)
                            .setCaterory("Category Company 2"),
                    new TypeCategoryCompany()
                            .setId(3L)
                            .setCaterory("Category Company 3")
            );
            typeCategoryCompanyRepository.saveAll(typeCategoryCompanyList);
        }
    }
}