package com.rany.acl.api.facade;

import com.cake.framework.common.response.PojoResult;
import com.rany.acl.api.command.grant.DisGrantRoleMenusCommand;
import com.rany.acl.api.command.grant.GrantRoleMenusCommand;

/**
 * 角色菜单授权
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/27 20:39
 * @email 18668485565163.com
 */
public interface GrantRoleMenuFacade {


    /**
     * 角色菜单绑定
     *
     * @param grantRoleMenusCommand 授权请求
     * @return 是否成功
     */
    PojoResult<Boolean> grantRoleMenus(GrantRoleMenusCommand grantRoleMenusCommand);


    /**
     * 解除角色菜单绑定
     *
     * @param disGrantRoleMenusCommand 解除授权请求
     * @return 是否成功
     */
    PojoResult<Boolean> disGrantRoleMenus(DisGrantRoleMenusCommand disGrantRoleMenusCommand);
}
