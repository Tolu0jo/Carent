package com.example.carent.dto.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Data
@Builder
public class BookingResponse {

    private HttpStatusCode statusCode;
    private String message;
}
