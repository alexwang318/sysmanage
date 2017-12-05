/**
 * 
 */
package com.sp.sysmanage.dao;

import com.sp.sysmanage.domain.UserInfoDO;

import java.util.List;

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
