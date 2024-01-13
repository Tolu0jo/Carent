package com.example.carent.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentResponse {
        @JsonProperty("status")
        private String status;

        @JsonProperty("message")
        private String message;

        @JsonProperty("data")
        private FLWData data;
    }


