package com.tinkoff.edu;


import com.tinkoff.edu.app.CalculationsWithAllParamsService;
import com.tinkoff.edu.app.LoanCalcController;
import com.tinkoff.edu.app.LoanCalcRepository;
import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;
import com.tinkoff.edu.app.LoanResponseType;
import com.tinkoff.edu.app.LoanType;
import com.tinkoff.edu.app.StaticVariableLoanCalcRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AppTest {
    private LoanRequest loanRequest;
    private LoanCalcController controller;
    private LoanCalcRepository repository;

    @BeforeEach
    public void init() {
        loanRequest = new LoanRequest(10, 1000, LoanType.IP);
        repository = new StaticVariableLoanCalcRepository();
        controller = new LoanCalcController(new CalculationsWithAllParamsService(repository));
    }

    @Test
    @DisplayName("Проверка одобрения заявки")
    public void shouldAnswerWithTrue() {
        LoanResponse loanResponse = controller.createRequest(loanRequest);
        System.out.println("Your request number is: " + loanResponse.getRequestId() + "; "
                + "Your request status is: " + loanResponse.getIsAccepted());
        Assertions.assertEquals(LoanResponseType.APPROVED, loanResponse.getIsAccepted());
        Assertions.assertEquals(1, loanResponse.getRequestId());
        finalCheck(LoanResponseType.APPROVED, 1, loanResponse);
    }

    @Test
    @DisplayName("Проверка одобрения заявки с произвольным id")
    public void initCustomRequestId() {
        int newId = 50;
        repository.setRequestId(newId);
        LoanResponse loanResponse = controller.createRequest(loanRequest);
        System.out.println("Your request number is: " + loanResponse.getRequestId() + "; "
                + "Your request status is: " + loanResponse.getIsAccepted());
        finalCheck(LoanResponseType.APPROVED, newId + 1, loanResponse);
    }

    private void finalCheck(LoanResponseType expectedLoanType, int expectedId, LoanResponse loanResponse) {
        Assertions.assertEquals(expectedLoanType, loanResponse.getIsAccepted());
        Assertions.assertEquals(expectedId, loanResponse.getRequestId());
    }

}
