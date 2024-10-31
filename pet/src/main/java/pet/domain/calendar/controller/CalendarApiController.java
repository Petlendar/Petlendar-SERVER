package pet.domain.calendar.controller;

import global.annotation.UserSession;
import global.api.Api;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pet.common.resolver.User;
import pet.domain.calendar.business.CalendarBusiness;
import pet.domain.calendar.controller.model.VaccinationDateResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/calendar")
public class CalendarApiController {

    private final CalendarBusiness calendarBusiness;

    @GetMapping("/{year}/{month}")
    public Api<List<VaccinationDateResponse>> getNextVaccinationDate(
        @PathVariable int year,
        @PathVariable int month,
        @Parameter(hidden = true) @UserSession User user
    ) {
        List<VaccinationDateResponse> response = calendarBusiness.getNextVaccinationDate(year, month, user.getId());
        return Api.OK(response);
    }
}