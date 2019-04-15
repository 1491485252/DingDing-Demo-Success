package com.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cat.common.Const;
import com.cat.common.utils.CollectionUtils;
import com.config.URLConstant;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.dingtalk.api.response.OapiUserListbypageResponse.Userlist;
import com.taobao.api.ApiException;

public class PushMessageUtil {

    public static void pushMessage(String userId, String content) {
        if (StringUtils.isEmpty(userId)) {
            return;
        }
        OapiUserGetResponse user = UserDepartUtils.getUser(userId);
        if (null == user) {
            return;
        }
        List<Long> depart = user.getDepartment();
        if (CollectionUtils.isEmpty(depart)) {
            return;
        }
        List<String> userIdList = new ArrayList<String>(100);
        for (Long de : depart) {
            List<Userlist> userlists = UserDepartUtils.listUserBypage(de);
            userIdList.addAll(UserDepartUtils.listLeaderUserid(userlists));
        }
        String userIdlist = getUserid(userIdList);
        push(userIdlist, "日报消息", content, "http://devincs.vaiwan.com/page/daily/daily", "");
    }

    @SuppressWarnings("unused")
    public static void push(String useridList, String title, String text, String messageUrl, String picUrl) {

        DingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_PUSH_MESSAGE);

        OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
        request.setUseridList(useridList);
        request.setAgentId(Const.AGENT_ID);
        request.setToAllUser(false);

        OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
        msg.setMsgtype("link");
        msg.setLink(new OapiMessageCorpconversationAsyncsendV2Request.Link());
        msg.getLink().setTitle(title);
        msg.getLink().setText(text);
        msg.getLink().setMessageUrl(messageUrl);
        msg.getLink().setPicUrl(picUrl);
        request.setMsg(msg);

        try {
            OapiMessageCorpconversationAsyncsendV2Response response = client.execute(request,
                    AccessTokenUtil.getToken());
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }

    /**
     * 根据部门用户详情获取领导的userid列表
     * 
     * @param userList
     * @return
     */
    public static String getUserid(List<String> userIdList) {
        if (CollectionUtils.isEmpty(userIdList)) {
            return StringUtils.EMPTY;
        }
        return StringUtils.join(userIdList, "USER_ID_LIST");
    }
}
