package com.rany.acl.domain.repository.impl;

import com.cake.framework.common.response.Page;
import com.github.pagehelper.PageInfo;
import com.rany.acl.common.dto.application.ApplicationDTO;
import com.rany.acl.common.params.ApplicationPageSearchParam;
import com.rany.acl.common.params.ApplicationSearchParam;
import com.rany.acl.domain.aggregate.Application;
import com.rany.acl.domain.convertor.ApplicationDataConvertor;
import com.rany.acl.domain.dao.ApplicationDao;
import com.rany.acl.domain.page.annotation.PagingQuery;
import com.rany.acl.domain.pk.ApplicationId;
import com.rany.acl.domain.repository.ApplicationRepository;
import com.rany.acl.infra.mapper.ApplicationPOMapper;
import com.rany.acl.infra.po.ApplicationPO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/11/26 00:18
 * @email 18668485565163.com
 */
@AllArgsConstructor
@Slf4j
@Service
public class ApplicationRepositoryImpl implements ApplicationRepository {

    private final ApplicationPOMapper applicationPOMapper;
    private final ApplicationDao applicationDao;
    private final ApplicationDataConvertor applicationDataConvertor;

    @Override
    public Application find(@NotNull ApplicationId applicationId) {
        ApplicationPO accountPO = applicationPOMapper.selectByPrimaryKey(applicationId.getId());
        return applicationDataConvertor.targetToSource(accountPO);
    }

    @Override
    public void remove(@NotNull Application application) {
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(@NotNull Application application) {
        Long accountId = application.getId().getId();
        applicationDao.save(application);
    }


    @Override
    public int update(Application account) {
        return applicationDao.update(account);
    }

    @Override
    public Application findByAppCode(String appCode) {
        ApplicationPO applicationPO = applicationDao.selectByAppCode(appCode);
        return applicationDataConvertor.targetToSource(applicationPO);
    }


    @Override
    public List<ApplicationDTO> findApplications(ApplicationSearchParam applicationSearchParam) {
        List<ApplicationPO> accountPOS = applicationDao.selectList(applicationSearchParam);
        return applicationDataConvertor.targetToDTO(accountPOS);
    }

    @Override
    @PagingQuery
    public Page<ApplicationDTO> pageApplications(ApplicationPageSearchParam applicationPageSearchParam) {
        List<ApplicationPO> content = applicationDao.selectPage(applicationPageSearchParam);
        PageInfo<ApplicationPO> pageInfo = new PageInfo<>(content);

        Page<ApplicationDTO> pageDTO = new Page<>();
        pageDTO.setPageNo(pageInfo.getPageNum());
        pageDTO.setPageSize(pageInfo.getPageSize());
        pageDTO.setTotalPage(pageInfo.getPages());
        pageDTO.setTotal(Long.valueOf(pageInfo.getTotal()).intValue());
        List<ApplicationDTO> values = applicationDataConvertor.targetToDTO(pageInfo.getList());
        pageDTO.setItems(values);
        return pageDTO;
    }
}
