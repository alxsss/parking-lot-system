package com.parking.gate;

import org.springframework.stereotype.Service;
import com.parking.lpr.LprService;

@Service
public class GateService {
    private final LprService lprService;

    public GateService(LprService lprService) {
        this.lprService = lprService;
    }

    public String processEntry(String licensePlate) {
        if (lprService.checkPayment(licensePlate)) {
            return "Gate opening for: " + licensePlate;
        } else {
            return "Payment required. Gate remains closed.";
        }
    }
}