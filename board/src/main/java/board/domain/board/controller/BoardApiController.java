package board.domain.board.controller;

import board.common.response.MessageResponse;
import board.domain.board.business.BoardBusiness;
import board.domain.board.controller.model.register.BoardRegisterRequest;
import board.domain.board.controller.model.register.BoardRegisterResponse;
import board.domain.board.controller.model.update.BoardUpdateRequest;
import board.domain.board.controller.model.update.BoardUpdateResponse;
import board.common.resolver.User;
import global.annotation.UserSession;
import global.api.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Operation(summary = "[게시글 등록]")
    public Api<BoardRegisterResponse> register(
        @RequestBody @Valid Api<BoardRegisterRequest> request,
        @Parameter(hidden = true) @UserSession User user
    ) {
        BoardRegisterResponse response = boardBusiness.register(request.getBody(), user.getId());
        return Api.OK(response);
    }

    @PostMapping("/unregister/{boardId}")
    @Operation(summary = "[게시글 삭제]")
    public Api<MessageResponse> unregister(
        @PathVariable Long boardId,
        @Parameter(hidden = true) @UserSession User user) {
        MessageResponse response = boardBusiness.unregister(boardId, user.getId());
        return Api.OK(response);
    }

    @PostMapping("/update")
    @Operation(summary = "[게시글 수정]")
    public Api<BoardUpdateResponse> update(
        @RequestBody @Valid Api<BoardUpdateRequest> updateRequest,
        @Parameter(hidden = true) @UserSession User user) {
        BoardUpdateResponse response = boardBusiness.update(updateRequest.getBody(), user.getId());
        return Api.OK(response);
    }

}
