package com.rany.acl.infra.mapper;

import com.rany.acl.infra.po.TenantPO;

public interface TenantPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenant
     *
     * @mbggenerated Thu Aug 17 23:37:07 CST 2023
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenant
     *
     * @mbggenerated Thu Aug 17 23:37:07 CST 2023
     */
    int insert(TenantPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenant
     *
     * @mbggenerated Thu Aug 17 23:37:07 CST 2023
     */
    int insertSelective(TenantPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenant
     *
     * @mbggenerated Thu Aug 17 23:37:07 CST 2023
     */
    TenantPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenant
     *
     * @mbggenerated Thu Aug 17 23:37:07 CST 2023
     */
    int updateByPrimaryKeySelective(TenantPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenant
     *
     * @mbggenerated Thu Aug 17 23:37:07 CST 2023
     */
    int updateByPrimaryKey(TenantPO record);
}