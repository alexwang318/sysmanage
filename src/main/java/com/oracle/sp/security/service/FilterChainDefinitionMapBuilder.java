package com.oracle.sp.security.service;

import com.oracle.sp.dao.security.RoleAuthMapper;
import com.oracle.sp.domain.RoleAuthDO;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;

public class FilterChainDefinitionMapBuilder {
	
    @Autowired
    private RoleAuthMapper RoleAuthMapper;
    private StringBuilder builder = new StringBuilder();

    /**
     * Get the auth info.
     * @return return a map of auth info.
     */
    public LinkedHashMap<String, String> builderFilterChainDefinitionMap(){
        LinkedHashMap<String, String> authMap = new LinkedHashMap<>();

        // These auth is the basic auth for web page resources loading.
        authMap.put("/css/**", "anon");
        authMap.put("/js/**", "anon");
        authMap.put("/image/**", "anon");		
        authMap.put("/errorPage/**", "anon");
        authMap.put("/subPages/**", "anon");
        authMap.put("/login", "anon, kickOut");
        authMap.put("/account/login", "anon");
        authMap.put("/account/checkCode/**", "anon");

        LinkedHashMap<String, String> auth = getAuthDataFromDB();
        if (auth != null){
            authMap.putAll(auth);
        }

        authMap.put("/", "authc");

        return authMap;
    }

    private LinkedHashMap<String, String> getAuthDataFromDB(){
        LinkedHashMap<String, String> authData = null;

        List<RoleAuthDO> roleAuthDOS = RoleAuthMapper.selectAll();
        if (roleAuthDOS != null){
            authData = new LinkedHashMap<>(roleAuthDOS.size());
            String url;
            String role;
            String auth;
            for (RoleAuthDO RoleAuthDO : roleAuthDOS){
                url = RoleAuthDO.getUrl();
                role = RoleAuthDO.getRole();

                if (authData.containsKey(url)){
                    builder.delete(0, builder.length());
                    builder.append(authData.get(url));
                    builder.insert(builder.length() - 1, ",");
                    builder.insert(builder.length() - 1, role);
                }else{
                    builder.delete(0, builder.length());
                    builder.append("authc,kickOut,roles[").append(role).append("]");
                }
                auth = builder.toString();
                authData.put(url, auth);
            }
        }

        return authData;
    }
	

}
