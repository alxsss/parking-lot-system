package com.parking.payment;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import com.parking.grpc.PaymentServiceGrpc;
import com.parking.grpc.PaymentRequest;
import com.parking.grpc.PaymentResponse;
import com.parking.grpc.GateServiceGrpc;
import com.parking.grpc.GateRequest;
import com.parking.grpc.GateResponse;

import java.util.HashSet;
import java.util.Set;

public class PaymentService extends PaymentServiceGrpc.PaymentServiceImplBase {
    private final Set<String> paidPlates = new HashSet<>();
    private final GateServiceGrpc.GateServiceBlockingStub gateStub;

    public PaymentService() {
        paidPlates.add("ABC123"); // Simulated paid license plate

        ManagedChannel channel = ManagedChannelBuilder.forAddress("gate-service", 8082)
                .usePlaintext()
                .build();
        gateStub = GateServiceGrpc.newBlockingStub(channel);
    }

    @Override
    public void checkPayment(PaymentRequest request, StreamObserver<PaymentResponse> responseObserver) {
        boolean isPaid = paidPlates.contains(request.getLicensePlate());

        PaymentResponse response = PaymentResponse.newBuilder()
                .setIsPaid(isPaid)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

        // If paid, notify gate service
        if (isPaid) {
            GateRequest gateRequest = GateRequest.newBuilder()
                    .setLicensePlate(request.getLicensePlate())
                    .build();
            GateResponse gateResponse = gateStub.openGate(gateRequest);
            System.out.println("Gate response: " + gateResponse.getMessage());
        }
    }
}
