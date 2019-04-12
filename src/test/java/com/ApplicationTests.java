package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiUserGetRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.taobao.api.ApiException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Test
	public void contextLoads() {
	    String accessToken = "";
	    DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
	    OapiGettokenRequest request = new OapiGettokenRequest();
	    request.setAppkey("dingoax3vbk6bkbhvwqwhl");
	    request.setAppsecret("_lnXazx-Kzpy0fuv9oo6wjbHowlSRWWPLkZlgSOY6HSLo95QBmwq0ujJZxxMNteq-qN1CRn");
	    request.setHttpMethod("GET");
	    try {
            OapiGettokenResponse response = client.execute(request);
            System.out.println(response.toString());
            System.out.println(response.getAccessToken());
            accessToken = response.getAccessToken();
        } catch (ApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    

	}

//	@Test
//	public void getUser() {
//	       DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/get");
//	        OapiUserGetRequest request = new OapiUserGetRequest();
//	        request.setUserid("zhangsan");
//	        request.setHttpMethod("GET");
//	        try {
//                OapiUserGetResponse response = client.execute(request, "7af962a6817e32be9a8ce8e66d4197b3");
//            } catch (ApiException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//	}
}
