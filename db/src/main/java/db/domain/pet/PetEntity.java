package db.domain.pet;

import db.common.BaseEntity;
import db.domain.pet.enums.PetCategory;
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

    private String name;

    private LocalDate birth;

    private String address;

    @Enumerated(EnumType.STRING)
    private PetCategory category;

    private float weight;

    private String phone;

    private LocalDateTime registeredAt;

    private Long userId;

}
