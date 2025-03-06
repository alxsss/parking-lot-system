package com.parking.lpr;

import com.parking.grpc.LprRequest;
import com.parking.grpc.LprResponse;
import com.parking.grpc.LprServiceGrpc;
import com.parking.grpc.PaymentRequest;
import com.parking.grpc.PaymentResponse;
import com.parking.grpc.PaymentServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class LprService extends LprServiceGrpc.LprServiceImplBase {  

    private final PaymentServiceGrpc.PaymentServiceBlockingStub paymentStub;

    public LprService() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("payment-service", 9090)
                .usePlaintext()
                .build();
        paymentStub = PaymentServiceGrpc.newBlockingStub(channel);
    }

    @Override
    public void processLpr(LprRequest request, StreamObserver<LprResponse> responseObserver) {  
        // Extract license plate from image (mocked for now)
        String extractedLicensePlate = request.getImagePath().equals("test.jpg") ? "ABC123" : "UNKNOWN";

        // Check payment status via gRPC
        PaymentRequest paymentRequest = PaymentRequest.newBuilder()
                .setLicensePlate(extractedLicensePlate)
                .build();

        PaymentResponse paymentResponse = paymentStub.checkPayment(paymentRequest);

        // Construct response
        LprResponse response = LprResponse.newBuilder()
                .setLicensePlate(extractedLicensePlate)  
                .setIsPaid(paymentResponse.getIsPaid())  
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
