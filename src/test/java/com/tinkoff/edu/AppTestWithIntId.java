package com.tinkoff.edu;


import com.tinkoff.edu.app.controller.LoanCalcController;
import com.tinkoff.edu.app.enums.LoanRequestType;
import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.model.IntIdLoanRequest;
import com.tinkoff.edu.app.model.IntIdLoanResponse;
import com.tinkoff.edu.app.repository.VariableLoanCalcRepository;
import com.tinkoff.edu.app.service.CalculationsWithAllParamsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AppTestWithIntId {
    private IntIdLoanRequest loanRequest;
    private LoanCalcController controller;


    @Test
    @DisplayName("Проверка одобрения заявки: loanType = PERSON, amount = 10000, months = 12")
    public void checkApproveRequestForPerson1() {
        loanRequest = new IntIdLoanRequest(12, 10000, LoanRequestType.PERSON, "Иванов Иван Иванович");
        IntIdLoanResponse loanResponse = (IntIdLoanResponse) buildController().createRequest(loanRequest);
        assertEquals(LoanResponseType.APPROVED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка одобрения заявки: loanType = PERSON, amount = 10000, months < 12")
    public void checkApproveRequestForPerson2() {
        loanRequest = new IntIdLoanRequest(11, 10000, LoanRequestType.PERSON, "Иванов Иван Иванович");
        IntIdLoanResponse loanResponse = (IntIdLoanResponse) buildController().createRequest(loanRequest);
        assertEquals(LoanResponseType.APPROVED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка одобрения заявки: loanType = PERSON, amount < 10000, months < 12")
    public void checkApproveRequestForPerson3() {
        loanRequest = new IntIdLoanRequest(11, 9000, LoanRequestType.PERSON, "Иванов Иван Иванович");
        IntIdLoanResponse loanResponse = (IntIdLoanResponse) buildController().createRequest(loanRequest);
        assertEquals(LoanResponseType.APPROVED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка одобрения заявки: loanType = PERSON, amount < 10000, months = 12")
    public void checkApproveRequestForPerson4() {
        loanRequest = new IntIdLoanRequest(12, 9000, LoanRequestType.PERSON, "Иванов Иван Иванович");
        IntIdLoanResponse loanResponse = (IntIdLoanResponse) buildController().createRequest(loanRequest);
        assertEquals(LoanResponseType.APPROVED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = PERSON, amount < 10000, months > 12")
    public void checkDeclineRequestForPerson5() {
        loanRequest = new IntIdLoanRequest(14, 9000, LoanRequestType.PERSON, "Иванов Иван Иванович");
        IntIdLoanResponse loanResponse = (IntIdLoanResponse) buildController().createRequest(loanRequest);
        assertEquals(LoanResponseType.DECLINED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = PERSON, amount = 10000, months > 12")
    public void checkDeclineRequestForPerson6() {
        loanRequest = new IntIdLoanRequest(14, 10000, LoanRequestType.PERSON, "Иванов Иван Иванович");
        IntIdLoanResponse loanResponse = (IntIdLoanResponse) buildController().createRequest(loanRequest);
        assertEquals(LoanResponseType.DECLINED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = PERSON, amount > 10000, months > 12")
    public void checkDeclineRequestForPerson7() {
        loanRequest = new IntIdLoanRequest(14, 10005, LoanRequestType.PERSON, "Иванов Иван Иванович");
        IntIdLoanResponse loanResponse = (IntIdLoanResponse) buildController().createRequest(loanRequest);
        assertEquals(LoanResponseType.DECLINED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка одобрения заявки: loanType = PERSON, amount > 10000, months = 12")
    public void checkApproveRequestForPerson8() {
        loanRequest = new IntIdLoanRequest(12, 10005, LoanRequestType.PERSON, "Иванов Иван Иванович");
        IntIdLoanResponse loanResponse = (IntIdLoanResponse) buildController().createRequest(loanRequest);
        assertEquals(LoanResponseType.APPROVED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка одобрения заявки: loanType = PERSON, amount > 10000, months < 12")
    public void checkApproveRequestForPerson9() {
        loanRequest = new IntIdLoanRequest(10, 10005, LoanRequestType.PERSON, "Иванов Иван Иванович");
        IntIdLoanResponse loanResponse = (IntIdLoanResponse) buildController().createRequest(loanRequest);
        assertEquals(LoanResponseType.APPROVED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = OOO, amount < 10000, months < 12")
    public void checkDeclineRequestForOOO1() {
        loanRequest = new IntIdLoanRequest(11, 8000, LoanRequestType.OOO, "Иванов Иван Иванович");
        IntIdLoanResponse loanResponse = (IntIdLoanResponse) buildController().createRequest(loanRequest);
        assertEquals(LoanResponseType.DECLINED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = OOO, amount < 10000, months = 12")
    public void checkDeclineRequestForOOO2() {
        loanRequest = new IntIdLoanRequest(12, 8000, LoanRequestType.OOO, "Иванов Иван Иванович");
        IntIdLoanResponse loanResponse = (IntIdLoanResponse) buildController().createRequest(loanRequest);
        assertEquals(LoanResponseType.DECLINED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = OOO, amount = 10000, months < 12")
    public void checkDeclineRequestForOOO3() {
        loanRequest = new IntIdLoanRequest(11, 10000, LoanRequestType.OOO, "Иванов Иван Иванович");
        IntIdLoanResponse loanResponse = (IntIdLoanResponse) buildController().createRequest(loanRequest);
        assertEquals(LoanResponseType.DECLINED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = OOO, amount = 10000, months = 12")
    public void checkDeclineRequestForOOO4() {
        loanRequest = new IntIdLoanRequest(12, 10000, LoanRequestType.OOO, "Иванов Иван Иванович");
        IntIdLoanResponse loanResponse = (IntIdLoanResponse) buildController().createRequest(loanRequest);
        assertEquals(LoanResponseType.DECLINED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = OOO, amount < 10000,  months > 12")
    public void checkDeclineRequestForOOO5() {
        loanRequest = new IntIdLoanRequest(14, 8000, LoanRequestType.OOO, "Иванов Иван Иванович");
        IntIdLoanResponse loanResponse = (IntIdLoanResponse) buildController().createRequest(loanRequest);
        assertEquals(LoanResponseType.DECLINED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = OOO, amount = 10000,  months > 12")
    public void checkDeclineRequestForOOO6() {
        loanRequest = new IntIdLoanRequest(14, 10000, LoanRequestType.OOO, "Иванов Иван Иванович");
        IntIdLoanResponse loanResponse = (IntIdLoanResponse) buildController().createRequest(loanRequest);
        assertEquals(LoanResponseType.DECLINED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка одобрения заявки: loanType = OOO, amount > 10000, months < 12")
    public void checkApproveRequestForOOO7() {
        loanRequest = new IntIdLoanRequest(11, 10005, LoanRequestType.OOO, "Иванов Иван Иванович");
        IntIdLoanResponse loanResponse = (IntIdLoanResponse) buildController().createRequest(loanRequest);
        assertEquals(LoanResponseType.APPROVED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = OOO, amount > 10000, months = 12")
    public void checkDeclineRequestForOOO8() {
        loanRequest = new IntIdLoanRequest(12, 10015, LoanRequestType.OOO, "Иванов Иван Иванович");
        IntIdLoanResponse loanResponse = (IntIdLoanResponse) buildController().createRequest(loanRequest);
        assertEquals(LoanResponseType.DECLINED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = OOO, amount > 10000, months > 12")
    public void checkDeclineRequestForOOO9() {
        loanRequest = new IntIdLoanRequest(14, 10015, LoanRequestType.OOO, "Иванов Иван Иванович");
        IntIdLoanResponse loanResponse = (IntIdLoanResponse) buildController().createRequest(loanRequest);
        assertEquals(LoanResponseType.DECLINED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = IP")
    public void checkDeclineRequestForIP() {
        loanRequest = new IntIdLoanRequest(12, 10000, LoanRequestType.IP, "Иванов Иван Иванович");
        IntIdLoanResponse loanResponse = (IntIdLoanResponse) buildController().createRequest(loanRequest);
        assertEquals(LoanResponseType.DECLINED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка отклонения заявки с произвольным id")
    public void initDeclineRequestId() {
        loanRequest = new IntIdLoanRequest(10, 1000, LoanRequestType.IP, "Иванов Иван Иванович");
        final int newId = 50;
        controller = new LoanCalcController(new CalculationsWithAllParamsService(new VariableLoanCalcRepository(newId)));
        IntIdLoanResponse loanResponse = (IntIdLoanResponse) controller.createRequest(loanRequest);
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
            loanRequest = new IntIdLoanRequest(12, 0, LoanRequestType.IP, "Иванов Иван Иванович");
            fail("No exception caught");
        } catch (IllegalArgumentException exception) {
        }
    }

    @Test
    @DisplayName("Проверка выброса исключения для заявки с months <= 0 ")
    public void shouldGetErrorWhenApplyZeroOrNegativeMonthsRequest() {
        try {
            loanRequest = new IntIdLoanRequest(-10, 10000, LoanRequestType.IP, "Иванов Иван Иванович");
            fail("No exception caught");
        } catch (IllegalArgumentException exception) {
        }
    }

    private void finalCheck(LoanResponseType expectedLoanType, int expectedId, IntIdLoanResponse loanResponse) {
        assertEquals(expectedLoanType, loanResponse.getIsAccepted());
        assertEquals(expectedId, loanResponse.getRequestId());
    }

    private LoanCalcController buildController() {
        return new LoanCalcController(new CalculationsWithAllParamsService(new VariableLoanCalcRepository()));
    }

}
