package test.dataaccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import test.dataaccess.entities.TransactionEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionJpaRepository extends JpaRepository<TransactionEntity, Integer> {

    List<TransactionEntity> findByTransacitionDateBetween(LocalDateTime startDate,
                                                          LocalDateTime endDate);

    @Query("SELECT m FROM TransactionEntity m where m.accountNumber = :accountNumber")
    Optional<TransactionEntity> findByAccountNumber(String accountNumber);
}
