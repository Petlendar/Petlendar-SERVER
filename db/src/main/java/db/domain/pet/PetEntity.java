package db.domain.pet;

import db.common.BaseEntity;
import db.domain.pet.enums.PetCategory;
import db.domain.pet.enums.PetStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "pet")
public class PetEntity extends BaseEntity {

    @Column(nullable = false, length = 50)
    private String name;

    private LocalDate birth;

    @Column(nullable = false, length = 50)
    private String address;

    @Enumerated(EnumType.STRING)
    private PetCategory category;

    @Column(nullable = false)
    private float weight;

    @Enumerated(EnumType.STRING)
    private PetStatus status;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    @Column(nullable = false)
    private Long userId;

}
