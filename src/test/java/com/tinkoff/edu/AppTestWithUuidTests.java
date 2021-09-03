package com.tinkoff.edu;

import com.tinkoff.edu.app.controller.LoanCalcController;
import com.tinkoff.edu.app.enums.LoanRequestType;
import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.model.UuidLoanRequest;
import com.tinkoff.edu.app.model.UuidLoanResponse;
import com.tinkoff.edu.app.repository.LoanCalcRepository;
import com.tinkoff.edu.app.repository.VariableLoanCalcRepository;
import com.tinkoff.edu.app.service.CalculationsWithAllParamsService;
import com.tinkoff.edu.app.service.LoanCalcService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AppTestWithUuidTests {

    private UuidLoanRequest loanRequest;
    private LoanCalcRepository repository = new VariableLoanCalcRepository();
    private LoanCalcService service = new CalculationsWithAllParamsService(repository);
    private LoanCalcController controller = new LoanCalcController(service);

    @Test
    @DisplayName("Проверка одобрения заявки: loanType = PERSON, amount = 10000, months = 12")
    public void checkApproveRequestForPerson1() {
        loanRequest = new UuidLoanRequest(12, 10000, LoanRequestType.PERSON, "Иванов Иван Иванович");
        UuidLoanResponse loanResponse = controller.createRequest(loanRequest);
        assertEquals(LoanResponseType.APPROVED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка одобрения заявки: loanType = PERSON, amount = 10000, months < 12")
    public void checkApproveRequestForPerson2() {
        loanRequest = new UuidLoanRequest(11, 10000, LoanRequestType.PERSON, "Иванов Иван Иванович");
        UuidLoanResponse loanResponse = controller.createRequest(loanRequest);
        assertEquals(LoanResponseType.APPROVED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка одобрения заявки: loanType = PERSON, amount < 10000, months < 12")
    public void checkApproveRequestForPerson3() {
        loanRequest = new UuidLoanRequest(11, 9000, LoanRequestType.PERSON, "Иванов Иван Иванович");
        UuidLoanResponse loanResponse = controller.createRequest(loanRequest);
        assertEquals(LoanResponseType.APPROVED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка одобрения заявки: loanType = PERSON, amount < 10000, months = 12")
    public void checkApproveRequestForPerson4() {
        loanRequest = new UuidLoanRequest(12, 9000, LoanRequestType.PERSON, "Иванов Иван Иванович");
        UuidLoanResponse loanResponse = controller.createRequest(loanRequest);
        assertEquals(LoanResponseType.APPROVED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = PERSON, amount < 10000, months > 12")
    public void checkDeclineRequestForPerson5() {
        loanRequest = new UuidLoanRequest(14, 9000, LoanRequestType.PERSON, "Иванов Иван Иванович");
        UuidLoanResponse loanResponse = controller.createRequest(loanRequest);
        assertEquals(LoanResponseType.DECLINED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = PERSON, amount = 10000, months > 12")
    public void checkDeclineRequestForPerson6() {
        loanRequest = new UuidLoanRequest(14, 10000, LoanRequestType.PERSON, "Иванов Иван Иванович");
        UuidLoanResponse loanResponse = controller.createRequest(loanRequest);
        assertEquals(LoanResponseType.DECLINED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = PERSON, amount > 10000, months > 12")
    public void checkDeclineRequestForPerson7() {
        loanRequest = new UuidLoanRequest(14, 10005, LoanRequestType.PERSON, "Иванов Иван Иванович");
        UuidLoanResponse loanResponse = controller.createRequest(loanRequest);
        assertEquals(LoanResponseType.DECLINED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка одобрения заявки: loanType = PERSON, amount > 10000, months = 12")
    public void checkApproveRequestForPerson8() {
        loanRequest = new UuidLoanRequest(12, 10005, LoanRequestType.PERSON, "Иванов Иван Иванович");
        UuidLoanResponse loanResponse = controller.createRequest(loanRequest);
        assertEquals(LoanResponseType.APPROVED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка одобрения заявки: loanType = PERSON, amount > 10000, months < 12")
    public void checkApproveRequestForPerson9() {
        loanRequest = new UuidLoanRequest(10, 10005, LoanRequestType.PERSON, "Иванов Иван Иванович");
        UuidLoanResponse loanResponse = controller.createRequest(loanRequest);
        assertEquals(LoanResponseType.APPROVED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = OOO, amount < 10000, months < 12")
    public void checkDeclineRequestForOOO1() {
        loanRequest = new UuidLoanRequest(11, 8000, LoanRequestType.OOO, "Иванов Иван Иванович");
        UuidLoanResponse loanResponse = controller.createRequest(loanRequest);
        assertEquals(LoanResponseType.DECLINED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = OOO, amount < 10000, months = 12")
    public void checkDeclineRequestForOOO2() {
        loanRequest = new UuidLoanRequest(12, 8000, LoanRequestType.OOO, "Иванов Иван Иванович");
        UuidLoanResponse loanResponse = controller.createRequest(loanRequest);
        assertEquals(LoanResponseType.DECLINED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = OOO, amount = 10000, months < 12")
    public void checkDeclineRequestForOOO3() {
        loanRequest = new UuidLoanRequest(11, 10000, LoanRequestType.OOO, "Иванов Иван Иванович");
        UuidLoanResponse loanResponse = controller.createRequest(loanRequest);
        assertEquals(LoanResponseType.DECLINED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = OOO, amount = 10000, months = 12")
    public void checkDeclineRequestForOOO4() {
        loanRequest = new UuidLoanRequest(12, 10000, LoanRequestType.OOO, "Иванов Иван Иванович");
        UuidLoanResponse loanResponse = controller.createRequest(loanRequest);
        assertEquals(LoanResponseType.DECLINED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = OOO, amount < 10000,  months > 12")
    public void checkDeclineRequestForOOO5() {
        loanRequest = new UuidLoanRequest(14, 8000, LoanRequestType.OOO, "Иванов Иван Иванович");
        UuidLoanResponse loanResponse = controller.createRequest(loanRequest);
        assertEquals(LoanResponseType.DECLINED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = OOO, amount = 10000,  months > 12")
    public void checkDeclineRequestForOOO6() {
        loanRequest = new UuidLoanRequest(14, 10000, LoanRequestType.OOO, "Иванов Иван Иванович");
        UuidLoanResponse loanResponse = controller.createRequest(loanRequest);
        assertEquals(LoanResponseType.DECLINED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка одобрения заявки: loanType = OOO, amount > 10000, months < 12")
    public void checkApproveRequestForOOO7() {
        loanRequest = new UuidLoanRequest(11, 10005, LoanRequestType.OOO, "Иванов Иван Иванович");
        UuidLoanResponse loanResponse = controller.createRequest(loanRequest);
        assertEquals(LoanResponseType.APPROVED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = OOO, amount > 10000, months = 12")
    public void checkDeclineRequestForOOO8() {
        loanRequest = new UuidLoanRequest(12, 10015, LoanRequestType.OOO, "Иванов Иван Иванович");
        UuidLoanResponse loanResponse = controller.createRequest(loanRequest);
        assertEquals(LoanResponseType.DECLINED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = OOO, amount > 10000, months > 12")
    public void checkDeclineRequestForOOO9() {
        loanRequest = new UuidLoanRequest(14, 10015, LoanRequestType.OOO, "Иванов Иван Иванович");
        UuidLoanResponse loanResponse = controller.createRequest(loanRequest);
        assertEquals(LoanResponseType.DECLINED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка отклонения заявки: loanType = IP")
    public void checkDeclineRequestForIP() {
        loanRequest = new UuidLoanRequest(12, 10000, LoanRequestType.IP, "Иванов Иван Иванович");
        UuidLoanResponse loanResponse = controller.createRequest(loanRequest);
        assertEquals(LoanResponseType.DECLINED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка выброса исключения для заявки = null")
    public void checkNullRequest() {
        try {
            controller.createRequest(null);
            fail("No exception caught");
        } catch (IllegalArgumentException exception) {
        }
    }

    @Test
    @DisplayName("Проверка выброса исключения для заявки с amount <= 0 ")
    public void shouldGetErrorWhenApplyZeroOrNegativeAmountRequest() {
        try {
            loanRequest = new UuidLoanRequest(12, 0, LoanRequestType.IP, "Иванов Иван Иванович");
            fail("No exception caught");
        } catch (IllegalArgumentException exception) {
        }
    }

    @Test
    @DisplayName("Проверка выброса исключения для заявки с months <= 0 ")
    public void shouldGetErrorWhenApplyZeroOrNegativeMonthsRequest() {
        try {
            loanRequest = new UuidLoanRequest(-10, 10000, LoanRequestType.IP, "Иванов Иван Иванович");
            fail("No exception caught");
        } catch (IllegalArgumentException exception) {
        }
    }

    @Test
    @DisplayName("Проверка получения статуса по uuid заявки")
    public void checkGettingRequestInfo() {
        loanRequest = new UuidLoanRequest(12, 10000, LoanRequestType.IP, "Иванов Иван Иванович");
        UuidLoanResponse loanResponse = controller.createRequest(loanRequest);
        LoanResponseType status = service.getRequestById(loanResponse.getRequestId());
        assertEquals(loanResponse.getIsAccepted(), status);
    }

    @Test
    @DisplayName("Проверка изменения статуса по uuid заявки")
    public void checkChangingRequestInfo() {
        loanRequest = new UuidLoanRequest(12, 10000, LoanRequestType.IP, "Иванов Иван Иванович");
        UuidLoanResponse loanResponse = controller.createRequest(loanRequest);
        assertEquals(LoanResponseType.DECLINED, loanResponse.getIsAccepted());
        service.setStatusRequestById(loanResponse.getRequestId(), LoanResponseType.APPROVED);
        assertEquals(LoanResponseType.APPROVED, loanResponse.getIsAccepted());
    }

    @Test
    @DisplayName("Проверка выброса exception при попытке получить статус несуществующей заявки")
    public void checkGettingExceptionForGettingNotExistRequest() {
        try {
            service.getRequestById(UUID.randomUUID());
            fail("No exception caught");
        } catch (NoSuchElementException exception) {
        }
    }

    @Test
    @DisplayName("Проверка выброса exception при попытке изменить статус несуществующей заявки")
    public void checkGettingExceptionForChangingNotExistRequest() {
        try {
            service.setStatusRequestById(UUID.randomUUID(), LoanResponseType.APPROVED);
            fail("No exception caught");
        } catch (NoSuchElementException exception) {
        }
    }

}
