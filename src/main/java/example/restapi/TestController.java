package example.restapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// INIT Controller for basic Test endpoint
@RestController
@RequestMapping("/api")
public class TestController {

    // Endpoint: /api/test
    // No Params
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("You have successfully reached the test rest api");
    }
}
