package test.dataaccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import test.dataaccess.entities.AccountEntity;

import java.util.Optional;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, Integer> {

    Optional<AccountEntity> findByAccountNumber(String accountNumber);

    @Query("SELECT c FROM AccountEntity c "
            + "JOIN c.client p WHERE p.idNumber = :idNumber "
            + "AND c.accountType = :accountType AND c.accountNumber = :accountNumber  ")
    Optional<AccountEntity> findAccount(String idNumber, String accountType, String accountNumber);

    @Query("SELECT c FROM AccountEntity c "
            + "JOIN c.client p WHERE p.idNumber = :idNumber "
            + "AND c.accountNumber = :accountNumber  "
            + "AND c.accountType = :accountType")
    Optional<AccountEntity> findAccountByUserAndAccountNumber(String idNumber,
                                                              String accountNumber, String accountType);

}
