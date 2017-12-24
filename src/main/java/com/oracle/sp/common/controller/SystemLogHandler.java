package com.oracle.sp.common.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.oracle.sp.common.service.Interface.SystemLogService;
import com.oracle.sp.common.util.Response;
import com.oracle.sp.common.util.ResponseFactory;
import com.oracle.sp.domain.AccessRecordDO;
import com.oracle.sp.domain.UserOperationRecordDTO;
import com.oracle.sp.exception.SystemLogServiceException;

@Controller
@RequestMapping(value = "/systemLog")
public class SystemLogHandler {
	
    @Autowired
    private SystemLogService systemLogService;
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "getAccessRecords", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getAccessRecords(@RequestParam("userName") String userNameStr,
                                         @RequestParam("accessType") String accessType,
                                         @RequestParam("startDate") String startDateStr,
                                         @RequestParam("endDate") String endDateStr,
                                         @RequestParam("offset") int offset,
                                         @RequestParam("limit") int limit) throws SystemLogServiceException {

        Response response = ResponseFactory.newInstance();
        List<AccessRecordDO> rows = null;
        long total = 0;

        String regex = "([0-9]{4})-([0-9]{2})-([0-9]{2})";
        boolean startDateFormatCheck = (StringUtils.isEmpty(startDateStr) || startDateStr.matches(regex));
        boolean endDateFormatCheck = (StringUtils.isEmpty(endDateStr) || endDateStr.matches(regex));
        boolean userNameCheck = StringUtils.isEmpty(userNameStr);

        if (startDateFormatCheck && endDateFormatCheck && userNameCheck) {
            Map<String, Object> queryResult = systemLogService.selectAccessRecord(userNameStr, accessType, startDateStr, endDateStr, offset, limit);
            if (queryResult != null) {
                rows = (List<AccessRecordDO>) queryResult.get("data");
                total = (long) queryResult.get("total");
            }
        } else
            response.setResponseMsg("Request Argument Error");

        if (rows == null)
            rows = new ArrayList<>(0);

        response.setUserInfo("rows", rows);
        response.setResponseTotal(total);
        return response.generateResponse();
    }

    
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "getUserOperationRecords")
    public @ResponseBody
    Map<String, Object> selectUserOperationRecords(@RequestParam("userName") String userNameStr,
                                                   @RequestParam("startDate") String startDateStr,
                                                   @RequestParam("endDate") String endDateStr,
                                                   @RequestParam("offset") int offset,
                                                   @RequestParam("limit") int limit) throws SystemLogServiceException {

        Response response = ResponseFactory.newInstance();
        List<UserOperationRecordDTO> rows = null;
        long total = 0;

        String regex = "([0-9]{4})-([0-9]{2})-([0-9]{2})";
        boolean startDateFormatCheck = (StringUtils.isEmpty(startDateStr) || startDateStr.matches(regex));
        boolean endDateFormatCheck = (StringUtils.isEmpty(endDateStr) || endDateStr.matches(regex));
        boolean userIDCheck = StringUtils.isEmpty(userNameStr);

        if (startDateFormatCheck && endDateFormatCheck && userIDCheck) {
            
        	Map<String, Object> queryResult = 
            		systemLogService.selectUserOperationRecord(
            				userNameStr, 
            				startDateStr, 
            				endDateStr, 
            				offset, 
            				limit);
            
            if (queryResult != null) {
                rows = (List<UserOperationRecordDTO>) queryResult.get("data");
                total = (long) queryResult.get("total");
            }
        } else
            response.setResponseMsg("Request argument error");

        if (rows == null)
            rows = new ArrayList<>(0);

        response.setUserInfo("rows", rows);
        response.setResponseTotal(total);
        return response.generateResponse();
    }
}
