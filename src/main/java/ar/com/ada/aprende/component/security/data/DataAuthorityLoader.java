package ar.com.ada.aprende.component.security.data;


import ar.com.ada.aprende.model.entity.security.Authority;
import ar.com.ada.aprende.model.entity.security.AuthorityName;
import ar.com.ada.aprende.model.repository.security.AuthorityRepository;
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
@Order(6)
public class DataAuthorityLoader implements ApplicationRunner {

    public static final Logger LOGGER = LoggerFactory.getLogger(DataAuthorityLoader.class);

    @Autowired
    @Qualifier("authorityRepository")
    private AuthorityRepository authorityRepository;

    @Value("${spring.application.env}")
    private String appEnv;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("DataRoleLoader.run");

        if (appEnv.equals("dev") || appEnv.equals("qa")) {
            LOGGER.info("Loading initial data Authority");


            Authority admin = new Authority()
                    .setId(1L)
                    .setName(AuthorityName.ROLE_ADMIN);

            Authority representative = new Authority()
                    .setId(2L)
                    .setName(AuthorityName.ROLE_REPRESENTATIVE);

            Authority participant = new Authority()
                    .setId(3L)
                    .setName(AuthorityName.ROLE_PARTICIPANT);

            List<Authority> authorityList = Arrays.asList(admin, representative, participant);
            authorityRepository.saveAll(authorityList);

        }
    }
}
