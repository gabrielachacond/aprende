package ar.com.ada.aprende.component.security.data;


import ar.com.ada.aprende.model.entity.security.Authority;
import ar.com.ada.aprende.model.entity.security.User;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Component
@Order(7)
public class DataUserLoader implements ApplicationRunner {

    public static final Logger LOGGER = LoggerFactory.getLogger(DataUserLoader.class);

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Autowired
    @Qualifier("authorityRepository")
    private AuthorityRepository authorityRepository;

    @Value("${spring.application.env}")
    private String appEnv;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        LOGGER.info("DataUserLoader.run");

        if (appEnv.equals("dev") || appEnv.equals("qa")) {
            LOGGER.info("Loading initial data Users");

            Authority adminAuthority = authorityRepository.findById(1L)
                    .orElseThrow(() -> new SQLException("Authority Not Found"));

            Authority representativeAuthority = authorityRepository.findById(2L)
                    .orElseThrow(() -> new SQLException("Authority Not Found"));

            Authority participantAuthority = authorityRepository.findById(3L)
                    .orElseThrow(() -> new SQLException("Authority Not Found"));

            User admin = new User()
                    .setId(1L).setUsername("admin")
                    .setPassword(passwordEncoder.encode("admin"))
                    .setFirstName("admin").setLastName("admin")
                    .setEmail("admin@admin.com").setEnabled(true)
                    .addAuthority(adminAuthority)
                    .addAuthority(representativeAuthority)
                    .addAuthority(participantAuthority);

            User representative = new User()
                    .setId(2L).setUsername("representative")
                    .setPassword(passwordEncoder.encode("representative"))
                    .setFirstName("representative").setLastName("representative")
                    .setEmail("representative@representative.com").setEnabled(true)
                    .addAuthority(representativeAuthority)
                    .addAuthority(participantAuthority);

            User participant = new User()
                    .setId(3L).setUsername("participant")
                    .setPassword(passwordEncoder.encode("participant"))
                    .setFirstName("participant").setLastName("participant")
                    .setEmail("participant@participant.com").setEnabled(true)
                    .addAuthority(participantAuthority);

            List<User> userList = Arrays.asList(admin, representative, participant);
            userRepository.saveAll(userList);

        }
    }
}
