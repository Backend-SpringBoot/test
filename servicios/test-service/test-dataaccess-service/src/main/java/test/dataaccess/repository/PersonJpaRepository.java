package test.dataaccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import test.dataaccess.entities.PersonEntity;

import java.util.Optional;

public interface PersonJpaRepository extends JpaRepository<PersonEntity, Integer> {

    @Query("""
            SELECT p FROM PersonEntity p WHERE p.idNumber = :idNumber AND p.status = 'TRUE'""")
    Optional<PersonEntity> findByIdNumber(String idNumber);

    void deletePersonEntityByIdNumber(String id);

}
