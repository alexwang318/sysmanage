package com.oracle.sp.common.util;

import org.apache.commons.collections.map.HashedMap;
import java.util.Map;


public class Response {
    public static final String RESPONSE_RESULT_SUCCESS = "success";
    public static final String RESPONSE_RESULT_ERROR = "error";

    // response
    private static final String RESPONSE_RESULT = "result";
    private static final String RESPONSE_MSG = "msg";
    private static final String RESPONSE_DATA = "data";
    private static final String RESPONSE_TOTAL = "total";

    private Map<String,Object> responseContent;

    // Constructor
    Response() {
        this.responseContent = new HashedMap(10);
    }

    /**
     * set response status
     * @param result response status��success or error
     */
    public void setResponseResult(String result){
        this.responseContent.put(Response.RESPONSE_RESULT,result);
    }

    /**
     * set response additional info
     * @param msg response  info
     */
    public void setResponseMsg(String msg){
        this.responseContent.put(Response.RESPONSE_MSG,msg);
    }

    /**
     * set response payload
     * @param data response ��Я��������
     */
    public void setResponseData(Object data){
        this.responseContent.put(Response.RESPONSE_DATA,data);
    }

    /**
     * set response length of payload
     * @param total length
     */
    public void setResponseTotal(long total){
        this.responseContent.put(Response.RESPONSE_TOTAL,total);
    }

    /**
     * ���� response �Զ�����Ϣ
     * @param key �Զ�����Ϣ�� key
     * @param value �Զ�����Ϣ��ֵ
     */
    public void setCustomerInfo(String key, Object value){
        this.responseContent.put(key,value);
    }

    /**
     * ���� response
     * @return ���� response ��һ�� Map ����
     */
    public Map<String, Object> generateResponse(){
        return this.responseContent;
    }
}
