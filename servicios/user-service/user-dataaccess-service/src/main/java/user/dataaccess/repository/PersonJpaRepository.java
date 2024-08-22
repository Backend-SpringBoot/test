package user.dataaccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface PersonJpaRepository extends JpaRepository<user.dataaccess.entities.PersonEntity, Integer> {

    @Query("""
            SELECT p FROM PersonEntity p WHERE p.idNumber = :idNumber AND p.status = 'TRUE'""")
    Optional<user.dataaccess.entities.PersonEntity> findByIdNumber(String idNumber);

    void deletePersonEntityByIdNumber(String id);

}
