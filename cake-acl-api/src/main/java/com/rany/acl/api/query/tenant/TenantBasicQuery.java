package com.rany.acl.api.query.tenant;

import com.rany.acl.common.base.BaseQuery;
import lombok.Data;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/31 16:47
 * @email 18668485565163.com
 */
@Data
public class TenantBasicQuery extends BaseQuery {

    private Long tenantId;
}
