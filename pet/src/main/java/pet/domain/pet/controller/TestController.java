package pet.domain.pet.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/pet")
public class TestController {

    @PostMapping
    public void test() {
        log.info("pet call Test ");
    }

}
