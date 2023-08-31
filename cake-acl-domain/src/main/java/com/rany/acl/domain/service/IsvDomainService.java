package com.rany.acl.domain.service;

import com.rany.acl.domain.aggregate.Isv;
import com.rany.acl.domain.repository.IsvRepository;
import com.rany.acl.domain.pk.IsvId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/1 22:36
 * @email 18668485565163.com
 */
@Slf4j
@Component
public class IsvDomainService {

    @Resource
    private IsvRepository isvRepository;


    public Boolean save(Isv isv) {
        isvRepository.save(isv);
        return Boolean.TRUE;
    }

    public Boolean update(Isv isv) {
        return isvRepository.updateIsv(isv);
    }

    public Isv findById(IsvId isvId) {
        return isvRepository.find(isvId);
    }


}