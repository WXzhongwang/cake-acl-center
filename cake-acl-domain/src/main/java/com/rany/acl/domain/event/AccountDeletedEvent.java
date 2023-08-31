package com.rany.acl.domain.event;

import com.cake.framework.common.event.DomainEvent;
import com.rany.acl.domain.aggregate.Account;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/1 22:26
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AccountDeletedEvent extends DomainEvent {

    private Account account;
    private Date eventTime;

    public AccountDeletedEvent(Account account, Date eventTime) {
        this.account = account;
        this.eventTime = eventTime;
    }
}