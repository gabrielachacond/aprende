package ar.com.ada.aprende.component.data;


import ar.com.ada.aprende.model.entity.*;
import ar.com.ada.aprende.model.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Year;
import java.util.*;

@Component
@Order(5)
public class ParticipantCourseLoaderData implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParticipantCourseLoaderData.class);

    private final List<String> stringNamesList = Arrays.asList(
            "Lorem", "ipsum",
            "dolor", "sit",
            "amet", "consectetur",
            "adipiscing", "elit"
    );

    private final List<String> participantsNamesList = Arrays.asList(
            "Mario", "Juan",
            "Gabriela", "Paulina",
            "Sofia", "Pedro"
    );

    private final List<String> participantsLastNamesList = Arrays.asList(
            "Gomez", "Gonzales",
            "Ramirez", "Chacon",
            "Maidana", "Parra"
    );

    @Value("${spring.application.env}")
    private String appEnv;

    @Autowired
    @Qualifier("typeCategoryCompanyRepository")
    private TypeCategoryCompanyRepository typeCategoryCompanyRepository;

    @Autowired
    @Qualifier("typeModalityCourseRepository")
    private TypeModalityCourseRepository typeModalityCourseRepository;

    @Autowired
    @Qualifier("typeCategoryCourseRepository")
    private TypeCategoryCourseRepository typeCategoryCourseRepository;

    @Autowired
    @Qualifier("courseHasParticipantRepository")
    private CourseHasParticipantRepository courseHasParticipantRepository;

    @Autowired
    @Qualifier("courseRepository")
    private CourseRepository courseRepository;

    @Autowired
    @Qualifier("participantRepository")
    private ParticipantRepository participantRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (appEnv.equals("dev") || appEnv.equals("qa")) {
            LOGGER.info("ParticipantCourseLoaderData initializer...\n");

            Set<CourseHasParticipant> courseHasParticipantSet = new HashSet<>();
            Set<Course> courses = new HashSet<>();
            Set<Participant> participants = new HashSet<>();

            for (int i = 0; i < 20; i++) {
                Random rd = new Random();
                Course course = getCourseObject(rd.nextBoolean());
                courses.add(course);
                Participant participant = getParticipantObject();
                participants.add(participant);
            }

            List<Course> coursesSaved = courseRepository.saveAll(courses);
            List<Participant> participantsSaved = participantRepository.saveAll(participants);

            for (int i = 0; i < 40; i++) {
                Random rd = new Random();
                Course course = getRandomElement(coursesSaved);
                Participant participant = getRandomElement(participantsSaved);

                CourseHasParticipantId id = new CourseHasParticipantId()
                        .setCourseId(course.getId())
                        .setParticipantId(participant.getId());

                CourseHasParticipant courseHasParticipant = new CourseHasParticipant()
                        .setId(id)
                        .setCourse(course)
                        .setParticipant(participant);

                boolean b = rd.nextBoolean();
                courseHasParticipant.setApprovalRate(b ? 75 : 0);
                courseHasParticipant.setCourseHasFinish(course.getPurchasedCouponCounter() == 0);
                courseHasParticipant.setIsApproved(b);
                courseHasParticipant.setIsScholaship(b);

                courseHasParticipantSet.add(courseHasParticipant);
            }

            courseHasParticipantRepository.saveAll(courseHasParticipantSet);

        }

    }

    private Participant getParticipantObject() {
        String address = getRandomElement(stringNamesList) + " " +
                getRandomElement(stringNamesList) + " " +
                getRandomElement(stringNamesList);
        String name = getRandomElement(participantsNamesList);
        String lastName = getRandomElement(participantsLastNamesList);
        return new Participant()
                .setName(name)
                .setLastName(lastName)
                .setAddress(address)
                .setGender("prefiero no decirlo")
                .setBirthday(LocalDate.parse("2016-08-16"));
    }

    private Course getCourseObject(Boolean b) {
        String nameCourse = getRandomElement(stringNamesList) + " " + getRandomElement(stringNamesList);
        String descriptionCourse = getRandomElement(stringNamesList) + " " +
                getRandomElement(stringNamesList) + " " +
                getRandomElement(stringNamesList) + " " +
                getRandomElement(stringNamesList) + " " +
                getRandomElement(stringNamesList);
        return new Course()
                .setNameCourse(nameCourse)
                .setDescriptionCourse(descriptionCourse)
                .setCostCourse(generateRandomDigits(5))
                .setHoursCourse(generateRandomDigits(3))
                .setTotalPaticipants(50)
                .setPlacesToScholarship(10)
                .setPurchasedCouponCounter(b ? 40 : 0)
                .setScholarshipCouponCounter(b ? 10 : 0)
                .setTypeCategoryCourse(getTypeCategoryCourseObject())
                .setTypeModalityCourse(getTypeModalityCourseObject())
                .setCompany(getCompanyObject());

    }

    private Company getCompanyObject() {
        String nameCompany = getRandomElement(stringNamesList) + " " + getRandomElement(stringNamesList);
        return new Company()
                .setNameCompany(nameCompany)
                .setCuil(Long.valueOf(generateRandomDigits(12)))
                .setAddressCompany("Lorem ipsum dolor sit amet")
                .setContactNumber(generateRandomDigits(10))
                .setFundationYear(Year.of(2010))
                .setTypeCompany("CA")
                .setTypeCategoryCompany(getTypeCategoryCompanyObject());
    }

    private TypeCategoryCompany getTypeCategoryCompanyObject() {
        List<Long> idsTypeCategoryCompanyList = Arrays.asList(1L, 2L, 3L);
        Long id = getRandomElement(idsTypeCategoryCompanyList);
        return typeCategoryCompanyRepository.findById(id).get();
    }

    private TypeModalityCourse getTypeModalityCourseObject() {
        List<Long> idsTypeCategoryCompanyList = Arrays.asList(1L, 2L);
        Long id = getRandomElement(idsTypeCategoryCompanyList);
        return typeModalityCourseRepository.findById(id).get();
    }

    private TypeCategoryCourse getTypeCategoryCourseObject() {
        List<Long> idsTypeCategoryCompanyList = Arrays.asList(1L, 2L, 3L);
        Long id = getRandomElement(idsTypeCategoryCompanyList);
        return typeCategoryCourseRepository.findById(id).get();
    }

    private <T> T getRandomElement(List<T> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    private Integer generateRandomDigits(int n) {
        int m = (int) Math.pow(10, n - 1);
        return m + new Random().nextInt(9 * m);
    }
}