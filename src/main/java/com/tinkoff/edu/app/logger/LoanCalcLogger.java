package com.tinkoff.edu.app.logger;


import com.tinkoff.edu.app.model.UuidLoanResponse;

/**
 * Describe logging of application work
 */
public class LoanCalcLogger {

    public static void log(UuidLoanResponse loanResponse) {
        loanResponse.printLoanResponseInfo();
    }
}
