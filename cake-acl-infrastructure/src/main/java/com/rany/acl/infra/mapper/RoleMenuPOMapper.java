package com.rany.acl.infra.mapper;

import com.rany.acl.infra.po.RoleMenuPO;

public interface RoleMenuPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu
     *
     * @mbggenerated Sat Oct 07 21:45:14 CST 2023
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu
     *
     * @mbggenerated Sat Oct 07 21:45:14 CST 2023
     */
    int insert(RoleMenuPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu
     *
     * @mbggenerated Sat Oct 07 21:45:14 CST 2023
     */
    int insertSelective(RoleMenuPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu
     *
     * @mbggenerated Sat Oct 07 21:45:14 CST 2023
     */
    RoleMenuPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu
     *
     * @mbggenerated Sat Oct 07 21:45:14 CST 2023
     */
    int updateByPrimaryKeySelective(RoleMenuPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu
     *
     * @mbggenerated Sat Oct 07 21:45:14 CST 2023
     */
    int updateByPrimaryKey(RoleMenuPO record);
}