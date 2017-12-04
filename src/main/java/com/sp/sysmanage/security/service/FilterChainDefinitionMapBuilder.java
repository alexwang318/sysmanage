package com.sp.sysmanage.security.service;

import com.sp.sysmanage.dao.RoleAuthMapper;
import com.sp.sysmanage.domain.RoleAuthDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;

public class FilterChainDefinitionMapBuilder {
	
    @Autowired
    private RoleAuthMapper RoleAuthMapper;
    private StringBuilder builder = new StringBuilder();

    /**
     * ��ȡ��Ȩ��Ϣ
     * @return ������Ȩ��Ϣ�б�
     */
    public LinkedHashMap<String, String> builderFilterChainDefinitionMap(){
        LinkedHashMap<String, String> permissionMap = new LinkedHashMap<>();

        permissionMap.put("/css/**", "anon");
        permissionMap.put("/js/**", "anon");
        permissionMap.put("/fonts/**", "anon");
        permissionMap.put("/media/**", "anon");
        permissionMap.put("/pagecomponent/**", "anon");
        permissionMap.put("/login", "anon, kickOut");
        permissionMap.put("/account/login", "anon");
        permissionMap.put("/account/checkCode/**", "anon");

        LinkedHashMap<String, String> permissions = getPermissionDataFromDB();
        if (permissions != null){
            permissionMap.putAll(permissions);
        }

        permissionMap.put("/", "authc");

        return permissionMap;
    }

    private LinkedHashMap<String, String> getPermissionDataFromDB(){
        LinkedHashMap<String, String> permissionData = null;

        List<RoleAuthDO> roleAuthDOS = RoleAuthMapper.selectAll();
        if (roleAuthDOS != null){
            permissionData = new LinkedHashMap<>(roleAuthDOS.size());
            String url;
            String role;
            String permission;
            for (RoleAuthDO RoleAuthDO : roleAuthDOS){
                url = RoleAuthDO.getUrl();
                role = RoleAuthDO.getRole();

                // �жϸ� url �Ƿ��Ѿ�����
                if (permissionData.containsKey(url)){
                    builder.delete(0, builder.length());
                    builder.append(permissionData.get(url));
                    builder.insert(builder.length() - 1, ",");
                    builder.insert(builder.length() - 1, role);
                }else{
                    builder.delete(0, builder.length());
                    builder.append("authc,kickOut,roles[").append(role).append("]");
                }
                permission = builder.toString();
                permissionData.put(url, permission);
            }
        }

        return permissionData;
    }
	

}
