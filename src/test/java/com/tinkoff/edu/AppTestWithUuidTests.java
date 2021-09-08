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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTestWithUuidTests {

    static Stream<Arguments> testNameDataProvider() {
        return Stream.of(
                Arguments.arguments("значение ФИО указано короче 10 символов", "Ива"),
                Arguments.arguments("значение ФИО указано длиннее 100 символов", "Ивааанннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннн")
        );
    }

    static Stream<Arguments> testEmptyOrNullNameDataProvider() {
        return Stream.of(
                Arguments.arguments("указано пустое значение ФИО ", ""),
                Arguments.arguments("указано значение ФИО = null", null)
        );
    }

    static Stream<Arguments> testAmountDataProvider() {
        return Stream.of(
                Arguments.arguments("значение суммы < 0.01", 0.009),
                Arguments.arguments("значение суммы > 999999.99", 1000000.0)
        );
    }

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

    @ParameterizedTest(name = "{index}: Проверка выброса exception: {0}")
    @MethodSource("testEmptyOrNullNameDataProvider")
    public void checkGettingExceptionForUsingWrongEmptyNameRequest(String title, String name) {
        final IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new UuidLoanRequest(12, 10000, LoanRequestType.IP, name)
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

    @ParameterizedTest(name = "{index}: Проверка выброса exception: {0}")
    @MethodSource("testNameDataProvider")
    public void checkGettingExceptionForUsingWrongNameRequest(String title, String name) {
        final RequestException thrown = assertThrows(
                RequestException.class,
                () -> new UuidLoanRequest(12, 10000, LoanRequestType.IP, name)
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

    @ParameterizedTest(name = "{index}: Проверка выброса exception: {0}")
    @MethodSource("testAmountDataProvider")
    public void checkGettingExceptionForUsingWrongAmountRequest(String title, Double amount) {
        final RequestException thrown = assertThrows(
                RequestException.class,
                () -> new UuidLoanRequest(12, amount, LoanRequestType.IP, "Иванов Иван Иванович")
        );
        assertTrue(thrown.getMessage().equals("Некорректная сумма в заявке"));
    }

    @Test
    @DisplayName("Проверка получение всех заявок с типом OOO")
    public void checkGettingAllOOORequests() {
        ArrayList<UuidLoanResponse> containsList = new ArrayList<>();
        loanRequest = new UuidLoanRequest(10, 10005, LoanRequestType.OOO, "Иванов Иван Иванович");
        UuidLoanResponse loanResponse1 = controller.createRequest(loanRequest);
        containsList.add(loanResponse1);
        loanRequest = new UuidLoanRequest(10, 10005, LoanRequestType.OOO, "Иванов Иван Иванович");
        UuidLoanResponse loanResponse2 = controller.createRequest(loanRequest);
        containsList.add(loanResponse2);
        loanRequest = new UuidLoanRequest(10, 10005, LoanRequestType.PERSON, "Иванов Иван Иванович");
        controller.createRequest(loanRequest);
        List<UuidLoanResponse> responses = service.getAllRequestsWithType(LoanRequestType.OOO);
        assertTrue((responses.containsAll(containsList)) && (responses.size() == containsList.size()));
    }

    @Test
    @DisplayName("Проверка получения суммы всех заявок с типом OOO")
    public void checkGettingAllOOOAmountsRequests() {
        Double sum = 0.0;
        loanRequest = new UuidLoanRequest(10, 10001, LoanRequestType.OOO, "Иванов Иван Иванович");
        sum += loanRequest.getAmount();
        UuidLoanResponse loanResponse1 = controller.createRequest(loanRequest);
        loanRequest = new UuidLoanRequest(10, 10007, LoanRequestType.OOO, "Иванов Иван Иванович");
        sum += loanRequest.getAmount();
        UuidLoanResponse loanResponse2 = controller.createRequest(loanRequest);
        loanRequest = new UuidLoanRequest(10, 10011, LoanRequestType.PERSON, "Иванов Иван Иванович");
        controller.createRequest(loanRequest);
        assertEquals(sum, service.getAllRequestsAmountWithType(LoanRequestType.OOO));
    }

}
