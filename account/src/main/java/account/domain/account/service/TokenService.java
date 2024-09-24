package account.domain.account.service;

import account.common.exception.jwt.TokenException;
import account.domain.account.ifs.TokenHelperIfs;
import global.errorcode.ErrorCode;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenHelperIfs tokenHelperIfs;

    public Long validationToken(String token) {
        Map<String, Object> userData = tokenHelperIfs.validationTokenWithThrow(token);

        Object userId = userData.get("userId");
        Objects.requireNonNull(userId, () -> {
            throw new TokenException(ErrorCode.NULL_POINT);
        });
        return Long.parseLong(userId.toString());
    }

}
