package com.tinkoff.edu;


import com.tinkoff.edu.app.CalculationsWithAllParamsService;
import com.tinkoff.edu.app.LoanCalcController;
import com.tinkoff.edu.app.LoanCalcRepository;
import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanRequestType;
import com.tinkoff.edu.app.LoanResponse;
import com.tinkoff.edu.app.LoanResponseType;
import com.tinkoff.edu.app.VariableLoanCalcRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class AppTest {
    private LoanRequest loanRequest;
    private LoanCalcController controller;
    private LoanCalcRepository repository;


    @Test
    @DisplayName("Проверка одобрения заявки: loanType = PERSON, amount <= 10000, months <= 12")
    public void checkApproveRequestForPerson1() {
        loanRequest = new LoanRequest(12, 10000, LoanRequestType.PERSON);
        repository = new VariableLoanCalcRepository();
        controller = new LoanCalcController(new CalculationsWithAllParamsService(repository));
        LoanResponse loanResponse = controller.createRequest(loanRequest);
        System.out.println("Your request number is: " + loanResponse.getRequestId() + "; "
                + "Your request status is: " + loanResponse.getIsAccepted());
        finalCheck(LoanResponseType.APPROVED, loanResponse);
    }

    @Test
    @DisplayName("Проверка одобрения заявки: loanType = PERSON, amount <= 10000, months > 12")
    public void checkApproveRequestForPerson2() {
        loanRequest = new LoanRequest(14, 10000, LoanRequestType.PERSON);
        repository = new VariableLoanCalcRepository();
        controller = new LoanCalcController(new CalculationsWithAllParamsService(repository));
        LoanResponse loanResponse = controller.createRequest(loanRequest);
        System.out.println("Your request number is: " + loanResponse.getRequestId() + "; "
                + "Your request status is: " + loanResponse.getIsAccepted());
        finalCheck(LoanResponseType.DECLINED, loanResponse);
    }

    @Test
    @DisplayName("Проверка одобрения заявки: loanType = PERSON, amount > 10000, months > 12")
    public void checkApproveRequestForPerson3() {
        loanRequest = new LoanRequest(14, 10005, LoanRequestType.PERSON);
        repository = new VariableLoanCalcRepository();
        controller = new LoanCalcController(new CalculationsWithAllParamsService(repository));
        LoanResponse loanResponse = controller.createRequest(loanRequest);
        System.out.println("Your request number is: " + loanResponse.getRequestId() + "; "
                + "Your request status is: " + loanResponse.getIsAccepted());
        finalCheck(LoanResponseType.DECLINED, loanResponse);
    }

    @Test
    @DisplayName("Проверка одобрения заявки: loanType = PERSON, amount > 10000, months <= 12")
    public void checkApproveRequestForPerson4() {
        loanRequest = new LoanRequest(12, 10005, LoanRequestType.PERSON);
        repository = new VariableLoanCalcRepository();
        controller = new LoanCalcController(new CalculationsWithAllParamsService(repository));
        LoanResponse loanResponse = controller.createRequest(loanRequest);
        System.out.println("Your request number is: " + loanResponse.getRequestId() + "; "
                + "Your request status is: " + loanResponse.getIsAccepted());
        finalCheck(LoanResponseType.APPROVED, loanResponse);
    }

    @Test
    @DisplayName("Проверка одобрения заявки: loanType = PERSON, amount > 10000, months > 12")
    public void checkApproveRequestForPerson5() {
        loanRequest = new LoanRequest(12, 10005, LoanRequestType.PERSON);
        repository = new VariableLoanCalcRepository();
        controller = new LoanCalcController(new CalculationsWithAllParamsService(repository));
        LoanResponse loanResponse = controller.createRequest(loanRequest);
        System.out.println("Your request number is: " + loanResponse.getRequestId() + "; "
                + "Your request status is: " + loanResponse.getIsAccepted());
        finalCheck(LoanResponseType.APPROVED, loanResponse);
    }

    @Test
    @DisplayName("Проверка одобрения заявки: loanType = OOO, amount <= 10000, months <= 12")
    public void checkApproveRequestForOOO1() {
        loanRequest = new LoanRequest(12, 8000, LoanRequestType.OOO);
        repository = new VariableLoanCalcRepository();
        controller = new LoanCalcController(new CalculationsWithAllParamsService(repository));
        LoanResponse loanResponse = controller.createRequest(loanRequest);
        System.out.println("Your request number is: " + loanResponse.getRequestId() + "; "
                + "Your request status is: " + loanResponse.getIsAccepted());
        finalCheck(LoanResponseType.DECLINED, loanResponse);
    }

    @Test
    @DisplayName("Проверка одобрения заявки: loanType = OOO, amount <= 10000,  months > 12")
    public void checkApproveRequestForOOO2() {
        loanRequest = new LoanRequest(14, 8000, LoanRequestType.OOO);
        repository = new VariableLoanCalcRepository();
        controller = new LoanCalcController(new CalculationsWithAllParamsService(repository));
        LoanResponse loanResponse = controller.createRequest(loanRequest);
        System.out.println("Your request number is: " + loanResponse.getRequestId() + "; "
                + "Your request status is: " + loanResponse.getIsAccepted());
        finalCheck(LoanResponseType.DECLINED, loanResponse);
    }

    @Test
    @DisplayName("Проверка одобрения заявки: loanType = OOO, amount > 10000, months < 12")
    public void checkApproveRequestForOOO3() {
        loanRequest = new LoanRequest(11, 10005, LoanRequestType.OOO);
        repository = new VariableLoanCalcRepository();
        controller = new LoanCalcController(new CalculationsWithAllParamsService(repository));
        LoanResponse loanResponse = controller.createRequest(loanRequest);
        System.out.println("Your request number is: " + loanResponse.getRequestId() + "; "
                + "Your request status is: " + loanResponse.getIsAccepted());
        finalCheck(LoanResponseType.APPROVED, loanResponse);
    }

    @Test
    @DisplayName("Проверка одобрения заявки: loanType = PERSON, amount > 10000, months >= 12")
    public void checkApproveRequestForOOO4() {
        loanRequest = new LoanRequest(12, 10015, LoanRequestType.OOO);
        repository = new VariableLoanCalcRepository();
        controller = new LoanCalcController(new CalculationsWithAllParamsService(repository));
        LoanResponse loanResponse = controller.createRequest(loanRequest);
        System.out.println("Your request number is: " + loanResponse.getRequestId() + "; "
                + "Your request status is: " + loanResponse.getIsAccepted());
        finalCheck(LoanResponseType.DECLINED, loanResponse);
    }

    @Test
    @DisplayName("Проверка одобрения заявки: loanType = IP")
    public void checkApproveRequestForIP() {
        loanRequest = new LoanRequest(12, 10000, LoanRequestType.IP);
        repository = new VariableLoanCalcRepository();
        controller = new LoanCalcController(new CalculationsWithAllParamsService(repository));
        LoanResponse loanResponse = controller.createRequest(loanRequest);
        System.out.println("Your request number is: " + loanResponse.getRequestId() + "; "
                + "Your request status is: " + loanResponse.getIsAccepted());
        finalCheck(LoanResponseType.DECLINED, loanResponse);
    }

    @Test
    @DisplayName("Проверка одобрения заявки с произвольным id")
    public void initCustomRequestId() {
        loanRequest = new LoanRequest(10, 1000, LoanRequestType.IP);
        final int newId = 50;
        repository = new VariableLoanCalcRepository(newId);
        controller = new LoanCalcController(new CalculationsWithAllParamsService(repository));
        LoanResponse loanResponse = controller.createRequest(loanRequest);
        System.out.println("Your request number is: " + loanResponse.getRequestId() + "; "
                + "Your request status is: " + loanResponse.getIsAccepted());
        finalCheck(LoanResponseType.DECLINED, newId + 1, loanResponse);
    }

    @Test
    @DisplayName("Проверка выброса исключения для заявки = null")
    public void checkNullRequest() {
        repository = new VariableLoanCalcRepository();
        controller = new LoanCalcController(new CalculationsWithAllParamsService(repository));
        try {
            controller.createRequest(null);
            fail("No exception caught");
        } catch (IllegalArgumentException exception) {
        }
    }

    @Test
    @DisplayName("Проверка выброса сключения для заявки с amount <= 0 ")
    public void shouldGetErrorWhenApplyZeroOrNegativeAmountRequest() {
        try {
            loanRequest = new LoanRequest(12, 0, LoanRequestType.IP);
            fail("No exception caught");
        } catch (IllegalArgumentException exception) {
        }
    }

    @Test
    @DisplayName("Проверка выброса сключения для заявки с months <= 0 ")
    public void shouldGetErrorWhenApplyZeroOrNegativeMonthsRequest() {
        try {
            loanRequest = new LoanRequest(-10, 10000, LoanRequestType.IP);
            fail("No exception caught");
        } catch (IllegalArgumentException exception) {
        }
    }

    private void finalCheck(LoanResponseType expectedLoanType, LoanResponse loanResponse) {
        Assertions.assertEquals(expectedLoanType, loanResponse.getIsAccepted());
    }

    private void finalCheck(LoanResponseType expectedLoanType, int expectedId, LoanResponse loanResponse) {
        Assertions.assertEquals(expectedLoanType, loanResponse.getIsAccepted());
        Assertions.assertEquals(expectedId, loanResponse.getRequestId());
    }

}
