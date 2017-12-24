/**
 * 
 */
package com.oracle.sp.dao.security;

import java.util.List;

import com.oracle.sp.domain.UserInfoDO;

/**
 * @author binwan
 *
 */
public interface UserInfoMapper {
	
	UserInfoDO selectByName(String userName);
	List<UserInfoDO> selectByRole(String roleName);
	List<UserInfoDO> selectAll();
	void update(UserInfoDO user);
	void deleteByName(String userName);
	void insert(UserInfoDO user);

}
