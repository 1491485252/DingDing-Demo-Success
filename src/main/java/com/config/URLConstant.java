package com.config;

public class URLConstant {
    /**
     * 钉钉网关gettoken地址
     */
    public static final String URL_GET_TOKKEN = "https://oapi.dingtalk.com/gettoken";

    /**
     *获取用户在企业内userId的接口URL
     */
    public static final String URL_GET_USER_INFO = "https://oapi.dingtalk.com/user/getuserinfo";

    /**
     *获取用户姓名的接口url
     */
    public static final String URL_USER_GET = "https://oapi.dingtalk.com/user/get";
    
    /**
     * 获取部门用户详情
     */
    public static final String URL_DEPART_USER= "https://oapi.dingtalk.com/user/listbypage";
    
    /**
     * 发送工作通知消息
     */
    public static final String URL_PUSH_MESSAGE = "https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2";
}
