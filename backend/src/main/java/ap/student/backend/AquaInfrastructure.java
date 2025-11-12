package ap.student.backend;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.HashMap;
import java.util.Map;

@RestController("/aqua-infrastructure")
public class AquaInfrastructure {

    private final WebClient webClient = WebClient.create();

    @GetMapping()
    public ResponseEntity<String> check() {
        return ResponseEntity.ok("Aqua Infrastructure");
    }

    @GetMapping("/ModularExpansionKit")
    public ResponseEntity<Map<String, Object>> modularExpansionKit() {

        String missionId = "3083dbfa-cade-455f-a9c3-155c791222df";

        String url = "https://htf.collide.be/missions/" + missionId + "/solution";

        int code = 0;

        while (code <= 9999) {
            String formatted = String.format("%04d", code);

            try {
                String response = webClient.get()
                        .uri(url + "?result=" + formatted)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();

                if (response != null && response.contains("success")) {
                    Map<String, Object> body = new HashMap<>();
                    body.put("found", true);
                    body.put("code", formatted);
                    body.put("externalResponse", response);

                    return ResponseEntity.ok(body);
                }

            } catch (WebClientResponseException e) {
                // ignore 4xx or 5xx errors and continue
            }

            code++;
        }

        Map<String, Object> body = new HashMap<>();
        body.put("found", false);

        return ResponseEntity.ok(body);

    }

}
