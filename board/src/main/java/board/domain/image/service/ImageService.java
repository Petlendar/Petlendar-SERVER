package board.domain.image.service;

import board.common.error.ImageErrorCode;
import board.common.exception.image.ImageNotFoundException;
import db.domain.board.BoardEntity;
import db.domain.image.ImageEntity;
import db.domain.image.ImageRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {

    private final ImageRepository imageRepository;

    /**
     * 이미지가 자신이 업로드한 이미지를 판단 및 등록
     */
    public void linkImageToBoard(List<Long> imageIdList, BoardEntity savedBoardEntity) {
        if (imageIdList != null && !imageIdList.isEmpty()) {
            // 모든 imageId가 savedBoardEntity 의 userId와 일치하는지 확인 (existsBy 사용)
            boolean allMatch = imageIdList.stream().allMatch(imageId ->
                imageRepository.existsByIdAndUserId(imageId, savedBoardEntity.getUserId())
            );

            // 일치하지 않는 이미지가 있는 경우 예외 발생
            if (!allMatch) {
                throw new ImageNotFoundException(ImageErrorCode.IMAGE_NOT_FOUND);
            }

            // 일치하는 경우 이미지를 boardId와 매칭
            imageIdList.forEach(imageId -> {
                imageRepository.findById(imageId).ifPresent(imageEntity -> {
                    imageEntity.setBoardId(savedBoardEntity.getId()); // boardId와 매칭
                    imageRepository.save(imageEntity);
                });
            });
        }
    }

    public List<ImageEntity> getImageOrEmptyBy(Long boardId) {
        return imageRepository.findAllByBoardIdOrderByIdAsc(boardId);
    }

    public List<ImageEntity> getImageBy(List<Long> boardIdList) {
        return imageRepository.findAllByBoardIdIn(boardIdList);
    }

}