package db.domain.board;

import db.common.BaseEntity;
import db.domain.board.enums.BoardStatus;
import db.domain.pet.enums.PetCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "board")
public class BoardEntity extends BaseEntity {

    private String title;

    private String content;

    private PetCategory category;

    private BoardStatus status;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime modifiedAt;

    private Long userId;

}
