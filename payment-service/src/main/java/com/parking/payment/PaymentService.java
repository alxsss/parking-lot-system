package com.parking.payment;

import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;
import com.parking.grpc.PaymentServiceGrpc;
import com.parking.grpc.PaymentRequest;
import com.parking.grpc.PaymentResponse;

import java.util.HashSet;
import java.util.Set;

@Service
public class PaymentService extends PaymentServiceGrpc.PaymentServiceImplBase {
    private final Set<String> paidPlates = new HashSet<>();

    public PaymentService() {
        paidPlates.add("ABC123"); // Simulated paid license plate
    }

    @Override
    public void checkPayment(PaymentRequest request, StreamObserver<PaymentResponse> responseObserver) {
        boolean isPaid = paidPlates.contains(request.getLicensePlate());
        PaymentResponse response = PaymentResponse.newBuilder()
                .setIsPaid(isPaid)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}