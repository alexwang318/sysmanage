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
	
	UserInfoDO selectByUserID(Integer userID);
	UserInfoDO selectByName(String userName);
	List<UserInfoDO> selectAll();
	void update(UserInfoDO user);
	void deleteById(Integer id);
	void deleteByName(String userName);
	void insert(UserInfoDO user);

}
