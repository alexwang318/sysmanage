package com.oracle.sp.security.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oracle.sp.dao.security.UserGroupMapper;
import com.oracle.sp.dao.security.UserInfoMapper;
import com.oracle.sp.dao.security.UserRoleMapper;
import com.oracle.sp.domain.GroupDO;
import com.oracle.sp.domain.RoleDO;
import com.oracle.sp.domain.UserInfoDO;
import com.oracle.sp.domain.UserInfoDTO;
import com.oracle.sp.exception.UserInfoServiceException;
import com.oracle.sp.security.service.Interface.UserInfoService;
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
	private UserRoleMapper userRoleMapper;
	
	@Autowired
	private UserGroupMapper userGroupMapper;
	
	
	@SuppressWarnings("unused")
	@Autowired
	private UserInfoService userInfoService;
	
	private DateFormat dateFormatDetail = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	
	private static Logger log = Logger.getLogger(UserInfoServiceImpl.class); 
	
    @Override
    public UserInfoDTO getUserInfo(String userName) throws UserInfoServiceException {
        if (userName == null) {
        	log.error("user name is empty");
            return null;
        }

        try {
            UserInfoDO userInfoDO = userInfoMapper.selectByName(userName);
            if (userInfoDO != null) {
            	List<RoleDO> roles = userRoleMapper.selectRoles4User(userInfoDO.getName());
            	List<GroupDO> groups = userGroupMapper.selectGroups4User(userInfoDO.getName());
            	log.error("Last Login time from DB: " + userInfoDO.getLastLoginDate());
                return convertUserInfoDO2UserInfoDTO(userInfoDO, roles, groups);

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
                List<GroupDO> groups;
                UserInfoDTO userInfoDTO;
                
                userInfoDTOS = new ArrayList<>(userInfoDOS.size());
                for (UserInfoDO userInfoDO : userInfoDOS) {
                    roles = userRoleMapper.selectRoles4User(userInfoDO.getName());
                    groups = userGroupMapper.selectGroups4User(userInfoDO.getName());
                    userInfoDTO = convertUserInfoDO2UserInfoDTO(userInfoDO, roles, groups);
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
    public boolean updateUserInfo(UserInfoDTO userInfoDTO) throws UserInfoServiceException {
    	if (userInfoDTO != null) {
            try {
            	UserInfoDO userInfoDO = convertUserInfoDTO2UserInfoDO(userInfoDTO);

                log.error("About to update user into DB now");                
                userInfoMapper.update(userInfoDO);
                
                return true;

            } catch (PersistenceException e) {
            	log.error("Throw exception now");
                throw new UserInfoServiceException(e);
            }
        } else {
        	log.error("UserInfoDTO is null");
        }
        return false;
    }

    @Override
    public boolean deleteUserInfo(String userName) throws UserInfoServiceException {
        if (userName == null)
            return false;

        try {
            userRoleMapper.deleteByUserName(userName);
            userInfoMapper.deleteByName(userName);
            userGroupMapper.deleteByUserName(userName);
            
            return true;
        } catch (PersistenceException e) {
            throw new UserInfoServiceException(e); 
        }
    }

    @Override
    public boolean insertUserInfo(UserInfoDTO userInfoDTO) throws UserInfoServiceException {
        if (userInfoDTO == null)
            return false;

        String userName = userInfoDTO.getName();
        String password = userInfoDTO.getPwd();
        String email = userInfoDTO.getEmail();
        
        log.error("insert user: " + userName);
        
        if (userName == null || password == null)
            return false;

        try {
            //String tempStr = MD5Util.MD5(password);
            //String encryptPassword = MD5Util.MD5(tempStr + userID.toString());

            UserInfoDO userInfoDO = new UserInfoDO();
            userInfoDO.setName(userName);
            userInfoDO.setPwd(password);
            userInfoDO.setEmail(email);
            userInfoDO.setFirstLogin(1);
            userInfoDO.setState(0);
            
            //FIXME
            userInfoDO.setLastLoginDate(new Date());
            
            log.error("call DAO to insert user now");

            userInfoMapper.insert(userInfoDO);

            List<String> roles = userInfoDTO.getRole();
            List<String> groups = userInfoDTO.getGroup();

            for (String roleName : roles) {
                if (roleName != null) {
                    userRoleMapper.insert(userName, roleName);
                    
                } else
                    throw new UserInfoServiceException("The role of userInfo unavailable");
            }
            
            for (String groupName : groups) {
            	if (groupName != null) {
            		userGroupMapper.insert(userName, groupName);
            	} else 
            		throw new UserInfoServiceException("The group of userInfo unavailable");
            }

            return true;

        } catch (PersistenceException e) {
            throw new UserInfoServiceException(e);
        }
    }

    private UserInfoDTO convertUserInfoDO2UserInfoDTO(UserInfoDO userInfoDO, List<RoleDO> roles, List<GroupDO> groups) {
        UserInfoDTO userInfoDTO = null;
        userInfoDTO = new UserInfoDTO();
        
        if (userInfoDO != null) {
            userInfoDTO.setId(userInfoDO.getId());
            userInfoDTO.setName(userInfoDO.getName());
            userInfoDTO.setPwd(userInfoDO.getPwd());
            userInfoDTO.setEmail(userInfoDO.getEmail());
            userInfoDTO.setState(userInfoDO.getState());
            userInfoDTO.setFirstLogin(userInfoDO.getFirstLogin() == 1);
            userInfoDTO.setLastLoginDate(dateFormatDetail.format(userInfoDO.getLastLoginDate()));
        
            if (roles != null) {
	        	for (RoleDO role : roles) {
	                userInfoDTO.getRole().add(role.getName());
	            }
            }
        	
            if (groups != null) {
	        	for (GroupDO group : groups) {
	        		userInfoDTO.getGroup().add(group.getName());
	        	}
            }
        }

        return userInfoDTO;
    }
    
    private UserInfoDO convertUserInfoDTO2UserInfoDO(UserInfoDTO userInfoDTO) {
        UserInfoDO userInfoDO = new UserInfoDO();
        
        if (userInfoDTO != null) {
        	userInfoDO.setId(userInfoDTO.getId());
        	userInfoDO.setName(userInfoDTO.getName());
        	userInfoDO.setPwd(userInfoDTO.getPwd());
        	userInfoDO.setEmail(userInfoDTO.getEmail());
        	userInfoDO.setState(userInfoDTO.getState());
        	userInfoDO.setFirstLogin(userInfoDTO.isFirstLogin()?1:0);
        	
        	try {
        		userInfoDO.setLastLoginDate(dateFormatDetail.parse(userInfoDTO.getLastLoginDate()));
        	} catch (ParseException e) {
        		log.error("Date parse error, use a new date to replace it");
        		userInfoDO.setLastLoginDate(new Date());
        	}
        }

        return userInfoDO;
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
    public Map<String, Object> getUsersByRole(String roleName, 
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
        
        log.error("get user by role: " + roleName + " offset:" + offset + ",limit:" + limit);
        try {
	        List<UserInfoDO> userInfoDOS = userInfoMapper.selectByRole(roleName);
	        if (userInfoDOS != null) {
	            List<RoleDO> roles;
	            List<GroupDO> groups;
	            UserInfoDTO userInfoDTO;
	            
	            userInfoDTOS = new ArrayList<>(userInfoDOS.size());
	            for (UserInfoDO userInfoDO : userInfoDOS) {
	                roles = userRoleMapper.selectRoles4User(userInfoDO.getName());
	                groups = userGroupMapper.selectGroups4User(userInfoDO.getName());
	                userInfoDTO = convertUserInfoDO2UserInfoDTO(userInfoDO, roles, groups);
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
