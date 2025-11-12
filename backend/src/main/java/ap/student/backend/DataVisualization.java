package ap.student.backend;

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
@RequestMapping("/data-visualization")
public class DataVisualization {

    private final WebClient webClient = WebClient.create();

    @GetMapping()
    public ResponseEntity<String> check() {
        return ResponseEntity.ok("Data Visualization");
    }

    @GetMapping("MarineTrafficMonitor")
    public ResponseEntity<Map<String, Object>> marineTrafficMonitor() {

        String missionId = "ea59e88c-b862-4281-a0f6-81651e6c2fac";

        int parameter = 14;
        long factorial = 1;

        for (int i = 1; i <= parameter; i++) {
            factorial *= i;
        }

        try {
            String response = CallAPI(missionId, String.valueOf(factorial));

            if (response != null && response.contains("success")) {
                Map<String, Object> body = new HashMap<>();
                body.put("found", true);
                body.put("result", factorial);
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

//    @GetMapping("SensorBuoyNetwork")
//    public ResponseEntity<Map<String, Object>> sensorBuoyNetwork() {}



    // region Private Methods

    private String CallAPI(String missionId, String result) {
        String url = "https://htf.collide.be/missions/" + missionId + "/solution";

        return webClient.get()
                .uri(url + "?result=" + result)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    // endregion

}