package com.rany.acl.api.command.role;

import com.rany.acl.common.base.DTO;
import lombok.Data;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/27 20:40
 * @email 18668485565163.com
 */
@Data
public class DeleteRoleCommand extends DTO {

    /**
     * roleId
     */
    private Long roleId;
}
