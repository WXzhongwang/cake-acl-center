package com.rany.acl.service.remote.isv;

import com.alibaba.dubbo.config.annotation.Service;
import com.cake.framework.common.response.PojoResult;
import com.rany.acl.api.command.isv.CreateIsvCommand;
import com.rany.acl.api.command.isv.DeleteIsvCommand;
import com.rany.acl.api.command.isv.DisableIsvCommand;
import com.rany.acl.api.command.isv.EnableIsvCommand;
import com.rany.acl.api.facade.isv.IsvFacade;
import com.rany.acl.api.query.isv.IsvBasicQuery;
import com.rany.acl.common.dto.isv.IsvDTO;
import com.rany.acl.common.enums.CommonStatusEnum;
import com.rany.acl.common.enums.DeleteStatusEnum;
import com.rany.acl.common.exception.BusinessException;
import com.rany.acl.common.exception.enums.BusinessErrorMessage;
import com.rany.acl.common.util.SnowflakeIdWorker;
import com.rany.acl.domain.aggregate.Isv;
import com.rany.acl.domain.convertor.IsvDataConvertor;
import com.rany.acl.domain.dp.EmailAddress;
import com.rany.acl.domain.dp.IsvName;
import com.rany.acl.domain.dp.Phone;
import com.rany.acl.domain.pk.IsvId;
import com.rany.acl.domain.service.IsvDomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import static com.rany.acl.common.Constants.DEFAULT_MAX_TENANTS;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/11/15 22:29
 * @email 18668485565163.com
 */
@Slf4j
@Service
@AllArgsConstructor
public class IsvRemoteServiceProvider implements IsvFacade {

    private final IsvDomainService isvDomainService;
    private final IsvDataConvertor isvDataConvertor;
    private final SnowflakeIdWorker snowflakeIdWorker;

    @Override

    public PojoResult<Boolean> createIsv(CreateIsvCommand createIsvCommand) {
        Isv isv = new Isv(new IsvId(snowflakeIdWorker.nextId()),
                new IsvName(createIsvCommand.getName(), createIsvCommand.getShortName()),
                new EmailAddress(createIsvCommand.getEmail()),
                new Phone(createIsvCommand.getPhone())
        );
        isv.setCountry(createIsvCommand.getCountry());
        isv.setUrl(createIsvCommand.getUrl());
        isv.setRegisterIp(createIsvCommand.getRegisterIp());
        isv.setIsDeleted(DeleteStatusEnum.NO.getValue());
        isv.setStatus(CommonStatusEnum.ENABLE.getValue());
        isv.setMaxTenants(DEFAULT_MAX_TENANTS);
        isvDomainService.save(isv);
        return PojoResult.succeed();
    }

    @Override
    public PojoResult<Boolean> deleteIsv(DeleteIsvCommand deleteIsvCommand) {
        Isv isv = isvDomainService.findById(new IsvId(deleteIsvCommand.getId()));
        if (Objects.isNull(isv)) {
            throw new BusinessException(BusinessErrorMessage.ISV_NOT_FOUND);
        }
        if (StringUtils.equals(isv.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.ISV_DELETED);
        }
        if (StringUtils.equals(isv.getStatus(), CommonStatusEnum.DISABLED.getValue())) {
            throw new BusinessException(BusinessErrorMessage.ISV_DISABLED);
        }
        isv.delete();
        isvDomainService.update(isv);
        return PojoResult.succeed(true);
    }

    @Override
    public PojoResult<Boolean> disableIsv(DisableIsvCommand disableIsvCommand) {
        Isv isv = isvDomainService.findById(new IsvId(disableIsvCommand.getId()));
        if (Objects.isNull(isv)) {
            throw new BusinessException(BusinessErrorMessage.ISV_NOT_FOUND);
        }
        if (StringUtils.equals(isv.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.ISV_DELETED);
        }
        isv.disable();
        isvDomainService.update(isv);
        return PojoResult.succeed();
    }

    @Override
    public PojoResult<Boolean> enableIsv(EnableIsvCommand enableIsvCommand) {
        Isv isv = isvDomainService.findById(new IsvId(enableIsvCommand.getId()));
        if (Objects.isNull(isv)) {
            throw new BusinessException(BusinessErrorMessage.ISV_NOT_FOUND);
        }
        if (StringUtils.equals(isv.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.ISV_DELETED);
        }
        isv.enable();
        isvDomainService.update(isv);
        return PojoResult.succeed();
    }

    @Override
    public PojoResult<IsvDTO> findIsv(IsvBasicQuery isvBaseQuery) {
        Isv isv = isvDomainService.findById(new IsvId(isvBaseQuery.getIsvId()));
        if (Objects.isNull(isv)) {
            throw new BusinessException(BusinessErrorMessage.ISV_NOT_FOUND);
        }
        IsvDTO isvDTO = isvDataConvertor.sourceToDTO(isv);
        return PojoResult.succeed(isvDTO);
    }
}
