package com.tinkoff.edu.app.logger;


import com.tinkoff.edu.app.model.AbstractLoanResponse;

/**
 * Describe logging of application work
 */
public class LoanCalcLogger {

    public static void log(AbstractLoanResponse loanResponse) {
        loanResponse.printLoanResponseInfo();
    }
}
