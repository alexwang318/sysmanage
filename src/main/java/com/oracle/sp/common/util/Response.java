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
     * @param result response status，success or error
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
     * @param data response 中携带的数据
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
     * 设置 response 自定义信息
     * @param key 自定义信息的 key
     * @param value 自定义信息的值
     */
    public void setCustomerInfo(String key, Object value){
        this.responseContent.put(key,value);
    }

    /**
     * 生成 response
     * @return 代表 response 的一个 Map 对象
     */
    public Map<String, Object> generateResponse(){
        return this.responseContent;
    }
}
