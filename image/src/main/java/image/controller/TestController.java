package image.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/image")
public class TestController {

    @GetMapping()
    public void test(@RequestHeader("x-user-id") String userId) {
        log.info("x-user-id: {}", userId);
    }
}