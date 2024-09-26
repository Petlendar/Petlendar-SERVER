package db.domain.board;

import static db.domain.board.QBoardEntity.*;
import static org.springframework.util.StringUtils.hasText;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import db.domain.board.enums.BoardStatus;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class QueryBoardRepository {

    private final JPAQueryFactory queryFactory;

    public List<BoardEntity> getBoardSearchBy(EntitySearchCondition searchCondition) {
        return queryFactory.selectFrom(boardEntity)
            .where(
                likeTitle(searchCondition.getTitle()),
                goeStartDate(searchCondition.getStartDate()), // startDate 이후
                loeEndDate(searchCondition.getEndDate()), // endDate 이전 날짜로 조회
                ltBoardId(searchCondition.getBoardId()), // cursor 방식 적용
                boardEntity.status.ne(BoardStatus.UNREGISTERED)
            )
            .orderBy(getOrderSpecifier(searchCondition.getPage().getSort()).stream()
                .toArray(size -> new OrderSpecifier[size]))
            .limit(searchCondition.getPage().getPageSize())
            .fetch();
    }

    private List<OrderSpecifier> getOrderSpecifier(Sort sort) {
        List<OrderSpecifier> orders = new ArrayList<>();
        sort.stream().forEach(order -> {
            PathBuilder orderByExpression = new PathBuilder(BoardEntity.class,
                "boardEntity");
            orders.add(new OrderSpecifier<>(order.isAscending() ? Order.ASC : Order.DESC,
                orderByExpression.get(order.getProperty())));

        });
        return orders;
    }

    private BooleanExpression likeTitle(String title) {
        return hasText(title) ? boardEntity.title.like("%" + title + "%") : null;
    }

    private BooleanExpression goeStartDate(LocalDateTime startDate) {
        return startDate != null ? boardEntity.registeredAt.goe(startDate) : null;
    }

    private BooleanExpression loeEndDate(LocalDateTime endDate) {
        return endDate != null ? boardEntity.registeredAt.loe(endDate) : null;
    }

    private BooleanExpression ltBoardId(Long usedGoodsId) {
        return usedGoodsId != null ? boardEntity.id.lt(usedGoodsId) : null;
    }

    private BooleanExpression filterBy(BoardStatus status) {
        return status != null ? boardEntity.status.eq(status)
            : boardEntity.status.ne(BoardStatus.UNREGISTERED);
    }

}
