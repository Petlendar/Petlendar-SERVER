package user.domain.jwt.ifs;

import java.util.Map;
import user.domain.jwt.model.TokenDto;

public interface TokenHelperIfs {

    TokenDto issueAccessToken(Map<String, Object> data);
    TokenDto issueRefresh(Map<String, Object> data);
    Map<String, Object> validationTokenWithThrow(String token);

}
