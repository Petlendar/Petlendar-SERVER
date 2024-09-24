package db.domain.image;


import db.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "image")
public class ImageEntity extends BaseEntity {

    private String imageUrl;

    private String originalName;

    private String serverName;

    private String extension;

    private Long petId;

    private Long boardId;

}
