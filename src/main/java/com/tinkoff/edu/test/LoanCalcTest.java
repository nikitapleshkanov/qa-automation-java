package com.tinkoff.edu.test;

import com.tinkoff.edu.app.LoanCalcController;

public class LoanCalcTest {

    public static void main(String... args) {
        int requestId = LoanCalcController.createRequest();
        System.out.println("Your request number is: " + requestId);
    }

}
