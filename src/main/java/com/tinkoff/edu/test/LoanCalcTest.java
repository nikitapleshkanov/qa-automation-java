package com.tinkoff.edu.test;

import com.tinkoff.edu.app.CalculationsWithAllParamsService;
import com.tinkoff.edu.app.LoanCalcController;
import com.tinkoff.edu.app.LoanCalcRepository;
import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;
import com.tinkoff.edu.app.LoanType;
import com.tinkoff.edu.app.StaticVariableLoanCalcRepository;

public class LoanCalcTest {

    public static void main(String... args) {
        LoanRequest loanRequest = new LoanRequest(10, 1000, LoanType.IP);
        LoanCalcRepository repository = new StaticVariableLoanCalcRepository();
        LoanCalcController controller = new LoanCalcController(new CalculationsWithAllParamsService(repository));
        LoanResponse loanResponse = controller.createRequest(loanRequest);
        System.out.println("Your request number is: " + loanResponse.getRequestId() + ';'
                + " Is your request accepted: " + loanResponse.getIsAccepted());
    }

}
