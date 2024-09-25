package db.domain.comment;

import db.common.BaseEntity;
import db.domain.comment.enums.CommentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "comment")
public class CommentEntity extends BaseEntity {

    private String content;

    @Enumerated(EnumType.STRING)
    private CommentStatus status;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime modifiedAt;

    private Long boardId;

    private Long userId;

}
