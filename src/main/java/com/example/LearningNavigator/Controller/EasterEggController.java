package com.example.LearningNavigator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class EasterEggController {

    private static final String NUMBERS_API_URL = "http://numbersapi.com/{number}/trivia";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hidden-feature/{number}")
    public ResponseEntity<String> easterEgg(@PathVariable("number") int number) {
        String apiUrl = NUMBERS_API_URL.replace("{number}", String.valueOf(number));
        String fact = restTemplate.getForObject(apiUrl, String.class);

        if (fact != null) {
            return ResponseEntity.ok(fact);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch fact from Numbers API");
        }
    }
}
