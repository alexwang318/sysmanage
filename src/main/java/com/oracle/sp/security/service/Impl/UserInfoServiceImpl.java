package com.oracle.sp.security.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oracle.sp.dao.security.UserInfoMapper;
import com.oracle.sp.dao.security.UserRoleMapper;
import com.oracle.sp.domain.RoleDO;
import com.oracle.sp.domain.UserInfoDO;
import com.oracle.sp.domain.UserInfoDTO;
import com.oracle.sp.exception.UserInfoServiceException;
import com.oracle.sp.security.service.Interface.UserInfoService;
import com.oracle.sp.security.utils.MD5Util;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private UserRoleMapper UserRoleMapper;
	
	
	@SuppressWarnings("unused")
	@Autowired
	private UserInfoService userInfoService;
	
	private static Logger log = Logger.getLogger(UserInfoServiceImpl.class); 
	
    @Override
    public UserInfoDTO getUserInfo(String userName) throws UserInfoServiceException {
        if (userName == null) {
        	log.error("user name is empty");
            return null;
        }

        try {
            UserInfoDO userInfoDO = userInfoMapper.selectByName(userName);
            log.error("Get user Info DTO from DB");
            if (userInfoDO != null) {
            	List<RoleDO> roles = UserRoleMapper.selectRoles4User(userInfoDO.getUserName());
            	//List<RoleDO> roles = new ArrayList<>();
            	//RoleDO role = new RoleDO();
            	//role.setRoleName("admin");
            	//role.setRoleID(1);
            	//role.setRoleDesc("system administrator");
            	//roles.add(role);
            	log.error("Add user's roles");
                return assembleUserInfo(userInfoDO, roles);

            } else {
            	log.error("didn't get user info from DB");
                return null;
            }
        } catch (PersistenceException e) {
            throw new UserInfoServiceException(e);
        }
    }
    
    @Override
    public Map<String, Object> getAllUserInfo() throws UserInfoServiceException {

        return getAllUserInfo(-1, -1);
    }
    
    @Override
    public Map<String, Object> getAllUserInfo(int offset, int limit)
    		throws UserInfoServiceException {
    	Map<String, Object> resultSet = new HashMap<>();
        List<UserInfoDTO> userInfoDTOS = null;
        long total = 0;
        boolean isPagination = true;

        if (offset < 0 || limit < 0)
            isPagination = false;

        try {
        	
            if (isPagination) {
        		PageHelper.offsetPage(offset, limit); 
            }
            
        	List<UserInfoDO> userInfoDOS = userInfoMapper.selectAll();            	
            if (userInfoDOS != null) {
                List<RoleDO> roles;
                UserInfoDTO userInfoDTO;
                userInfoDTOS = new ArrayList<>(userInfoDOS.size());
                for (UserInfoDO userInfoDO : userInfoDOS) {
                    roles = UserRoleMapper.selectRoles4User(userInfoDO.getUserName());
                    userInfoDTO = assembleUserInfo(userInfoDO, roles);
                    userInfoDTOS.add(userInfoDTO);
                }
            } else {
            	userInfoDTOS = new ArrayList<>();
            }
            
        	if (isPagination) {
                PageInfo<UserInfoDTO> pageInfo = new PageInfo<>(userInfoDTOS);
                total = pageInfo.getTotal();
	        } else {
	        	total = userInfoDTOS.size();
	        }

        } catch (PersistenceException e) {
            throw new UserInfoServiceException(e);
        }
        
        resultSet.put("data", userInfoDTOS);
        resultSet.put("total", total);
        return resultSet;
    }

    @Override
    public boolean updateUserInfo(UserInfoDTO userInfoDTO) throws UserInfoServiceException, ParseException {
        if (userInfoDTO != null) {
            try {
                Integer userID = userInfoDTO.getUserID();
                String userName = userInfoDTO.getUserName();
                String password = userInfoDTO.getPassword();
                Integer status = userInfoDTO.getStatus();
                Date lastLoginDate = new Date();
                
                try {
	                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");                
	                lastLoginDate = format.parse(userInfoDTO.getLastLoginDate());
                } catch (ParseException e) {
                	//FIXME: add some error process logic here?
                }
                
                if (userID != null && userName != null && password != null) {
                    UserInfoDO userInfoDO = new UserInfoDO();

                    userInfoDO.setUserID(userID);
                    userInfoDO.setUserName(userName);
                    userInfoDO.setPassword(password);
                    userInfoDO.setFirstLogin(userInfoDTO.isFirstLogin() ? 1 : 0);
                    userInfoDO.setStatus(status);
                    userInfoDO.setLastLoginDate(lastLoginDate);

                    userInfoMapper.update(userInfoDO);
                    return true;
                }

            } catch (PersistenceException e) {
                throw new UserInfoServiceException(e);
            }
        }
        return false;
    }

    @Override
    public boolean deleteUserInfo(String userName) throws UserInfoServiceException {
        if (userName == null)
            return false;

        try {
            UserRoleMapper.deleteByUserName(userName);
            userInfoMapper.deleteByName(userName);
            
            return true;
        } catch (PersistenceException e) {
            throw new UserInfoServiceException(e); 
        }
    }

    @Override
    public boolean insertUserInfo(UserInfoDTO userInfoDTO) throws UserInfoServiceException {
        if (userInfoDTO == null)
            return false;

        String userName = userInfoDTO.getUserName();
        String password = userInfoDTO.getPassword();
        String email = userInfoDTO.getEmail();
        if (userName == null || password == null)
            return false;

        try {
            //String tempStr = MD5Util.MD5(password);
            //String encryptPassword = MD5Util.MD5(tempStr + userID.toString());

            UserInfoDO userInfoDO = new UserInfoDO();
            userInfoDO.setUserName(userName);
            userInfoDO.setPassword(password);
            userInfoDO.setEmail(email);
            userInfoDO.setFirstLogin(1);
            userInfoDO.setStatus(0);
            
            //FIXME
            userInfoDO.setLastLoginDate(new Date());

            userInfoMapper.insert(userInfoDO);

            List<String> roles = userInfoDTO.getRole();

            for (String roleName : roles) {
                if (roleName != null)
                    UserRoleMapper.insert(userName, roleName);
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
        
        if (userInfoDO != null && roles != null) {
            userInfoDTO.setUserID(userInfoDO.getUserID());
            userInfoDTO.setUserName(userInfoDO.getUserName());
            userInfoDTO.setPassword(userInfoDO.getPassword());
            userInfoDTO.setEmail(userInfoDO.getEmail());
            userInfoDTO.setStatus(userInfoDO.getStatus());
            userInfoDTO.setFirstLogin(userInfoDO.getFirstLogin() == 1);
        
        	for (RoleDO role : roles) {
                userInfoDTO.getRole().add(role.getRoleName());
            }
        }

        return userInfoDTO;
    }

    @Override
    public Set<String> getUserRoles(String userName) throws UserInfoServiceException {

        UserInfoDTO userInfo = getUserInfo(userName);

        if (userInfo != null) {
            return new HashSet<>(userInfo.getRole());
        } else {
            return new HashSet<>();
        }
    }
    
    @Override
    public Map<String, Object> getUsersByRole(String selectRole, 
    		int offset, 
    		int limit) throws UserInfoServiceException {
    	Map<String, Object> resultSet = new HashMap<>();
    	List<UserInfoDTO> userInfoDTOS = null;
        long total = 0;
        boolean isPagination = true;

        if (offset < 0 || limit < 0)
            isPagination = false;
        
        if (isPagination) {
    		PageHelper.offsetPage(offset, limit); 
        }
        
        log.error("get user by role: " + selectRole + " offset:" + offset + ",limit:" + limit);
        try {
	        List<UserInfoDO> userInfoDOS = userInfoMapper.selectByRole(selectRole);
	        if (userInfoDOS != null) {
	            List<RoleDO> roles;
	            UserInfoDTO userInfoDTO;
	            userInfoDTOS = new ArrayList<>(userInfoDOS.size());
	            for (UserInfoDO userInfoDO : userInfoDOS) {
	                roles = UserRoleMapper.selectRoles4User(userInfoDO.getUserName());
	                userInfoDTO = assembleUserInfo(userInfoDO, roles);
	                userInfoDTOS.add(userInfoDTO);
	                
	            }
	            log.error("Get user: " + userInfoDTOS.size());
	        
		    	if (isPagination) {
		            PageInfo<UserInfoDTO> pageInfo = new PageInfo<>(userInfoDTOS);
		            total = pageInfo.getTotal();
		        } else {
		        	total = userInfoDTOS.size();
		        }
	        } else {
	        	log.error("Don't get any users that you want to get");
	            userInfoDTOS = new ArrayList<>();
	            total = 0;
	        }
        } catch (PersistenceException e) {
            throw new UserInfoServiceException(e);
        }
        
        resultSet.put("data", userInfoDTOS);
        resultSet.put("total", total);
        return resultSet;
    }
}
