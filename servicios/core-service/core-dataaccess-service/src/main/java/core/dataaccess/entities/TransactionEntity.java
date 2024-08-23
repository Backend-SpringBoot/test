package core.dataaccess.entities;

import core.dataaccess.entities.core.impl.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@DynamicUpdate
@DynamicInsert
//@Audited
@Table(
        name = "transaction",
        schema = "core"
)
@EntityListeners(AuditingEntityListener.class)
public class TransactionEntity extends AbstractEntity<Integer, Integer> {

    @Column(name = "transacitionDate", nullable = false)
    private LocalDateTime transacitionDate;

    @NotBlank(message = "{Tipo account no debe estar en blanco")
    @Size(max = 25, message = "{Tamaño maximo de 25")
    @Column(name = "accountType", nullable = false)
    private String accountType;

    @Column(name = "initialBalance", nullable = false)
    @NotNull(message = "Saldo inicial no puede ser nulo")
    private double initialBalance;

    @Column(name = "availableBalance", nullable = false)
    @NotNull(message = "Saldo disponible no debe ser nulo")
    @PositiveOrZero(message = "Saldo disponible debe ser positivo")
    private double availableBalance;

    @NotBlank(message = "{Identificación no debe estar en blanco")
    @Size(max = 25, message = "{Tamaño maximo de 25")
    @Column(name = "client", nullable = false)
    private String client;

    @NotBlank(message = "{Número account no debe estar en blanco")
    @Size(max = 25, message = "{Tamaño maximo de 25 ")
    @Column(name = "accountNumber", nullable = false)
    private String accountNumber;

    @NotNull(message = "Movimiento disponible no debe ser nulo")
    @Positive(message = "Movimiento disponible debe ser positivo")
    @Column(name = "movement", nullable = false)
    private double movement;

    @NotBlank(message = "{Tipo movimiento no debe estar en blanco")
    @Size(max = 25, message = "{Tamaño maximo de 25 ")
    @Column(name = "movementType", nullable = false)
    private String movementType;
}
