package board.domain.comment.controller;

import board.common.response.MessageResponse;
import board.domain.board.controller.model.detail.CommentDetailResponse;
import board.domain.comment.business.CommentBusiness;
import board.domain.comment.controller.model.register.CommentRegisterRequest;
import board.domain.comment.controller.model.register.CommentRegisterResponse;
import board.domain.comment.controller.model.update.CommentUpdateRequest;
import board.domain.comment.controller.model.update.CommentUpdateResponse;
import board.common.resolver.User;
import global.annotation.UserSession;
import global.api.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentBusiness commentBusiness;

    @PostMapping()
    @Operation(summary = "[댓글 등록]")
    public Api<CommentRegisterResponse> register(
        @RequestBody @Valid Api<CommentRegisterRequest> registerRequest,
        @Parameter(hidden = true) @UserSession User user
    ) {
        CommentRegisterResponse response = commentBusiness.register(registerRequest.getBody(), user.getId());
        return Api.OK(response);
    }

    @GetMapping("/{boardId}")
    @Operation(summary = "[게시글 댓글 조회]")
    public Api<List<CommentDetailResponse>> getCommentDetailList(@PathVariable Long boardId) {
        List<CommentDetailResponse> response = commentBusiness.getCommentDetailListBy(boardId);
        return Api.OK(response);
    }

    @PostMapping("/unregister/{commentId}")
    @Operation(summary = "[댓글 삭제]")
    public Api<MessageResponse> unregister(
        @PathVariable Long commentId,
        @Parameter(hidden = true) @UserSession User user
    ) {
        MessageResponse response = commentBusiness.unregister(commentId, user.getId());
        return Api.OK(response);
    }

    @PostMapping("/update")
    @Operation(summary = "[댓글 수정]")
    public Api<CommentUpdateResponse> update(
        @RequestBody @Valid Api<CommentUpdateRequest> updateRequest,
        @Parameter(hidden = true) @UserSession User user)
    {
        CommentUpdateResponse response = commentBusiness.update(updateRequest.getBody(), user.getId());
        return Api.OK(response);
    }

}
