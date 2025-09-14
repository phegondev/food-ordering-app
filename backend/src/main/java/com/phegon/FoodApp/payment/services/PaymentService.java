package com.phegon.FoodApp.payment.services;

import com.phegon.FoodApp.payment.dtos.PaymentDTO;
import com.phegon.FoodApp.response.Response;

import java.util.List;

public interface PaymentService {

    Response<?> initializePayment(PaymentDTO paymentDTO);
    void updatePaymentForOrder(PaymentDTO paymentDTO);
    Response<List<PaymentDTO>> getAllPayments();
    Response<PaymentDTO> getPaymentById(Long paymentId);

}
