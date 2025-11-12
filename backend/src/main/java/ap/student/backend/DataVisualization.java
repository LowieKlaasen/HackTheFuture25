package ap.student.backend;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.*;

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

            if (response != null && response.contains("\"missionSolved\":true")) {
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

    @GetMapping("SensorBuoyNetwork")
    public ResponseEntity<Map<String, Object>> sensorBuoyNetwork() {

        String missionId = "e8265cf5-4cd7-47e8-b959-3d3a59985358";

        String morseCode = "-.-. -. -.-- --- .. .- .-- -.-- .--. -.-.";

        Map<String, Character> morseCodeMap = new HashMap<>();

        morseCodeMap.put(".-", 'A');
        morseCodeMap.put("-...", 'B');
        morseCodeMap.put("-.-.", 'C');
        morseCodeMap.put("-..", 'D');
        morseCodeMap.put(".", 'E');
        morseCodeMap.put("..-.", 'F');
        morseCodeMap.put("--.", 'G');
        morseCodeMap.put("....", 'H');
        morseCodeMap.put("..", 'I');
        morseCodeMap.put(".---", 'J');
        morseCodeMap.put("-.-", 'K');
        morseCodeMap.put(".-..", 'L');
        morseCodeMap.put("--", 'M');
        morseCodeMap.put("-.", 'N');
        morseCodeMap.put("---", 'O');
        morseCodeMap.put(".--.", 'P');
        morseCodeMap.put("--.-", 'Q');
        morseCodeMap.put(".-.", 'R');
        morseCodeMap.put("...", 'S');
        morseCodeMap.put("-", 'T');
        morseCodeMap.put("..-", 'U');
        morseCodeMap.put("...-", 'V');
        morseCodeMap.put(".--", 'W');
        morseCodeMap.put("-..-", 'X');
        morseCodeMap.put("-.--", 'Y');
        morseCodeMap.put("--..", 'Z');
        morseCodeMap.put("-----", '0');
        morseCodeMap.put(".----", '1');
        morseCodeMap.put("..---", '2');
        morseCodeMap.put("...--", '3');
        morseCodeMap.put("....-", '4');
        morseCodeMap.put(".....", '5');
        morseCodeMap.put("-....", '6');
        morseCodeMap.put("--...", '7');
        morseCodeMap.put("---..", '8');
        morseCodeMap.put("----.", '9');


        StringBuilder result = new StringBuilder();
        String[] codes = morseCode.trim().split(" ");

        for (String code : codes) {
            Character c = morseCodeMap.get(code);
            if (c != null) {
                result.append(c);
            } else {
                result.append('?'); // unknown character
            }
        }

        try {
            String response = CallAPI(missionId, result.toString());

            if (response != null && response.contains("\"missionSolved\":true")) {
                Map<String, Object> body = new HashMap<>();
                body.put("found", true);
                body.put("result", result.toString());
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

    @GetMapping("EcosystemHealthDashboard")
    public ResponseEntity<Map<String, Object>> EcosystemHealthDashboard() {

        String missionId = "5917b718-73cb-406f-a422-9a6950016700";

        int start = 57779;
        int end = 57879;


        List<Integer> primes = new ArrayList<>();

        for (int i = start; i <= end; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }

//        Map<String, Object> body = new HashMap<>();
//        body.put("solution", primes.toString());
//
//        return ResponseEntity.ok(body);

        try {
            String response = CallAPI(missionId, primes.toString());

            if (response != null && response.contains("\"missionSolved\":true")) {
                Map<String, Object> body = new HashMap<>();
                body.put("found", true);
                body.put("result", primes.toString());
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

        return webClient.get()
                .uri(url + "?result=" + result)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }


    private boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        int sqrt = (int) Math.sqrt(n);
        for (int i = 3; i <= sqrt; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // endregion

}