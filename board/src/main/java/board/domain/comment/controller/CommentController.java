package board.domain.comment.controller;

import board.common.response.MessageResponse;
import board.domain.comment.business.CommentBusiness;
import board.domain.comment.controller.model.register.CommentRegisterRequest;
import board.domain.comment.controller.model.register.CommentRegisterResponse;
import board.domain.comment.controller.model.update.CommentUpdateRequest;
import board.domain.comment.controller.model.update.CommentUpdateResponse;
import board.domain.user.controller.model.User;
import global.annotation.UserSession;
import global.api.Api;
import lombok.RequiredArgsConstructor;
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
    public Api<CommentRegisterResponse> register(
        @RequestBody Api<CommentRegisterRequest> registerRequest,
        @UserSession User user
    ) {
        CommentRegisterResponse response = commentBusiness.register(registerRequest.getBody(), user.getId());
        return Api.OK(response);
    }

    @PostMapping("/unregister/{commentId}")
    public Api<MessageResponse> unregister(
        @PathVariable Long commentId,
        @UserSession User user
    ) {
        MessageResponse response = commentBusiness.unregister(commentId, user.getId());
        return Api.OK(response);
    }

    @PostMapping("/update")
    public Api<CommentUpdateResponse> update(
        @RequestBody Api<CommentUpdateRequest> updateRequest,
        @UserSession User user)
    {
        CommentUpdateResponse response = commentBusiness.update(updateRequest.getBody(), user.getId());
        return Api.OK(response);
    }

}
