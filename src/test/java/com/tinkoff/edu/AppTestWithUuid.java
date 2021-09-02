package com.tinkoff.edu;


import com.tinkoff.edu.app.controller.LoanCalcController;
import com.tinkoff.edu.app.enums.LoanRequestType;
import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.model.UuidLoanRequest;
import com.tinkoff.edu.app.model.UuidLoanResponse;
import com.tinkoff.edu.app.repository.UuidLoanCalсRepository;
import com.tinkoff.edu.app.service.CalculationsWithAllParamsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTestWithUuid {
    private UuidLoanRequest loanRequest;
    private LoanCalcController controller;


    @Test
    @DisplayName("Проверка одобрения заявки: loanType = PERSON, amount = 10000, months = 12")
    public void checkApproveRequestForPerson1() {
        loanRequest = new UuidLoanRequest(12, 10000, LoanRequestType.PERSON, "Иванов Иван Иванович");
        UuidLoanResponse loanResponse = (UuidLoanResponse) buildController().createRequest(loanRequest);
        assertEquals(LoanResponseType.APPROVED, loanResponse.getIsAccepted());
    }


    private LoanCalcController buildController() {
        return new LoanCalcController(new CalculationsWithAllParamsService(new UuidLoanCalсRepository()));
    }

}
