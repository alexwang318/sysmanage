package com.sp.sysmanage.security.service.Impl;

import com.sp.sysmanage.dao.RolesMapper;
import com.sp.sysmanage.dao.UserInfoMapper;
import com.sp.sysmanage.dao.UserRoleMapper;
import com.sp.sysmanage.domain.RoleDO;
import com.sp.sysmanage.domain.UserInfoDO;
import com.sp.sysmanage.domain.UserInfoDTO;
import com.sp.sysmanage.exception.UserInfoServiceException;
import com.sp.sysmanage.security.controller.AccountHandler;
import com.sp.sysmanage.security.service.Interface.UserInfoService;
import com.sp.sysmanage.security.utils.MD5Util;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private UserRoleMapper UserRoleMapper;
	
	@Autowired
	private RolesMapper rolesMapper;
	
	@SuppressWarnings("unused")
	@Autowired
	private UserInfoService userInfoService;
	
	private static Logger log = Logger.getLogger(UserInfoServiceImpl.class); 
	
	@Override
	public UserInfoDTO getUserInfo(Integer userID) throws UserInfoServiceException {
		if (userID == null)
			return null;
		
		try {
			UserInfoDO userInfoDO = userInfoMapper.selectByUserID(userID);
			List<RoleDO> roles = UserRoleMapper.selectUserRole(userID);
			
			return assembleUserInfo(userInfoDO, roles);
		} catch (PersistenceException e) {
			throw new UserInfoServiceException(e);
		}
	}
	
    @Override
    public UserInfoDTO getUserInfo(String userName) throws UserInfoServiceException {
        if (userName == null) {
        	log.error("user name is empty");
            return null;
        }

        try {
            UserInfoDO userInfoDO = userInfoMapper.selectByName(userName);
            if (userInfoDO != null) {
            	log.error("user Info: " + userInfoDO.getUserName() + "," + userInfoDO.getPassword());
                List<RoleDO> roles = UserRoleMapper.selectUserRole(userInfoDO.getUserName());
                log.error("userID: " + userInfoDO.getUserID() + "role" + roles.toString());
                return assembleUserInfo(userInfoDO, roles);
            } else
                return null;
        } catch (PersistenceException e) {
            throw new UserInfoServiceException(e);
        }
    }
    
    @Override
    public List<UserInfoDTO> getAllUserInfo() throws UserInfoServiceException {

        List<UserInfoDTO> userInfoDTOS = null;

        try {
            List<UserInfoDO> userInfoDOS = userInfoMapper.selectAll();
            if (userInfoDOS != null) {
                List<RoleDO> roles;
                UserInfoDTO userInfoDTO;
                userInfoDTOS = new ArrayList<>(userInfoDOS.size());
                for (UserInfoDO userInfoDO : userInfoDOS) {
                    roles = UserRoleMapper.selectUserRole(userInfoDO.getUserID());
                    userInfoDTO = assembleUserInfo(userInfoDO, roles);
                    userInfoDTOS.add(userInfoDTO);
                }
            }

            return userInfoDTOS;
        } catch (PersistenceException e) {
            throw new UserInfoServiceException(e);
        }
    }

    @Override
    public void updateUserInfo(UserInfoDTO userInfoDTO) throws UserInfoServiceException {
        if (userInfoDTO != null) {
            try {
                Integer userID = userInfoDTO.getUserID();
                String userName = userInfoDTO.getUserName();
                String password = userInfoDTO.getPassword();
                if (userID != null && userName != null && password != null) {
                    UserInfoDO userInfoDO = new UserInfoDO();
                    userInfoDO.setUserID(userID);
                    userInfoDO.setUserName(userName);
                    userInfoDO.setPassword(password);
                    userInfoDO.setFirstLogin(userInfoDTO.isFirstLogin() ? 1 : 0);

                    userInfoMapper.update(userInfoDO);
                }

            } catch (PersistenceException e) {
                throw new UserInfoServiceException(e);
            }
        }

    }

    @Override
    public void deleteUserInfo(Integer userID) throws UserInfoServiceException {
        if (userID == null)
            return;

        try {
            UserRoleMapper.deleteByUserID(userID);

            userInfoMapper.deleteById(userID);
        } catch (PersistenceException e) {
            throw new UserInfoServiceException(e);
            
        }

    }

    @Override
    public boolean insertUserInfo(UserInfoDTO userInfoDTO) throws UserInfoServiceException {
        if (userInfoDTO == null)
            return false;

        Integer userID = userInfoDTO.getUserID();
        String userName = userInfoDTO.getUserName();
        String password = userInfoDTO.getPassword();
        if (userName == null || password == null)
            return false;

        try {
            String tempStr = MD5Util.MD5(password);
            String encryptPassword = MD5Util.MD5(tempStr + userID.toString());

            UserInfoDO userInfoDO = new UserInfoDO();
            userInfoDO.setUserID(userID);
            userInfoDO.setUserName(userName);
            userInfoDO.setPassword(encryptPassword);
            userInfoDO.setFirstLogin(1);
            userInfoDO.setStatus(1);
            
            //FIXME
            userInfoDO.setLastLoginDate(null);

            userInfoMapper.insert(userInfoDO);

            List<String> roles = userInfoDTO.getRole();
            Integer roleID;

            for (String role : roles) {
                roleID = rolesMapper.getRoleID(role);
                if (roleID != null)
                    UserRoleMapper.insert(userID, roleID);
                else
                    throw new UserInfoServiceException("The role of userInfo unavailable");
            }

            return true;

        } catch (PersistenceException e) {
            throw new UserInfoServiceException(e);
        }
    }

    private UserInfoDTO assembleUserInfo(UserInfoDO userInfoDO, List<RoleDO> roles) {
        UserInfoDTO userInfoDTO = null;
        userInfoDTO = new UserInfoDTO();
        
        if (userInfoDO != null) {
            userInfoDTO.setUserID(userInfoDO.getUserID());
            userInfoDTO.setUserName(userInfoDO.getUserName());
            userInfoDTO.setPassword(userInfoDO.getPassword());
            userInfoDTO.setFirstLogin(userInfoDO.getFirstLogin() == 1);
        }
        
        if (!roles.isEmpty()) {
        	for (RoleDO role : roles) {
                userInfoDTO.getRole().add(role.getRoleName());
            }
        } else {
        	log.error("roles is empty");
        }
        return userInfoDTO;
    }

    @Override
    public Set<String> getUserRoles(Integer userID) throws UserInfoServiceException {

        UserInfoDTO userInfo = getUserInfo(userID);


        if (userInfo != null) {
            return new HashSet<>(userInfo.getRole());
        } else {
            return new HashSet<>();
        }
    }    
}
