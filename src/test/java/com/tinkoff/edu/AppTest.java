package com.tinkoff.edu;


import com.tinkoff.edu.app.CalculationsWithAllParamsService;
import com.tinkoff.edu.app.LoanCalcController;
import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanRequestType;
import com.tinkoff.edu.app.LoanResponse;
import com.tinkoff.edu.app.LoanResponseType;
import com.tinkoff.edu.app.VariableLoanCalcRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AppTest {
    private LoanRequest loanRequest;
    private LoanCalcController controller;


    @Test
    @DisplayName("Проверка одобрения заявки: loanType = PERSON, amount = 10000, months = 12")
    public void checkApproveRequestForPerson1() {
        loanRequest = new LoanRequest(12, 10000, LoanRequestType.PERSON);
        LoanResponse loanResponse = buildController().createRequest(loanRequest);
        loanResponse.printLoanResponseInfo();
        finalCheck(LoanResponseType.APPROVED, loanResponse);
    }

    @Test
    @DisplayName("Проверка одобрения заявки: loanType = PERSON, amount = 10000, months < 12")
    public void checkApproveRequestForPerson2() {
        loanRequest = new LoanRequest(11, 10000, LoanRequestType.PERSON);
        LoanResponse loanResponse = buildController().createRequest(loanRequest);
        loanResponse.printLoanResponseInfo();
        finalCheck(LoanResponseType.APPROVED, loanResponse);
    }

    @Test
    @DisplayName("Проверка одобрения заявки: loanType = PERSON, amount < 10000, months < 12")
    public void checkApproveRequestForPerson3() {
        loanRequest = new LoanRequest(11, 9000, LoanRequestType.PERSON);
        LoanResponse loanResponse = buildController().createRequest(loanRequest);
        loanResponse.printLoanResponseInfo();
        finalCheck(LoanResponseType.APPROVED, loanResponse);
    }

    @Test
    @DisplayName("Проверка одобрения заявки: loanType = PERSON, amount < 10000, months = 12")
    public void checkApproveRequestForPerson4() {
        loanRequest = new LoanRequest(12, 9000, LoanRequestType.PERSON);
        LoanResponse loanResponse = buildController().createRequest(loanRequest);
        loanResponse.printLoanResponseInfo();
        finalCheck(LoanResponseType.APPROVED, loanResponse);
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = PERSON, amount < 10000, months > 12")
    public void checkDeclineRequestForPerson5() {
        loanRequest = new LoanRequest(14, 9000, LoanRequestType.PERSON);
        LoanResponse loanResponse = buildController().createRequest(loanRequest);
        loanResponse.printLoanResponseInfo();
        finalCheck(LoanResponseType.DECLINED, loanResponse);
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = PERSON, amount = 10000, months > 12")
    public void checkDeclineRequestForPerson6() {
        loanRequest = new LoanRequest(14, 10000, LoanRequestType.PERSON);
        LoanResponse loanResponse = buildController().createRequest(loanRequest);
        loanResponse.printLoanResponseInfo();
        finalCheck(LoanResponseType.DECLINED, loanResponse);
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = PERSON, amount > 10000, months > 12")
    public void checkDeclineRequestForPerson7() {
        loanRequest = new LoanRequest(14, 10005, LoanRequestType.PERSON);
        LoanResponse loanResponse = buildController().createRequest(loanRequest);
        loanResponse.printLoanResponseInfo();
        finalCheck(LoanResponseType.DECLINED, loanResponse);
    }

    @Test
    @DisplayName("Проверка одобрения заявки: loanType = PERSON, amount > 10000, months = 12")
    public void checkApproveRequestForPerson8() {
        loanRequest = new LoanRequest(12, 10005, LoanRequestType.PERSON);
        LoanResponse loanResponse = buildController().createRequest(loanRequest);
        loanResponse.printLoanResponseInfo();
        finalCheck(LoanResponseType.APPROVED, loanResponse);
    }

    @Test
    @DisplayName("Проверка одобрения заявки: loanType = PERSON, amount > 10000, months < 12")
    public void checkApproveRequestForPerson9() {
        loanRequest = new LoanRequest(10, 10005, LoanRequestType.PERSON);
        LoanResponse loanResponse = buildController().createRequest(loanRequest);
        loanResponse.printLoanResponseInfo();
        finalCheck(LoanResponseType.APPROVED, loanResponse);
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = OOO, amount < 10000, months < 12")
    public void checkDeclineRequestForOOO1() {
        loanRequest = new LoanRequest(11, 8000, LoanRequestType.OOO);
        LoanResponse loanResponse = buildController().createRequest(loanRequest);
        loanResponse.printLoanResponseInfo();
        finalCheck(LoanResponseType.DECLINED, loanResponse);
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = OOO, amount < 10000, months = 12")
    public void checkDeclineRequestForOOO2() {
        loanRequest = new LoanRequest(12, 8000, LoanRequestType.OOO);
        LoanResponse loanResponse = buildController().createRequest(loanRequest);
        loanResponse.printLoanResponseInfo();
        finalCheck(LoanResponseType.DECLINED, loanResponse);
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = OOO, amount = 10000, months < 12")
    public void checkDeclineRequestForOOO3() {
        loanRequest = new LoanRequest(11, 10000, LoanRequestType.OOO);
        LoanResponse loanResponse = buildController().createRequest(loanRequest);
        loanResponse.printLoanResponseInfo();
        finalCheck(LoanResponseType.DECLINED, loanResponse);
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = OOO, amount = 10000, months = 12")
    public void checkDeclineRequestForOOO4() {
        loanRequest = new LoanRequest(12, 10000, LoanRequestType.OOO);
        LoanResponse loanResponse = buildController().createRequest(loanRequest);
        loanResponse.printLoanResponseInfo();
        finalCheck(LoanResponseType.DECLINED, loanResponse);
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = OOO, amount < 10000,  months > 12")
    public void checkDeclineRequestForOOO5() {
        loanRequest = new LoanRequest(14, 8000, LoanRequestType.OOO);
        LoanResponse loanResponse = buildController().createRequest(loanRequest);
        loanResponse.printLoanResponseInfo();
        finalCheck(LoanResponseType.DECLINED, loanResponse);
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = OOO, amount = 10000,  months > 12")
    public void checkDeclineRequestForOOO6() {
        loanRequest = new LoanRequest(14, 10000, LoanRequestType.OOO);
        LoanResponse loanResponse = buildController().createRequest(loanRequest);
        loanResponse.printLoanResponseInfo();
        finalCheck(LoanResponseType.DECLINED, loanResponse);
    }

    @Test
    @DisplayName("Проверка одобрения заявки: loanType = OOO, amount > 10000, months < 12")
    public void checkApproveRequestForOOO7() {
        loanRequest = new LoanRequest(11, 10005, LoanRequestType.OOO);
        LoanResponse loanResponse = buildController().createRequest(loanRequest);
        loanResponse.printLoanResponseInfo();
        finalCheck(LoanResponseType.APPROVED, loanResponse);
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = OOO, amount > 10000, months = 12")
    public void checkDeclineRequestForOOO8() {
        loanRequest = new LoanRequest(12, 10015, LoanRequestType.OOO);
        LoanResponse loanResponse = buildController().createRequest(loanRequest);
        loanResponse.printLoanResponseInfo();
        finalCheck(LoanResponseType.DECLINED, loanResponse);
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = OOO, amount > 10000, months > 12")
    public void checkDeclineRequestForOOO9() {
        loanRequest = new LoanRequest(14, 10015, LoanRequestType.OOO);
        LoanResponse loanResponse = buildController().createRequest(loanRequest);
        loanResponse.printLoanResponseInfo();
        finalCheck(LoanResponseType.DECLINED, loanResponse);
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = IP")
    public void checkDeclineRequestForIP() {
        loanRequest = new LoanRequest(12, 10000, LoanRequestType.IP);
        LoanResponse loanResponse = buildController().createRequest(loanRequest);
        loanResponse.printLoanResponseInfo();
        finalCheck(LoanResponseType.DECLINED, loanResponse);
    }

    @Test
    @DisplayName("Проверка отклонения заявки с произвольным id")
    public void initDeclineRequestId() {
        loanRequest = new LoanRequest(10, 1000, LoanRequestType.IP);
        final int newId = 50;
        controller = new LoanCalcController(new CalculationsWithAllParamsService(new VariableLoanCalcRepository(newId)));
        LoanResponse loanResponse = controller.createRequest(loanRequest);
        loanResponse.printLoanResponseInfo();
        finalCheck(LoanResponseType.DECLINED, newId + 1, loanResponse);
    }

    @Test
    @DisplayName("Проверка выброса исключения для заявки = null")
    public void checkNullRequest() {
        try {
            buildController().createRequest(null);
            fail("No exception caught");
        } catch (IllegalArgumentException exception) {
        }
    }

    @Test
    @DisplayName("Проверка выброса исключения для заявки с amount <= 0 ")
    public void shouldGetErrorWhenApplyZeroOrNegativeAmountRequest() {
        try {
            loanRequest = new LoanRequest(12, 0, LoanRequestType.IP);
            fail("No exception caught");
        } catch (IllegalArgumentException exception) {
        }
    }

    @Test
    @DisplayName("Проверка выброса исключения для заявки с months <= 0 ")
    public void shouldGetErrorWhenApplyZeroOrNegativeMonthsRequest() {
        try {
            loanRequest = new LoanRequest(-10, 10000, LoanRequestType.IP);
            fail("No exception caught");
        } catch (IllegalArgumentException exception) {
        }
    }

    private void finalCheck(LoanResponseType expectedLoanType, LoanResponse loanResponse) {
        assertEquals(expectedLoanType, loanResponse.getIsAccepted());
    }

    private void finalCheck(LoanResponseType expectedLoanType, int expectedId, LoanResponse loanResponse) {
        assertEquals(expectedLoanType, loanResponse.getIsAccepted());
        assertEquals(expectedId, loanResponse.getRequestId());
    }

    private LoanCalcController buildController() {
        return new LoanCalcController(new CalculationsWithAllParamsService(new VariableLoanCalcRepository()));
    }

}
