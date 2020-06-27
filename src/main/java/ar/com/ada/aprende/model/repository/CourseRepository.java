package ar.com.ada.aprende.model.repository;

import ar.com.ada.aprende.model.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("courseRepository")
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Modifying
    @Query(value = "ALTER TABLE Course AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoincrementValue();

    @Query(value = "SELECT * FROM Course c WHERE c.TypeCategoryCourse_id = :categoryCourseId", nativeQuery = true)
    Page<Course> findAllByCategory(@Param("categoryCourseId") Long categoryCourseId, Pageable pageable);

    @Query(value = "SELECT * FROM Course c WHERE Company_id = :companyId", nativeQuery = true)
    Page<Course> findAllByCompany(@Param("companyId") Long companyId, Pageable pageable);

    @Query(value = "SELECT * FROM Course c WHERE c.Company_id = :companyId AND c.TypeCategoryCourse_id = :categoryCourseId", nativeQuery = true)
    Page<Course> findAllByCompanyAndCategory(@Param("companyId") Long companyId, @Param("categoryCourseId") Long categoryCourseId, Pageable pageable);

    @Query(value = "SELECT * FROM Course c WHERE c.purchasedCouponCounter > 0 OR c.scholarshipCouponCounter > 0", nativeQuery = true)
    Page<Course> findAllByCoursesAvailable(Pageable pageable);

    @Query(
            value = "SELECT c.* FROM Course c INNER JOIN CourseHasParticipant cp ON c.id = cp.course_id WHERE cp.courseHasFinish = :courseHasFinish GROUP BY c.id",
            countQuery = "SELECT DISTINCT COUNT(*) OVER () FROM Course c LEFT JOIN CourseHasParticipant cp ON c.id = cp.course_id WHERE cp.courseHasFinish = :courseHasFinish GROUP BY c.id;",
            nativeQuery = true
    )
    Page<Course> findAllCourseByParticipantsProgressStatus(@Param("courseHasFinish") Boolean courseHasFinish, Pageable pageable);
}
