package core.dataaccess.repository;

import core.dataaccess.entities.AccountEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, Integer> {

  Optional<AccountEntity> findByAccountNumber(String accountNumber);

  @Query("SELECT c FROM AccountEntity c "
      + " WHERE c.idNumber = :idNumber "
      + "AND c.accountNumber = :accountNumber  "
      + "AND c.accountType = :accountType")
  Optional<AccountEntity> findAccountByUserAndAccountNumber(String idNumber,
      String accountNumber, String accountType);

  List<AccountEntity> findByIdNumber(String idNumber);

}
