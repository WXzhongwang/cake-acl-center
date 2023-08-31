package com.rany.acl.domain.repository.impl;

import cn.hutool.core.date.DateUtil;
import com.cake.framework.common.response.Page;
import com.github.pagehelper.PageInfo;
import com.rany.acl.common.dto.account.AccountDTO;
import com.rany.acl.common.enums.DeleteStatusEnum;
import com.rany.acl.common.enums.LoginSafeStrategyEnum;
import com.rany.acl.common.params.AccountPageSearchParam;
import com.rany.acl.common.params.AccountSearchParam;
import com.rany.acl.domain.aggregate.Account;
import com.rany.acl.domain.convertor.AccountDataConvertor;
import com.rany.acl.domain.convertor.SafeStrategyConvertor;
import com.rany.acl.domain.dao.SafeStrategyDao;
import com.rany.acl.domain.entity.SafeStrategy;
import com.rany.acl.domain.page.annotation.PagingQuery;
import com.rany.acl.domain.repository.AccountRepository;
import com.rany.acl.infra.mapper.AccountPOMapper;
import com.rany.acl.infra.mapper.SafeStrategyPOMapper;
import com.rany.acl.infra.po.AccountPO;
import com.rany.acl.infra.po.SafeStrategyPO;
import com.rany.acl.domain.dao.AccountDao;
import com.rany.acl.domain.pk.AccountId;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

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
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountPOMapper accountMapper;
    private final SafeStrategyPOMapper safeStrategyPOMapper;
    private final AccountDao accountDao;
    private final SafeStrategyDao safeStrategyDao;
    private final AccountDataConvertor accountDataConvertor;
    private final SafeStrategyConvertor safeStrategyConvertor;

    @Override
    public Account find(@NotNull AccountId accountId) {
        AccountPO accountPO = accountMapper.selectByPrimaryKey(accountId.getId());
        Account account = accountDataConvertor.targetToSource(accountPO);
        List<SafeStrategyPO> safeStrategyPOS = safeStrategyDao.selectStrategiesByAccountId(accountId.getId());
        List<SafeStrategy> safeStrategies = safeStrategyConvertor.targetToSource(safeStrategyPOS);
        account.setSafeStrategies(safeStrategies);
        return account;
    }

    @Override
    public void remove(@NotNull Account account) {
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(@NotNull Account account) {
        Long accountId = account.getId().getId();
        accountDao.save(account);
        List<SafeStrategy> safeStrategies = account.getSafeStrategies();
        if (CollectionUtils.isNotEmpty(safeStrategies)) {
            for (SafeStrategy safeStrategy : safeStrategies) {
                safeStrategy.setAccountId(accountId);
                safeStrategy.setIsDeleted(DeleteStatusEnum.NO.getValue());
                safeStrategy.setGmtCreate(DateUtil.date());
                safeStrategy.setGmtModified(DateUtil.date());
                SafeStrategyPO strategy = safeStrategyConvertor.sourceToTarget(safeStrategy);
                safeStrategyPOMapper.insertSelective(strategy);
            }
        }
    }

    @Override
    public Account findAccountByLoginName(String loginName) {
        SafeStrategyPO safeStrategyPO = safeStrategyDao.findAccountByAuthCode(loginName, LoginSafeStrategyEnum.BASIC_AUTH.name());
        if (Objects.isNull(safeStrategyPO)) {
            return null;
        }
        Long accountId = safeStrategyPO.getAccountId();
        return find(new AccountId(accountId));
    }

    @Override
    public Account findAccountByDingUnionId(Long tenantId, String dingUnionId) {
        AccountPO accountPO = accountDao.getAccountByUnionId(dingUnionId, tenantId);
        return accountDataConvertor.targetToSource(accountPO);
    }

    @Override
    public int update(Account account) {
        return accountDao.update(account);
    }

    @Override
    public Boolean saveSafeStrategy(Account account) {
        Long accountId = account.getId().getId();
        List<SafeStrategy> safeStrategies = account.getSafeStrategies();
        if (CollectionUtils.isNotEmpty(safeStrategies)) {
            for (SafeStrategy safeStrategy : safeStrategies) {
                safeStrategy.setAccountId(accountId);
                safeStrategy.setIsDeleted(DeleteStatusEnum.NO.getValue());
                safeStrategy.setGmtCreate(DateUtil.date());
                safeStrategy.setGmtModified(DateUtil.date());
                SafeStrategyPO strategy = safeStrategyConvertor.sourceToTarget(safeStrategy);
                safeStrategyPOMapper.insertSelective(strategy);
            }
        }
        return Boolean.TRUE;
    }


    @Override
    public Boolean updateSafeStrategy(Account account) {
        List<SafeStrategy> safeStrategies = account.getSafeStrategies();
        if (CollectionUtils.isNotEmpty(safeStrategies)) {
            for (SafeStrategy safeStrategy : safeStrategies) {
                SafeStrategyPO strategy = safeStrategyConvertor.sourceToTarget(safeStrategy);
                safeStrategyPOMapper.updateByPrimaryKey(strategy);
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public List<AccountDTO> findAccounts(AccountSearchParam accountSearchParam) {
        List<AccountPO> accountPOS = accountDao.selectList(accountSearchParam);
        return accountDataConvertor.targetToDTO(accountPOS);
    }

    @Override
    @PagingQuery
    public Page<AccountDTO> pageAccounts(AccountPageSearchParam accountPageSearchParam) {
        List<AccountPO> content = accountDao.selectPage(accountPageSearchParam);
        PageInfo<AccountPO> pageInfo = new PageInfo<>(content);
        Page<AccountDTO> pageDTO = new Page<>();
        pageDTO.setPageNo(pageInfo.getPageNum());
        pageDTO.setPageSize(pageInfo.getPageSize());
        pageDTO.setTotalPage(pageInfo.getPages());
        pageDTO.setTotal(Long.valueOf(pageInfo.getTotal()).intValue());
        List<AccountDTO> values = accountDataConvertor.targetToDTO(pageInfo.getList());
        pageDTO.setItems(values);
        return pageDTO;
    }
}
