package com.example.carent.service;

import com.example.carent.dto.request.PaymentRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface FlutterService {
   Object createPayment(PaymentRequest paymentRequest) throws JsonProcessingException;
}
