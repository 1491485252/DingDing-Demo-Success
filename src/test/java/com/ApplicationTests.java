package com;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.request.OapiUserListbypageRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.dingtalk.api.response.OapiUserListbypageResponse;
import com.dingtalk.api.response.OapiUserListbypageResponse.Userlist;
import com.taobao.api.ApiException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    // @Test
    // public void contextLoads() {
    // DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
    // OapiGettokenRequest request = new OapiGettokenRequest();
    // request.setAppkey("dingaiis3afuqugi69t5");
    // request.setAppsecret("5iieXTG2xMh0h3xO4zJwchYbnKRebwE6zKkC2ksSXYKc0qR6eOaQ6dnJK-qN1CRn");
    // request.setHttpMethod("GET");
    // try {
    // OapiGettokenResponse response = client.execute(request);
    // System.out.println(response.getAccessToken());
    // } catch (ApiException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // }

    // @Test
    // public void getUserId() {
    // DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/getuserinfo");
    // OapiUserGetuserinfoRequest request = new OapiUserGetuserinfoRequest();
    // request.setCode("74afeb3679f9374689e7a43ac28651a7");
    // request.setHttpMethod("GET");
    // OapiUserGetuserinfoResponse response;
    // try {
    // response = client.execute(request, "7af962a6817e32be9a8ce8e66d4197b3");
    // System.out.println(response.toString());
    // String userId = response.getUserid();
    // System.out.println(userId);
    // } catch (ApiException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    //
    // }

     @Test
     public void pushM() {
         String accessToken = getAccessToken();
    
    
     DingTalkClient client = new
     DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2");
     OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
    
     request.setUseridList("040710621420225931");
     request.setDeptIdList("111456430");
     request.setAgentId(253029537L);
     request.setToAllUser(false);
    
     OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
     msg.setMsgtype("text");
     msg.setText(new OapiMessageCorpconversationAsyncsendV2Request.Text());
     msg.getText().setContent("测试部门下的全部人员");
     request.setMsg(msg);
     try {
     OapiMessageCorpconversationAsyncsendV2Response response = client.execute(request,accessToken);
     System.out.println(response.toString());
     } catch (ApiException e) {
    
     e.printStackTrace();
     }
     }

//    @Test
//    public void getDepart() {
//        String accessToken = getAccessToken();
//
//        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/get");
//        OapiUserGetRequest request = new OapiUserGetRequest();
//        request.setUserid("manager6698");
//        request.setHttpMethod("GET");
//        try {
//            OapiUserGetResponse response = client.execute(request, accessToken);
//            System.out.println(Arrays.asList(response.getDepartment()));
//        } catch (ApiException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void getUserIdList1() {
//        String accessToken = getAccessToken();
//        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/listbypage");
//        OapiUserListbypageRequest request = new OapiUserListbypageRequest();
//        request.setDepartmentId(111456430L);
//        request.setOffset(0L);
//        request.setSize(10L);
//        request.setOrder("entry_desc");
//        request.setHttpMethod("GET");
//        try {
//            OapiUserListbypageResponse execute = client.execute(request,accessToken);
//            List<Userlist> list = execute.getUserlist();
//            for(Userlist item:list) {
//                System.out.println(item.getIsLeader());
//                System.out.println(item.getUserid());
//                System.out.println(item.getName());
//            }
//            
//        } catch (ApiException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
    
    private String getAccessToken() {
        DefaultDingTalkClient client2 = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest request2 = new OapiGettokenRequest();
        request2.setAppkey("dingixrxdtsdsg1ofguu");
        request2.setAppsecret("fvu8vsypCum3ezWLmOok1j33AkTvbPVOFHANoEPuD-QADsuwY2rrUKINO3uhLyw-");
        request2.setHttpMethod("GET");
        try {
            OapiGettokenResponse response = client2.execute(request2);
            return response.getAccessToken();
        } catch (ApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

}
