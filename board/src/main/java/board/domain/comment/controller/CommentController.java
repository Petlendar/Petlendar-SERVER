package board.domain.comment.controller;

import board.domain.comment.business.CommentBusiness;
import board.domain.comment.controller.model.CommentRegisterRequest;
import board.domain.comment.controller.model.CommentRegisterResponse;
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

}
