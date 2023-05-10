package com.example.jehlum.apache;

import org.apache.commons.lang3.builder.*;

import java.time.LocalDate;

/**
 * @author irfan.nagoo
 */

public class Account implements Diffable<Account>, Comparable<Account> {

    public enum AccountType {
        SAVINGS, CURRENT
    }

    private final Long id;
    private final AccountType type;
    private final String accountNumber;
    private final LocalDate createDate;

    public Account(Long id, AccountType type, String accountNumber, LocalDate createDate) {
        this.id = id;
        this.type = type;
        this.accountNumber = accountNumber;
        this.createDate = createDate;
    }

    @Override
    public DiffResult<Account> diff(Account account) {
        return new DiffBuilder<>(this, account, ToStringStyle.JSON_STYLE)
                .append("id", this.id, account.id)
                .append("type", this.type, account.type)
                .append("accountNumber", this.accountNumber, account.accountNumber)
                .append("createDate", this.createDate, account.createDate)
                .build();
    }

    @Override
    public int compareTo(Account account) {
        return new CompareToBuilder()
                .append(this.type, account.type)
                .append(this.accountNumber, account.accountNumber)
                .toComparison();
    }
}
