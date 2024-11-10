package board.domain.board.controller;

import board.domain.board.business.BoardBusiness;
import board.domain.board.controller.model.detail.BoardDetailResponse;
import board.domain.board.controller.model.search.BoardSearchResponse;
import board.domain.board.controller.model.search.SearchCondition;
import global.api.Api;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/open-api/board")
public class BoardOpenApiController {

    private final BoardBusiness boardBusiness;

    @GetMapping()
    @Operation(summary = "[게시글 목록 조회/검색]")
    public Api<List<BoardSearchResponse>> getBoardSearchBy(
        @ModelAttribute @Valid SearchCondition condition,
        @PageableDefault(sort = "registeredAt", direction = Sort.Direction.DESC) Pageable page // default size = 10
    ) {
        List<BoardSearchResponse> response = boardBusiness.getBoardSearchBy(condition, page);
        return Api.OK(response);
    }

    @GetMapping("/{boardId}")
    @Operation(summary = "[게시글 상세 조회]")
    public Api<BoardDetailResponse> getBoardDetail(@PathVariable Long boardId) {
        BoardDetailResponse response = boardBusiness.getBoardDetailBy(boardId);
        return Api.OK(response);
    }

}
