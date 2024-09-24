package image.domain.user.controller.model;

import db.user.enums.UserRole;
import db.user.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
public class User {

    private Long userId;
    private String email;
    private UserRole role;

}
