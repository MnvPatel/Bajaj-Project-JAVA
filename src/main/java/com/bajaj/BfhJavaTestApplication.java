package com.bajaj;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;

@SpringBootApplication
public class BfhJavaTestApplication implements CommandLineRunner {

    private final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        SpringApplication.run(BfhJavaTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        WebhookRequest webhookRequest = new WebhookRequest();
        webhookRequest.name = "Manav Hareshbhai Patel";
        webhookRequest.regNo = "22BCE10054";
        webhookRequest.email = "manavhareshbhaipatel2022@vitbhopal.ac.in";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<WebhookRequest> requestEntity = new HttpEntity<>(webhookRequest, headers);

        ResponseEntity<WebhookResponse> response = restTemplate.exchange(
            "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA",
            HttpMethod.POST,
            requestEntity,
            WebhookResponse.class
        );

        WebhookResponse webhookResponse = response.getBody();
        if (webhookResponse == null || webhookResponse.accessToken == null) {
            throw new RuntimeException("Failed to get access token");
        }

        String sqlQuery = "SELECT d.DEPARTMENT_NAME, " +
            "AVG(TIMESTAMPDIFF(YEAR, e.DOB, CURDATE())) AS AVERAGE_AGE, " +
            "SUBSTRING_INDEX(GROUP_CONCAT(DISTINCT CONCAT(e.FIRST_NAME, ' ', e.LAST_NAME) ORDER BY e.EMP_ID SEPARATOR ', '), ', ', 10) AS EMPLOYEE_LIST " +
            "FROM DEPARTMENT d " +
            "INNER JOIN EMPLOYEE e ON d.DEPARTMENT_ID = e.DEPARTMENT_ID " +
            "INNER JOIN (SELECT DISTINCT EMP_ID FROM PAYMENTS WHERE AMOUNT > 70000) p ON e.EMP_ID = p.EMP_ID " +
            "GROUP BY d.DEPARTMENT_ID, d.DEPARTMENT_NAME " +
            "ORDER BY d.DEPARTMENT_ID DESC";

        SolutionRequest solutionRequest = new SolutionRequest();
        solutionRequest.finalQuery = sqlQuery;

        HttpHeaders solutionHeaders = new HttpHeaders();
        solutionHeaders.setContentType(MediaType.APPLICATION_JSON);
        solutionHeaders.set("Authorization", webhookResponse.accessToken);
        HttpEntity<SolutionRequest> solutionEntity = new HttpEntity<>(solutionRequest, solutionHeaders);

        try {
            ResponseEntity<String> solutionResponse = restTemplate.exchange(
                "https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA",
                HttpMethod.POST,
                solutionEntity,
                String.class
            );
            System.out.println("Solution submitted successfully: " + solutionResponse.getStatusCode());
            if (solutionResponse.getBody() != null) {
                System.out.println("Response: " + solutionResponse.getBody());
            }
        } catch (org.springframework.web.client.HttpClientErrorException e) {
            System.err.println("Error submitting solution: " + e.getStatusCode());
            System.err.println("Response body: " + e.getResponseBodyAsString());
            throw e;
        }
    }

    static class WebhookRequest {
        public String name;
        public String regNo;
        public String email;
    }

    static class WebhookResponse {
        @JsonProperty("webhookUrl")
        public String webhookUrl;
        
        @JsonProperty("accessToken")
        public String accessToken;
    }

    static class SolutionRequest {
        @JsonProperty("finalQuery")
        public String finalQuery;
    }
}

