package com.example.carent.dto.request;


import com.example.carent.model.Booking;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@Builder
public class PaymentRequest {
    private  String tx_ref;
    private  String redirect_url;
    private double amount;
    private String url;
    private Booking meta;
}
