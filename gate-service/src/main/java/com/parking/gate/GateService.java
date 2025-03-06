package com.parking.gate;

import com.parking.grpc.GateRequest;
import com.parking.grpc.GateResponse;
import com.parking.grpc.GateServiceGrpc;

import io.grpc.stub.StreamObserver;

public class GateService extends GateServiceGrpc.GateServiceImplBase { 
    @Override
    public void openGate(GateRequest request, StreamObserver<GateResponse> responseObserver) {  // ✅ Correct method signature
        String message = "Gate opened for license plate: " + request.getLicensePlate();

        GateResponse response = GateResponse.newBuilder()
                .setMessage(message)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
