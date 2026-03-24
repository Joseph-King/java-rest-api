package example.restapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

// INIT Controller for basic Test endpoint
@RestController
@RequestMapping("/test")
public class TestController {

    // Endpoint: /test/get
    // No Params
    @GetMapping("/get")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("You have successfully reached the test rest api");
    }

    // Endpoint: /test/post-json
    // Expecting a JSON object
    @PostMapping("/post-json")
    public ResponseEntity<String> postJson(@RequestBody Map<String, Object> payload) {
        return ResponseEntity.ok("Received JSON: " + payload);
    }

    // Endpoint: /test/upload-txt
    // Expecting a .txt file
    @PostMapping("/upload-txt")
    public ResponseEntity<String> uploadTxt(@RequestParam("file") MultipartFile file) {
        // CHECK if file is empty
        if(file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        // CHECK for .txt extension
        String fileName = file.getOriginalFilename();
        if (fileName == null || !fileName.endsWith(".txt")) {
            return ResponseEntity.badRequest().body("Only .txt files are allowed.");
        }

        try {
            // Reading the content of the text file
            String content = new String(file.getBytes(), StandardCharsets.UTF_8);
            return ResponseEntity.ok(fileName + " uploaded. Content: " + content);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error reading file.");
        }
    }
}
