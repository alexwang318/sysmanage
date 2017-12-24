package com.oracle.sp.dao.syslogs;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oracle.sp.domain.UserOperationRecordDO;

public interface UserOperationRecordMapper {
    List<UserOperationRecordDO> selectUserOperationRecord(
    		@Param("userName") String userName,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);

    void insertUserOperationRecord(UserOperationRecordDO userOperationRecordDO);
}
