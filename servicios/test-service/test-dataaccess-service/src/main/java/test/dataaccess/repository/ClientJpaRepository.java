package test.dataaccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.dataaccess.entities.ClientEntity;

import java.util.Optional;

public interface ClientJpaRepository extends JpaRepository<ClientEntity, Integer> {

    Optional<ClientEntity> findByIdNumber(String idNumber);

    void deleteByIdNumber(String idNumber);
}
