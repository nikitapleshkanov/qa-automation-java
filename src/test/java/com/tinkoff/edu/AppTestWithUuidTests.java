package com.tinkoff.edu;

import com.tinkoff.edu.app.controller.LoanCalcController;
import com.tinkoff.edu.app.enums.LoanRequestType;
import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.exceptions.RequestException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        final IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> controller.createRequest(null)
        );
        assertTrue(thrown.getMessage().equals("Значение request передано = null"));
    }

    @Test
    @DisplayName("Проверка выброса исключения для заявки с amount <= 0 ")
    public void shouldGetErrorWhenApplyZeroOrNegativeAmountRequest() {
        final IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new UuidLoanRequest(12, 0, LoanRequestType.IP, "Иванов Иван Иванович")
        );
        assertTrue(thrown.getMessage().equals("Указано значение amount <= 0"));
    }

    @Test
    @DisplayName("Проверка выброса исключения для заявки с months <= 0 ")
    public void shouldGetErrorWhenApplyZeroOrNegativeMonthsRequest() {
        final IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new UuidLoanRequest(-10, 10000, LoanRequestType.IP, "Иванов Иван Иванович")
        );
        assertTrue(thrown.getMessage().equals("Указано значение month <= 0"));
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
        final NoSuchElementException thrown = assertThrows(
                NoSuchElementException.class,
                () -> service.getRequestById(UUID.randomUUID())
        );
        assertTrue(thrown.getMessage().equals("Элемент с полученным id не найден"));
    }

    @Test
    @DisplayName("Проверка выброса exception при попытке изменить статус несуществующей заявки")
    public void checkGettingExceptionForChangingNotExistRequest() {
        final NoSuchElementException thrown = assertThrows(
                NoSuchElementException.class,
                () -> service.setStatusRequestById(UUID.randomUUID(), LoanResponseType.APPROVED)
        );
        assertTrue(thrown.getMessage().equals("Элемент массива с полученным id не найден"));
    }

    @Test
    @DisplayName("Проверка выброса exception при попытке указать пустую строку в поле name")
    public void checkGettingExceptionForUsingEmptyNameStringRequest() {
        final IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new UuidLoanRequest(12, 10000, LoanRequestType.IP, "")
        );
        assertTrue(thrown.getMessage().equals("Значение ФИО указано пустое или null"));
    }

    @Test
    @DisplayName("Проверка выброса exception при попытке указать name = null")
    public void checkGettingExceptionForUsingNullNameRequest() {
        final IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new UuidLoanRequest(12, 10000, LoanRequestType.IP, null)
        );
        assertTrue(thrown.getMessage().equals("Значение ФИО указано пустое или null"));
    }

    @Test
    @DisplayName("Проверка выброса exception при попытке указать loanType = null")
    public void checkGettingExceptionForUsingNullLoanTypeRequest() {
        final IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new UuidLoanRequest(12, 10000, null, "Иванов Иван Иванович")
        );
        assertTrue(thrown.getMessage().equals("Значение типа заявки указано null"));
    }

    @Test
    @DisplayName("Проверка выброса exception при попытке указать ФИО короче 10 символов")
    public void checkGettingExceptionForUsingShortNameRequest() {
        final RequestException thrown = assertThrows(
                RequestException.class,
                () -> new UuidLoanRequest(12, 10000, LoanRequestType.IP, "Ива")
        );
        assertTrue(thrown.getMessage().equals("Некорректная длина ФИО в заявке"));
    }

    @Test
    @DisplayName("Проверка выброса exception при попытке указать ФИО длиннее 100 символов")
    public void checkGettingExceptionForUsingLongNameRequest() {
        final RequestException thrown = assertThrows(
                RequestException.class,
                () -> new UuidLoanRequest(12, 10000, LoanRequestType.IP, "Ивааанннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннн")
        );
        assertTrue(thrown.getMessage().equals("Некорректная длина ФИО в заявке"));
    }

    @Test
    @DisplayName("Проверка выброса exception при попытке указать количество месяцев > 100")
    public void checkGettingExceptionForUsingHighMonthValueRequest() {
        final RequestException thrown = assertThrows(
                RequestException.class,
                () -> new UuidLoanRequest(101, 10000, LoanRequestType.IP, "Иванов Иван Иванович")
        );
        assertTrue(thrown.getMessage().equals("Некорректнаое число месяцев в заявке"));
    }

    @Test
    @DisplayName("Проверка выброса exception при попытке указать значение суммы < 0.01")
    public void checkGettingExceptionForUsingLowAmountValueRequest() {
        final RequestException thrown = assertThrows(
                RequestException.class,
                () -> new UuidLoanRequest(12, 0.009, LoanRequestType.IP, "Иванов Иван Иванович")
        );
        assertTrue(thrown.getMessage().equals("Некорректная сумма в заявке"));
    }

    @Test
    @DisplayName("Проверка выброса exception при попытке указать значение суммы > 999999.99")
    public void checkGettingExceptionForUsingHighAmountValueRequest() {
        final RequestException thrown = assertThrows(
                RequestException.class,
                () -> new UuidLoanRequest(12, 1000000, LoanRequestType.IP, "Иванов Иван Иванович")
        );
        assertTrue(thrown.getMessage().equals("Некорректная сумма в заявке"));
    }

}
