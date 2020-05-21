package ar.com.ada.aprende.model.repository;
import ar.com.ada.aprende.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("companyRepository")
public interface CompanyRepository extends JpaRepository <Company, Long>{
}
