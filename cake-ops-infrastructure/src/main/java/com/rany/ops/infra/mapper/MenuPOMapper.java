package com.rany.ops.infra.mapper;

import com.rany.ops.infra.po.MenuPO;

public interface MenuPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbggenerated Sat Jun 08 15:31:03 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbggenerated Sat Jun 08 15:31:03 CST 2024
     */
    int insert(MenuPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbggenerated Sat Jun 08 15:31:03 CST 2024
     */
    int insertSelective(MenuPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbggenerated Sat Jun 08 15:31:03 CST 2024
     */
    MenuPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbggenerated Sat Jun 08 15:31:03 CST 2024
     */
    int updateByPrimaryKeySelective(MenuPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbggenerated Sat Jun 08 15:31:03 CST 2024
     */
    int updateByPrimaryKey(MenuPO record);
}