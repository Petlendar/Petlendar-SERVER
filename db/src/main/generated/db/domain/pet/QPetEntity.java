package db.domain.pet;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPetEntity is a Querydsl query type for PetEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPetEntity extends EntityPathBase<PetEntity> {

    private static final long serialVersionUID = -463385735L;

    public static final QPetEntity petEntity = new QPetEntity("petEntity");

    public final db.common.QBaseEntity _super = new db.common.QBaseEntity(this);

    public final StringPath address = createString("address");

    public final DatePath<java.time.LocalDate> birth = createDate("birth", java.time.LocalDate.class);

    public final EnumPath<db.domain.pet.enums.PetCategory> category = createEnum("category", db.domain.pet.enums.PetCategory.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public final DateTimePath<java.time.LocalDateTime> registeredAt = createDateTime("registeredAt", java.time.LocalDateTime.class);

    public final EnumPath<db.domain.pet.enums.PetStatus> status = createEnum("status", db.domain.pet.enums.PetStatus.class);

    public final DateTimePath<java.time.LocalDateTime> unregisteredAt = createDateTime("unregisteredAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final NumberPath<Float> weight = createNumber("weight", Float.class);

    public QPetEntity(String variable) {
        super(PetEntity.class, forVariable(variable));
    }

    public QPetEntity(Path<? extends PetEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPetEntity(PathMetadata metadata) {
        super(PetEntity.class, metadata);
    }

}

