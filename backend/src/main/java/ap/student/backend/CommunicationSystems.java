package ap.student.backend;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Base64;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController()
@RequestMapping("/communication-systems")
public class CommunicationSystems {

    private final WebClient webClient = WebClient.create();

    @GetMapping()
    public ResponseEntity<String> check() {
        return ResponseEntity.ok("Communication Systems");
    }

    @GetMapping("HandoffProtocolHandler")
    public ResponseEntity<Map<String, Object>> handoff() {

        String missionId = "4439a9f8-77eb-461b-9dcf-29e57a40fcc4";

        int year = 2002;
        int month = 9;

        YearMonth ym = YearMonth.of(year, month);

        LocalDate firstDay = ym.atDay(1);
        LocalDate lastDay = ym.atEndOfMonth();

        String firstDayName = firstDay.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).toUpperCase();
        String lastDayName = lastDay.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).toUpperCase();


//        Map<String, Object> body = new HashMap<>();
//        body.put("result", firstDayName + "-" + lastDayName);
//
//        return ResponseEntity.ok(body);

        try {
            String response = CallAPI(missionId, firstDayName + "-" + lastDayName);

            if (response != null && response.contains("\"missionSolved\":true")) {
                Map<String, Object> body = new HashMap<>();
                body.put("found", true);
                body.put("result", firstDayName + "-" + lastDayName);
                body.put("externalResponse", response);

                return ResponseEntity.ok(body);
            }

        } catch (WebClientResponseException e) {
            // ignore 4xx or 5xx errors and continue
        }

        Map<String, Object> body = new HashMap<>();
        body.put("found", false);

        return ResponseEntity.ok(body);

    }

    @GetMapping("DataCompressionModule")
    public ResponseEntity<Map<String, Object>> dataCompressionModule() {

        String missionId = "f6881aef-07d8-4b67-b051-37f3ed05cef1";

        String parameter = "yxwboqnylvuli";

        StringBuilder decoded = new StringBuilder();

        for (char c : parameter.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                decoded.append((char) ('a' + (c - 'a' + 13) % 26));
            } else if (c >= 'A' && c <= 'Z') {
                decoded.append((char) ('A' + (c - 'A' + 13) % 26));
            } else {
                decoded.append(c);
            }
        }

//        Map<String, Object> body = new HashMap<>();
//        body.put("result", decoded.toString());
//
//        return ResponseEntity.ok(body);

        try {
            String response = CallAPI(missionId, decoded.toString());

            if (response != null && response.contains("\"missionSolved\":true")) {
                Map<String, Object> body = new HashMap<>();
                body.put("found", true);
                body.put("result", decoded.toString());
                body.put("externalResponse", response);

                return ResponseEntity.ok(body);
            }

        } catch (WebClientResponseException e) {
            // ignore 4xx or 5xx errors and continue
        }

        Map<String, Object> body = new HashMap<>();
        body.put("found", false);
        return ResponseEntity.ok(body);

    }





    // region Private Methods

    private String CallAPI(String missionId, String result) {
        String url = "https://htf.collide.be/missions/" + missionId + "/solution";

        return webClient.post()
                .uri(url + "?result=" + result)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    // endregion

}