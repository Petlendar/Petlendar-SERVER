package db.domain.image.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImageKind {

    PET("반려동물 사진"),
    BOARD("게시판 사진");
    ;

    private final String description;

}
