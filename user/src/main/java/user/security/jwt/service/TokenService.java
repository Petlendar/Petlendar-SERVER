package user.security.jwt.service;

import db.domain.token.RefreshTokenEntity;
import db.domain.token.RefreshTokenRepository;
import global.errorcode.ErrorCode;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import user.common.error.TokenErrorCode;
import user.common.exception.jwt.TokenException;
import user.security.jwt.ifs.TokenHelperIfs;
import user.security.jwt.model.TokenDto;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenHelperIfs tokenHelperIfs;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenDto issueAccessToken(Long userId) {
        Map<String, Object> data = new HashMap<>();
        data.put("userId", userId);
        return tokenHelperIfs.issueAccessToken(data);
    }

    public TokenDto issueRefreshToken(Long userId) {
        Map<String, Object> data = new HashMap<>();
        data.put("userId", userId);
        return tokenHelperIfs.issueRefresh(data);
    }

    public TokenDto reIssueAccessToken(String refreshToken) {
        Long userId = validationToken(refreshToken);
        RefreshTokenEntity entity = refreshTokenRepository.findFirstByUserIdOrderByUserId(userId);
        if (!Objects.equals(userId, entity.getUserId())){
            throw new TokenException(TokenErrorCode.INVALID_TOKEN);
        }
        return issueRefreshToken(userId);
    }

    public Long validationToken(String token) {
        Map<String, Object> userData = tokenHelperIfs.validationTokenWithThrow(token);

        Object userId = userData.get("userId");
        Objects.requireNonNull(userId, () -> {
            throw new TokenException(ErrorCode.NULL_POINT);
        });
        return Long.parseLong(userId.toString());
    }

    public void saveRefreshToken(RefreshTokenEntity refreshTokenEntity) {
        refreshTokenRepository.save(refreshTokenEntity);
    }

    public void deleteRefreshToken(Long userId) {
        refreshTokenRepository.deleteByUserId(userId);
    }

}
