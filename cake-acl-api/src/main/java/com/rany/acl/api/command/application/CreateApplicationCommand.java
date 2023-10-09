package com.rany.acl.api.command.application;

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
public class CreateApplicationCommand extends DTO {

    /**
     * 账号名
     */
    private String appName;

    /**
     * 账号类型
     */
    private String appCode;

    /**
     * 手机号
     */
    private String authType;
}
