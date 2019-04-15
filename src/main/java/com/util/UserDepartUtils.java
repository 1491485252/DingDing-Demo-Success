package com.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cat.common.utils.CollectionUtils;
import com.config.Constant;
import com.config.URLConstant;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiUserGetRequest;
import com.dingtalk.api.request.OapiUserListbypageRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.dingtalk.api.response.OapiUserListbypageResponse;
import com.dingtalk.api.response.OapiUserListbypageResponse.Userlist;
import com.taobao.api.ApiException;

public class UserDepartUtils {

    private static final Logger bizLogger = LoggerFactory.getLogger(AccessTokenUtil.class);

    public static String getToken() throws RuntimeException {
        try {
            DefaultDingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_GET_TOKKEN);
            OapiGettokenRequest request = new OapiGettokenRequest();

            request.setAppkey(Constant.APP_KEY);
            request.setAppsecret(Constant.APP_SECRET);
            request.setHttpMethod("GET");
            OapiGettokenResponse response = client.execute(request);
            String accessToken = response.getAccessToken();
            return accessToken;
        } catch (ApiException e) {
            bizLogger.error("getAccessToken failed", e);
            throw new RuntimeException();
        }

    }

    /**
     * 获取部门用户详情
     * 
     * @return
     */
    public static OapiUserGetResponse getUser(String userId) {
        String accessToken = getToken();
        DingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_USER_GET);
        OapiUserGetRequest request = new OapiUserGetRequest();
        request.setUserid(userId);
        request.setHttpMethod("GET");
        try {
            OapiUserGetResponse response = client.execute(request, accessToken);
            return response;
        } catch (ApiException e) {
            e.printStackTrace();
           return null;
        }
    }

    /**
     * 获取部门用户详情
     * 
     * @return
     */
    public static List<Userlist> listUserBypage(Long departmentId) {
        String accessToken = getToken();
        DingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_DEPART_USER);
        OapiUserListbypageRequest request = new OapiUserListbypageRequest();
        request.setDepartmentId(departmentId);
        request.setOffset(0L);
        request.setSize(100L);
        request.setOrder("entry_desc");
        request.setHttpMethod("GET");
        try {
            OapiUserListbypageResponse execute = client.execute(request, accessToken);
            List<Userlist> list = execute.getUserlist();
            return list;
        } catch (ApiException e) {
            return Collections.emptyList();
        }
    }
    
    /**
     * 根据部门用户详情获取领导的userid列表
     * @param userList
     * @return
     */
    public static List<String> listLeaderUserid(List<Userlist> userList) {
        if(CollectionUtils.isEmpty(userList)) {
            return Collections.emptyList();
        }
        List<String> userIdList = new ArrayList<String>(userList.size());
        for(Userlist item:userList) {
            if(item.getIsLeader()) {
                userIdList.add(item.getUserid());
            }
        }
        return userIdList;
    }

}
