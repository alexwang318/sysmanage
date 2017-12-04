package com.sp.sysmanage.dao;

import java.util.List;
import java.sql.Date;

import org.apache.ibatis.annotations.Param;

import com.sp.sysmanage.domain.AccessRecordDO;

public interface AccessRecordMapper {
	void insertAccessRecord(AccessRecordDO accessRecordDO);
	
	List<AccessRecordDO> selectAccessRecord(@Param("userID") Integer userID,
            @Param("accessType") String accessType,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
			);
}
