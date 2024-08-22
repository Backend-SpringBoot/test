package user.dataaccess.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.concurrent.locks.StampedLock;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@DynamicUpdate
@DynamicInsert
//@Audited
@Table(
        name = "person",
        schema = "user"
)
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(AuditingEntityListener.class)
public class PersonEntity {

    @NotBlank(message = "{Nombre no debe estar en blanco")
    @Size(max = 25, message = "{Tamaño maximo de 25 ")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "{Edad no debe estar en blanco")
    @Size(max = 25, message = "{Tamaño maximo de 25")
    @Column(name = "gender", nullable = false)
    private String gender;

    @NotNull(message = "Edad no debe ser nulo")
    @Positive(message = "Edad debe ser positivo")
    @Column(name = "age", nullable = false)
    private Integer age;

    @Id
    @NotBlank(message = "{Identificación no debe estar en blanco")
    @Size(max = 25, message = "{Tamaño maximo de 25")
    @Column(name = "idNumber", nullable = false)
    private String idNumber;

    @NotBlank(message = "{Dirección no debe estar en blanco")
    @Size(max = 25, message = "{Tamaño maximo de 25")
    @Column(name = "address", nullable = false)
    private String address;

    @NotBlank(message = "{Teléfono no debe estar en blanco")
    @Size(max = 25, message = "{Tamaño maximo de 25")
    @Column(name = "phone", nullable = false)
    private String phone;

    @NotBlank(message = "Estado no puede ser blanco")
    @Size(max = 5, message = "Estado es True o False")
    @Column(name = "status", nullable = false, length = 5)
    private String status;

    @Transient
    private StampedLock lock = new StampedLock();

    public StampedLock getLock() {
        return lock;
    }
}
