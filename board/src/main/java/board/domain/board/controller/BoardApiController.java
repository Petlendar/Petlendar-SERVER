package board.domain.board.controller;

import board.common.response.MessageResponse;
import board.domain.board.business.BoardBusiness;
import board.domain.board.controller.model.detail.BoardDetailResponse;
import board.domain.board.controller.model.register.BoardRegisterRequest;
import board.domain.board.controller.model.register.BoardRegisterResponse;
import board.domain.board.controller.model.search.BoardSearchResponse;
import board.domain.board.controller.model.search.SearchCondition;
import board.domain.board.controller.model.update.BoardUpdateRequest;
import board.domain.board.controller.model.update.BoardUpdateResponse;
import board.domain.user.controller.model.User;
import global.annotation.UserSession;
import global.api.Api;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public Api<BoardRegisterResponse> register(
        @RequestBody Api<BoardRegisterRequest> request,
        @UserSession User user
    ) {
        BoardRegisterResponse response = boardBusiness.register(request.getBody(), user.getId());
        return Api.OK(response);
    }

    @PostMapping("/unregister/{boardId}")
    public Api<MessageResponse> unregister(
        @PathVariable Long boardId,
        @UserSession User user) {
        MessageResponse response = boardBusiness.unregister(boardId, user.getId());
        return Api.OK(response);
    }

    @PostMapping("/update")
    public Api<BoardUpdateResponse> update(
        @RequestBody Api<BoardUpdateRequest> updateRequest,
        @UserSession User user) {
        BoardUpdateResponse response = boardBusiness.update(updateRequest.getBody(), user.getId());
        return Api.OK(response);
    }

    @GetMapping("/{boardId}")
    public Api<BoardDetailResponse> getBoardDetail(@PathVariable Long boardId) {
        BoardDetailResponse response = boardBusiness.getBoardDetailBy(boardId);
        return Api.OK(response);
    }

}
