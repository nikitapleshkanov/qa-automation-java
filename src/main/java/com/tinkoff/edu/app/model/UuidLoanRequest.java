package com.tinkoff.edu.app.model;

import com.tinkoff.edu.app.enums.LoanRequestType;
import com.tinkoff.edu.app.exceptions.RequestException;

public class UuidLoanRequest {

    private final int months;
    private final double amount;
    private final LoanRequestType loanType;
    private final String name;

    public UuidLoanRequest(int months, double amount, LoanRequestType loanType, String name) {
        if (months > 0) {
            try {
                checkMonthIsCorrect(months);
            } catch (IllegalArgumentException e) {
                throw new RequestException(e.getMessage(), e.getCause());
            }
            this.months = months;
        } else throw new IllegalArgumentException("Указано значение month <= 0");
        if (amount > 0) {
            try {
                checkAmountIsCorrect(amount);
            } catch (IllegalArgumentException e) {
                throw new RequestException(e.getMessage(), e.getCause());
            }
            this.amount = amount;
        } else throw new IllegalArgumentException("Указано значение amount <= 0");
        if ((name == null) || (name.length() == 0)) {
            throw new IllegalArgumentException("Значение ФИО указано пустое или null");
        } else {
            try {
                checkNameIsCorrect(name);
            } catch (IllegalArgumentException e) {
                throw new RequestException(e.getMessage(), e.getCause());
            }
            this.name = name;
        }
        if (loanType == null) {
            throw new IllegalArgumentException("Значение типа заявки указано null");
        } else this.loanType = loanType;
    }

    public int getMonths() {
        return months;
    }

    public double getAmount() {
        return amount;
    }

    public LoanRequestType getLoanType() {
        return loanType;
    }

    public String getName() {
        return name;
    }

    private boolean checkNameIsCorrect(String name) {
        if ((name.length() > 10) && (name.length() < 100)) {
            return true;
        } else throw new IllegalArgumentException("Некорректная длина ФИО в заявке");
    }

    private boolean checkAmountIsCorrect(double amount) {
        if ((amount > 0.01) && (amount < 999999.99)) {
            return true;
        } else throw new IllegalArgumentException("Некорректная сумма в заявке");
    }

    private boolean checkMonthIsCorrect(int month) {
        if (month < 100) {
            return true;
        } else throw new IllegalArgumentException("Некорректнаое число месяцев в заявке");
    }

}
