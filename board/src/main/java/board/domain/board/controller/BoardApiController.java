package board.domain.board.controller;

import board.domain.board.business.BoardBusiness;
import board.domain.board.controller.model.BoardRegisterRequest;
import board.domain.board.controller.model.BoardRegisterResponse;
import board.domain.board.service.BoardService;
import board.domain.user.controller.model.User;
import global.annotation.UserSession;
import global.api.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardApiController {

    private final BoardBusiness boardBusiness;

    @PostMapping()
    public Api<BoardRegisterResponse> register(
        @RequestBody Api<BoardRegisterRequest> request,
        @UserSession User user
    ) {
        BoardRegisterResponse response = boardBusiness.register(request.getBody(), user.getId());
        return Api.OK(response);
    }

}
