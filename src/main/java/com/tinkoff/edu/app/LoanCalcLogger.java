package com.tinkoff.edu.app;


/**
 * Describe logging of application work
 */
public class LoanCalcLogger {

    public static void log(LoanResponse loanResponse) {
        loanResponse.printLoanResponseInfo();
    }
}
