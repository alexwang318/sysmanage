/**
 * 
 */
package com.oracle.sp.dao.security;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oracle.sp.domain.UserInfoDO;

/**
 * @author binwan
 *
 */
public interface UserInfoMapper {
	
	UserInfoDO selectByName(@Param("userName") String userName);
	List<UserInfoDO> selectByRole(@Param("roleName")String roleName);
	List<UserInfoDO> selectAll();
	void update(@Param("userInfoDO") UserInfoDO userInfoDO);
	void deleteByName(@Param("userName")String userName);
	void insert(@Param("userInfoDO") UserInfoDO userInfoDO);

}
