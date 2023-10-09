package com.rany.acl.domain.event.permission;

import com.cake.framework.common.event.DomainEvent;
import com.rany.acl.domain.aggregate.Permission;
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
public class PermissionDeletedEvent extends DomainEvent {

    private Permission permission;
    private Date eventTime;

    public PermissionDeletedEvent(Permission permission, Date eventTime) {
        this.permission = permission;
        this.eventTime = eventTime;
    }
}