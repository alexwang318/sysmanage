package com.oracle.sp.dao.syslogs;

import java.util.List;
import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.oracle.sp.domain.AccessRecordDO;

public interface AccessRecordMapper {
	void insertAccessRecord(AccessRecordDO accessRecordDO);
	
	List<AccessRecordDO> selectAccessRecord(@Param("userName") String userName,
            @Param("accessType") String accessType,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
			);
}
