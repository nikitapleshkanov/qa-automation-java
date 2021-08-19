package com.tinkoff.edu.test;

import com.tinkoff.edu.app.LoanCalcController;
import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;

public class LoanCalcTest {

    public static void main(String... args) {
        LoanRequest loanRequest = new LoanRequest(10, 1000);
        LoanCalcController controller = new LoanCalcController();
        LoanResponse loanResponse = controller.createRequest(loanRequest);
        System.out.println("Your request number is: " + loanResponse.getRequestId() + ';'
                + " Is your request accepted: " + loanResponse.getIsAccepted());
    }

}
