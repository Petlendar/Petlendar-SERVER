package account.domain.account.ifs;

import account.domain.account.model.TokenDto;
import java.util.Map;

public interface TokenHelperIfs {

    Map<String, Object> validationTokenWithThrow(String token);

}
