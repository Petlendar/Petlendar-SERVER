package image.domain.user.resolver;

import db.domain.user.enums.UserRole;
import global.annotation.UserSession;
import image.domain.user.controller.model.User;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserSessionResolver implements HandlerMethodArgumentResolver {


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 지원하는 파라미터 체크, 어노테이션 체크

        // 1. Annotation 있는지 확인
        boolean annotation = parameter.hasParameterAnnotation(UserSession.class);

        // 2. 파라미터의 타입 체크
        boolean parameterType = parameter.getParameterType().equals(User.class);

        return (annotation && parameterType);

    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // supportsParameter true 시 실행

        // request context holder 에서 찾아오기
        RequestAttributes requestContext = Objects.requireNonNull(
            RequestContextHolder.getRequestAttributes());

        Object userId = requestContext.getAttribute("userId",
            RequestAttributes.SCOPE_REQUEST);
        Object email = requestContext.getAttribute("email",
            RequestAttributes.SCOPE_REQUEST);
        Object role = requestContext.getAttribute("role",
            RequestAttributes.SCOPE_REQUEST);

        return User.builder()
            .id(Long.parseLong(userId.toString()))
            .email(email.toString())
            .role(UserRole.valueOf(role.toString()))
            .build();
    }
}
