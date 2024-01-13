package com.example.carent.service.impl;

import com.example.carent.dto.request.PaymentRequest;
import com.example.carent.dto.response.BookingResponse;
import com.example.carent.dto.response.PaymentResponse;
import com.example.carent.service.FlutterService;
import com.example.carent.utils.RestTemplateUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Component
@RequiredArgsConstructor
public class FlutterServiceImpl implements FlutterService {

    private final RestTemplate restTemplate;
    private final RestTemplateUtils restTemplateUtils;

    @Value("${flutter.payment.url}")
    private String paymentUrl;



    @Override
    public Object createPayment(PaymentRequest paymentRequest) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(paymentRequest);
        HttpEntity<String> httpEntity = new HttpEntity<>(jsonString, restTemplateUtils.getHeaders());
        ResponseEntity<String> response = restTemplate.exchange(paymentUrl, HttpMethod.POST, httpEntity, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            String responseBody = response.getBody();

            return objectMapper.readValue(responseBody, PaymentResponse.class);

        }
        return BookingResponse.builder()
                .message(response.getBody())
                .statusCode(response.getStatusCode())
                .build();

    }
}