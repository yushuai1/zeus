//package com.多线程.读写锁.cache;
//
//import com.irisking.web.open.common.dto.statistics.UserInfoStatisticsDTO;
//import com.irisking.web.open.util.UtilTTool;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//import static com.irisking.web.open.common.util.statistics.Setting.APPINFO;
//
//@Component
//@Order(value = 1)
//public class InitCache implements CommandLineRunner {
//    private final static Logger log = LoggerFactory.getLogger(InitCache.class);
//    @Autowired
//    MongoTemplate mongoTemplate;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        //初始化人员缓存
//        try {
//            List<UserInfoStatisticsDTO> userInList = mongoTemplate.findAll(UserInfoStatisticsDTO.class, APPINFO);
//            for (UserInfoStatisticsDTO u : userInList) {
//                UtilTTool.filter.put(u.getAppId(), u);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//}
