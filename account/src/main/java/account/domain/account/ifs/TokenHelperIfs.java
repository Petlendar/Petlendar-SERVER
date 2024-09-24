package account.domain.account.ifs;

import account.domain.account.model.TokenDto;
import java.util.Map;

public interface TokenHelperIfs {

    TokenDto issueAccessToken(Map<String, Object> data);
    TokenDto issueRefresh(Map<String, Object> data);
    Map<String, Object> validationTokenWithThrow(String token);

}
