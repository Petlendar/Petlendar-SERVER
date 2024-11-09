package user.domain.jwt.service;

import db.domain.token.TokenEntity;
import global.errorcode.ErrorCode;
import global.errorcode.TokenErrorCode;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import user.common.exception.jwt.TokenException;
import user.domain.jwt.model.TokenDto;
import user.domain.jwt.ifs.TokenHelperIfs;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenHelperIfs tokenHelperIfs;
    private final RedisTemplate<String, Object> redisTemplate;

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

        Map<Object, Object> tokenData  = redisTemplate.opsForHash().entries(String.valueOf(userId));

        String storedRefreshToken = (String) tokenData.get("refreshToken");

        if (storedRefreshToken == null || !storedRefreshToken.equals(refreshToken)) {
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

    public void saveRefreshToken(TokenEntity tokenEntity) {
        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("userId", tokenEntity.getUserId());
        tokenData.put("refreshToken", tokenEntity.getRefreshToken());
        redisTemplate.opsForHash().putAll(String.valueOf(tokenEntity.getUserId()), tokenData);
    }

    public void deleteRefreshToken(Long userId) {
        redisTemplate.delete(String.valueOf(userId));
    }

}
