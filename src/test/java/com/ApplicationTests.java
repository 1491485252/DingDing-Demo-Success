package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiUserGetuserinfoRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiUserGetuserinfoResponse;
import com.taobao.api.ApiException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

//	@Test
//	public void contextLoads() {
//	    DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
//	    OapiGettokenRequest request = new OapiGettokenRequest();
//	    request.setAppkey("dingaiis3afuqugi69t5");
//	    request.setAppsecret("5iieXTG2xMh0h3xO4zJwchYbnKRebwE6zKkC2ksSXYKc0qR6eOaQ6dnJK-qN1CRn");
//	    request.setHttpMethod("GET");
//	    try {
//            OapiGettokenResponse response = client.execute(request);
//            System.out.println(response.getAccessToken());
//        } catch (ApiException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//	}

	@Test
    public void getUserId() {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/getuserinfo");
        OapiUserGetuserinfoRequest request = new OapiUserGetuserinfoRequest();
        request.setCode("74afeb3679f9374689e7a43ac28651a7");
        request.setHttpMethod("GET");
        OapiUserGetuserinfoResponse response;
        try {
            response = client.execute(request, "7af962a6817e32be9a8ce8e66d4197b3");
            System.out.println(response.toString());
            String userId = response.getUserid();
            System.out.println(userId);
        } catch (ApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
