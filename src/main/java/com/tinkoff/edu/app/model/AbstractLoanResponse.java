package com.tinkoff.edu.app.model;

import com.tinkoff.edu.app.enums.LoanResponseType;

public abstract class AbstractLoanResponse {

    public abstract void setIsAccepted(LoanResponseType isAccepted);

    public abstract void printLoanResponseInfo();

}
