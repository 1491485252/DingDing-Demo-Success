package com.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.cat.common.Const;
import com.cat.common.utils.CollectionUtils;
import com.cat.model.DailyForm;
import com.config.URLConstant;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.dingtalk.api.response.OapiUserListbypageResponse.Userlist;
import com.taobao.api.ApiException;

public class PushMessageUtil {

    public static void pushMessage(String userId, DailyForm dailyForm) {
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
        if(!userIdList.contains(userId)) {
            userIdList.add(userId);
        }
        String userIdlist = getUserid(userIdList);
        //pushText(userIdlist, content);
        String messageText = dailyForm.getDate() + "日报";
        String content = JSONObject.toJSONString(dailyForm);
        push(userIdlist, dailyForm.getUserName()+"的日报消息", messageText, "eapp://page/dailyDetail/dailyDetail?content=" + content, "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1752243568,253651337&fm=27&gp=0.jpg");
    }

    @SuppressWarnings("unused")
    public static void pushText(String useridList, String text) {

        DingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_PUSH_MESSAGE);

        OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
        request.setUseridList(useridList);
        request.setAgentId(Const.AGENT_ID);
        request.setToAllUser(false);

        OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
        msg.setMsgtype("text");
        msg.setText(new OapiMessageCorpconversationAsyncsendV2Request.Text());
        msg.getText().setContent(text);
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
        return StringUtils.join(userIdList, Const.USER_ID_LIST);
    }
}
