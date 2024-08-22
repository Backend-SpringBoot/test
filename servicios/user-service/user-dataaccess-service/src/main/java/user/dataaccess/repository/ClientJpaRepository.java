package user.dataaccess.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import user.dataaccess.entities.ClientEntity;

public interface ClientJpaRepository extends JpaRepository<ClientEntity, Integer> {

    Optional<ClientEntity> findByIdNumber(String idNumber);

    void deleteByIdNumber(String idNumber);
}
