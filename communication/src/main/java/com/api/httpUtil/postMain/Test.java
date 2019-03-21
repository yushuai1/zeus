package com.api.httpUtil.postMain;

import com.alibaba.fastjson.JSON;
import com.api.httpUtil.postMain.test.ApiDosageDTO;
import com.api.httpUtil.postMain.test.ApiOverviewDTO;
import com.api.httpUtil.postMain.test.GatewayStatisticsDTO;
import com.api.httpUtil.postMain.test.UserDosageDTO;
import entry.test.TestEntry;
import entry.test.User;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Test {

    public static void main(String[] as) {
//        String numString = "1";
//        System.out.println(Integer.parseInt(numString));
//        postniu();
//        postTest();
        getTest();
//        postUse();
//        postUseUsage();
    }

    private static void getTest() {

        ExcePostMain excePostMain = new ExcePostMainImpl();
        String re1=null;
        while(true){
            PostMainEntry postMainEntry1 = new PostMainEntry
                    (HttpMethod.GET,
                            "http://127.0.0.1:8825/test1");
             re1 = excePostMain.getResult(postMainEntry1, String.class);
        }

//        System.out.println(re1);

    }

    private static void postTest() {
        ExcePostMain excePostMain = new ExcePostMainImpl();
        PostMainEntry postMainEntry = new PostMainEntry(HttpMethod.POST, "http://bonus.sports.sina.com.cn/rwd/index/incr");
        MultiValueMap params = new LinkedMultiValueMap<>();
        params.add("mid", 192347);
        params.add("mo_id","583ecae2-fb46-11e1-82cb-f4ce4684ea4c");
        postMainEntry.setParams(params);
        Map<String, Integer> re = excePostMain.getResult(postMainEntry, Map.class);
        System.out.println(re.toString());
    }

    private static void postentry() {
        ExcePostMain excePostMain = new ExcePostMainImpl();
        PostMainEntry postMainEntry = new PostMainEntry(HttpMethod.POST, "http://127.0.0.1:1980/testEntry");
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name", "yushuai");
        postMainEntry.setParams(params);
        TestEntry re = excePostMain.getResult(postMainEntry, TestEntry.class);
        System.out.println(re);
    }


    private static void postUse(){
        ExcePostMain excePostMain = new ExcePostMainImpl();
        PostMainEntry postMainEntry = new PostMainEntry(HttpMethod.POST, "http://127.0.0.1:37000/statistics/userdosage");
        ApiOverviewDTO apiOverviewDTO = new ApiOverviewDTO();
        apiOverviewDTO.setPageStart(1);
        apiOverviewDTO.setPageSize(10);
        apiOverviewDTO.setCurrentTime("2018-07-09");
        apiOverviewDTO.setTimeType(2);
        postMainEntry.setObject(apiOverviewDTO);

        ApiOverviewDTO re = excePostMain.getResult(postMainEntry, ApiOverviewDTO.class);
    }


    private static void postUseUsage(){
        ExcePostMain excePostMain = new ExcePostMainImpl();
        PostMainEntry postMainEntry = new PostMainEntry(HttpMethod.POST,
                "http://127.0.0.1:37000/statistics/overviewPage");
        ApiDosageDTO apiOverviewDTO = new ApiDosageDTO();
        apiOverviewDTO.setRegST("2018-07-06");
        apiOverviewDTO.setRegET("2018-07-12");
        apiOverviewDTO.setPageStart(1);
        apiOverviewDTO.setPageSize(10);
        apiOverviewDTO.setCurrentTime("2018-07-09");
        apiOverviewDTO.setTimeType(2);
        apiOverviewDTO.setInterfaceId(1947443022);
        apiOverviewDTO.setIndustry(1);
        postMainEntry.setObject(apiOverviewDTO);

        ApiDosageDTO re = excePostMain.getResult(postMainEntry, ApiDosageDTO.class);
    }

    private static void postniu() {

        String[] uri = {"/api/irisV1/v1/reg", "/api/irisV1/v1/authenticate", "/api/irisV1/v1/discernByPerson",
                "/api/irisV1/v1/regByCheckCreate", "/api/irisV1/v1/irisDiscern", "/api/irisV1/v1/regByGroupToken",
                "/api/irisV1/v1/irisDeleteByIds", "/api/irisV1/v1/addBind", "/api/irisV1/v1/coverBind",
                "/api/irisV1/v1/person/add", "/api/irisV1/v1/person/update", "/api/irisV1/v1/person/deleteById",
                "/api/irisV1/v1/uploadImg", "/api/irisV1/v1/regCheckPic", "/api/developer/v1/addAppTokenKey",
                "/api/irisV1/v1/getFeatureByPersonId", "/api/developer/v1/getDev", "/api/developer/v1/getAppById"};

        ExcePostMain excePostMain = new ExcePostMainImpl();
        PostMainEntry postMainEntry = new PostMainEntry(HttpMethod.POST, "http://10.2.1.150:37000/message/sendGateway");


        long time = 1531065600000L+(long)3600*1000*24L;
        for (int i = 1; i < 20000; i++) {
            List<GatewayStatisticsDTO> list = new ArrayList<>();
            GatewayStatisticsDTO gatewayStatisticsDTO = new GatewayStatisticsDTO();
            int k = i % 5;
            switch (k) {
                case 0:
                    gatewayStatisticsDTO.setAppId(4481495516505524L);
                    break;
                case 1:
                    gatewayStatisticsDTO.setAppId(1561484399090520L);
                    break;
                case 2:
                    gatewayStatisticsDTO.setAppId(379326786755654L);
                    break;
                case 3:
                    gatewayStatisticsDTO.setAppId(1846403336563147L);
                    break;
                case 4:
                    gatewayStatisticsDTO.setAppId(4533018451160330L);
                    break;
            }
            int m = i % uri.length;
            gatewayStatisticsDTO.setUrl(uri[m]);

            Random random = new Random();
//            System.out.println(Math.random());
            gatewayStatisticsDTO.setCurrentTime(time + random.nextInt(24 * 3600 * 1000));
            gatewayStatisticsDTO.setPort(123);
            gatewayStatisticsDTO.setIp("127.0.0.1");
            int s = (Math.random() > 0.9) ? 2 : 1;
            gatewayStatisticsDTO.setStatus(s);
            list.add(gatewayStatisticsDTO);
            postMainEntry.setObject(list);

            String re = excePostMain.getResult(postMainEntry, String.class);
            System.out.println(re);
        }

    }

}
