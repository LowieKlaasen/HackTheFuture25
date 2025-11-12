package ap.student.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("/aqua-infrastructure")
public class AquaInfrastructure {

    private final WebClient webClient = WebClient.create();

    @GetMapping()
    public ResponseEntity<String> check() {
        return ResponseEntity.ok("Aqua Infrastructure");
    }

    @GetMapping("ModularExpansionKit")
    public ResponseEntity<Map<String, Object>> modularExpansionKit() {

        String missionId = "3083dbfa-cade-455f-a9c3-155c791222df";

//        String url = "https://htf.collide.be/missions/" + missionId + "/solution";

        int code = 0;

        while (code <= 9999) {
            String formatted = String.format("%04d", code);

            try {
                System.out.println("Trying code: " + formatted);

                String response = CallAPI(missionId, formatted);

                if (response != null && response.contains("success")) {
                    Map<String, Object> body = new HashMap<>();
                    body.put("found", true);
                    body.put("code", formatted);
                    body.put("externalResponse", response);

                    System.out.println("OK: " + response);

                    return ResponseEntity.ok(body);
                }
                else {
                    System.out.println("WRONG: " + response);
                }

            } catch (WebClientResponseException e) {
                // ignore 4xx or 5xx errors and continue
                System.out.println("ERROR: " + e.getMessage());
            }

            code++;
        }

        Map<String, Object> body = new HashMap<>();
        body.put("found", false);

        return ResponseEntity.ok(body);
    }

    @GetMapping("EnergyEfficientWaterPump")
    public ResponseEntity<Map<String, Object>> energyEfficientWaterPump() {

        String missionId = "be9abbed-40b9-4e3c-a100-83a74fc97592";

        int a = Math.abs(861);
        int b = Math.abs(712);

        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }


        try {
            String response = CallAPI(missionId, String.valueOf(a));

            if (response != null && response.contains("success")) {
                Map<String, Object> body = new HashMap<>();
                body.put("found", true);
                body.put("result", a);
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

    @GetMapping("FoundationAnchor")
    public ResponseEntity<Map<String, Object>> foundationAnchor() {

        String missionId = "13ae7fb2-cec3-4cb1-8edb-c68483268eda";

        String encoded = "Q1BzbU5EcVJjVU4wWlZuRFNVSQ==";

        byte[] decodedBytes = Base64.getDecoder().decode(encoded);
        String decodedString = new String(decodedBytes);

        try {
            String response = CallAPI(missionId, decodedString);

            if (response != null && response.contains("success")) {
                Map<String, Object> body = new HashMap<>();
                body.put("found", true);
                body.put("result", decodedString);
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