package ar.com.ada.aprende.component.data;

import ar.com.ada.aprende.model.entity.TypeDocument;
import ar.com.ada.aprende.model.repository.CompanyRepresentativeRepository;
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

import java.util.Arrays;
import java.util.List;

@Component
public class TypeDocumentLoaderData implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(TypeCategoryCompanyLoaderData.class);

    @Value("${spring.application.env}")
    private String appEnv;

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
            LOGGER.info("Loading TypeDocument initial data...\n");

            List<TypeDocument> typeDocumentList = Arrays.asList(
                    new TypeDocument().setId(1L).setType("DNI"),
                    new TypeDocument().setId(2L).setType("PASAPORTE"),
                    new TypeDocument().setId(3L).setType("LC"),
                    new TypeDocument().setId(4L).setType("LE")
            );
            typeDocumentRepository.saveAll(typeDocumentList);
        }
    }
}
